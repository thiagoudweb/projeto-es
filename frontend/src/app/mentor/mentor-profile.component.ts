
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EditMentorModalComponent } from './edit-mentor-modal.component';
import { Mentor } from '../auth/mentor';

@Component({
  standalone: true,
  selector: 'app-mentor-profile',
  templateUrl: './mentor-profile.component.html',
  styleUrls: ['./mentor-profile.component.css'],
  imports: [CommonModule, FormsModule, EditMentorModalComponent]
})
export class MentorProfileComponent {
  mentor: Mentor = {
    fullName: 'Nome do Mentor',
    email: 'mentor@email.com',
    password: '',
    role: 'mentor',
    cpf: '000.000.000-00',
    birthDate: '1990-01-01',
    course: 'Curso Exemplo',
    professionalSummary: 'Resumo profissional...',
    affiliationType: 'Tipo de vínculo',
    specializations: ['Especialização 1', 'Especialização 2']
  };
  showEditModal = false;

  openEditModal() {
    this.showEditModal = true;
  }

  updateMentor(updatedMentor: Mentor) {
    this.mentor = { ...updatedMentor };
    this.showEditModal = false;
  }
}
