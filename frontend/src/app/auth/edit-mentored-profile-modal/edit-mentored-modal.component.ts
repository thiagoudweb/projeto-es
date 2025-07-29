import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges
} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Mentored } from '../../entity/mentored';

@Component({
  selector: 'app-edit-mentored-modal',
  standalone: true,
  templateUrl: './edit-mentored-modal.component.html',
  styleUrls: ['./edit-mentored-modal.component.css'],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EditMentoredModalComponent implements OnChanges {
  @Input() mentored!: Mentored;
  @Output() save = new EventEmitter<Mentored>();
  @Output() close = new EventEmitter<void>();

  mentoredForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.mentoredForm = this.fb.group({
      cpf: ['', Validators.required],
      birthDate: ['', Validators.required],
      course: ['', Validators.required],
      academicSummary: ['']
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['mentored'] && this.mentored) {
      this.mentoredForm.patchValue({ ...this.mentored });
    }
  }

  onSave() {
    if (this.mentoredForm.valid) {
      const updatedMentored: Mentored = {
        ...this.mentored,
        ...this.mentoredForm.value
      };
      this.save.emit(updatedMentored);
    }
  }

  onClose() {
    this.close.emit();
  }
}
