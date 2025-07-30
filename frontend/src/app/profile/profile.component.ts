import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProfileService, ProfileData } from './profile.service';
import { EditMentoredModalComponent } from '../auth/edit-mentored-profile-modal/edit-mentored-modal.component';
import { EditMentorProfileModalComponent } from '../auth/edit-mentor-profile-modal/edit-mentor-profile-modal.component';
import { DeleteMentorModalComponent } from '../auth/delete-mentor-modal/delete-mentor-modal.component';
import { DeleteMentoredModalComponent } from '../auth/delete-mentored-modal/delete-mentored-modal.component';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    EditMentorProfileModalComponent,
    EditMentoredModalComponent,
    DeleteMentorModalComponent,
    DeleteMentoredModalComponent
  ],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profileData: ProfileData | null = null;
  loading = true;
  error: string | null = null;
  showEditModal = false;
  showDeleteModal = false;

  constructor(private profileService: ProfileService, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(): void {
    this.loading = true;
    this.error = null;

    this.profileService.getCompleteProfile().subscribe({
      next: (data: ProfileData) => {
        this.profileData = data;
        this.loading = false;
        console.log('Profile loaded:', data);
      },
      error: (err: any) => {
        this.error = 'Erro ao carregar perfil';
        this.loading = false;
        console.error('Error loading profile:', err);
      }
    });
  }

  editProfile(): void {
    this.showEditModal = true;
  }

  closeEditModal(): void {
    this.showEditModal = false;
  }

  saveMentorProfile(updatedData: any): void {
    if (!this.profileData || this.profileData.type !== 'MENTOR') return;

    const mentorId = this.profileData.data.id;
    this.profileService.updateMentorProfile(mentorId, updatedData).subscribe({
      next: (response: any) => {
        console.log('Perfil atualizado com sucesso:', response);
        this.loadProfile();
        this.closeEditModal();
      },
      error: (err: any) => {
        console.error('Erro ao atualizar perfil:', err);
        this.error = 'Erro ao atualizar perfil';
      }
    });
  }

  saveMentoredProfile(updatedData: any): void {
    if (!this.profileData) return;

    this.profileService.updateMentoredProfile(updatedData).subscribe({
      next: (response: any) => {
        console.log('Profile updated successfully:', response);
        this.loadProfile();
        this.closeEditModal();
      },
      error: (err: any) => {
        console.error('Erro ao atualizar perfil:', err);
        this.error = 'Erro ao atualizar perfil';
      }
    });
  }

  openDeleteModal(): void {
    if (this.profileData?.type === 'MENTOR' || this.profileData?.type === 'MENTORADO') {
      this.showDeleteModal = true;
    } else {
      console.error('Tipo de perfil não suportado para exclusão.');
    }
  }

  async confirmDelete(): Promise<void> {
    if (!this.profileData) return;

    const id = this.profileData.data.id;
    try {
      if (this.profileData.type === 'MENTOR') {
        await this.authService.deleteMentor(id);
      } else if (this.profileData.type === 'MENTORADO') {
        await this.authService.deleteMentored(id);
      }

      this.showDeleteModal = false;
      this.authService.logout();
      window.location.href = '/login'; 
    } catch (error) {
      console.error('Erro ao deletar conta:', error);
      this.error = 'Erro ao deletar conta';
    }
  }

  cancelDelete(): void {
    this.showDeleteModal = false;
  }
}
