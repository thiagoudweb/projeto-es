
import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Mentor } from '../../entity/mentor';

@Component({
  selector: 'app-edit-mentor-profile-modal',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './edit-mentor-profile-modal.component.html',
  styleUrls: ['./edit-mentor-profile-modal.component.css']
})
export class EditMentorProfileModalComponent implements OnChanges {
  @Input() mentor!: Mentor;
  @Output() save = new EventEmitter<Mentor>();
  @Output() close = new EventEmitter<void>();

  mentorForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.mentorForm = this.fb.group({
      cpf: ['', Validators.required],
      birthDate: ['', Validators.required],
      course: ['', Validators.required],
      professionalSummary: [''],
      affiliationType: [''],
      specializations: ['']
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['mentor'] && this.mentor) {
      this.mentorForm.patchValue({
        ...this.mentor,
        specializations: this.mentor.specializations?.join(', ') || ''
      });
    }
  }

  onSave() {
    if (this.mentorForm.valid) {
      const formValue = this.mentorForm.value;
      const updatedMentor: Mentor = {
        ...this.mentor,
        ...formValue,
        specializations: formValue.specializations.split(',').map((s: string) => s.trim())
      };
      this.save.emit(updatedMentor);
    }
  }

  onClose() {
    this.close.emit();
  }
}
