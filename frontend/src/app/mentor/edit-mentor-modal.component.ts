import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Mentor } from '../auth/mentor';

@Component({
  standalone: true,
  selector: 'app-edit-mentor-modal',
  templateUrl: './edit-mentor-modal.component.html',
  styleUrls: ['./edit-mentor-modal.component.css'],
  imports: [CommonModule, FormsModule]
})
export class EditMentorModalComponent implements OnInit {
  @Input() mentor!: Mentor;
  @Output() save = new EventEmitter<Mentor>();
  @Output() cancel = new EventEmitter<void>();

  specializationsString: string = '';

  ngOnInit() {
    this.specializationsString = this.mentor.specializations?.join(', ') || '';
  }

  onSubmit() {
    this.mentor.specializations = this.specializationsString
      .split(',')
      .map(s => s.trim())
      .filter(s => s.length > 0);
    this.save.emit(this.mentor);
  }

  close() {
    this.cancel.emit();
  }
}
