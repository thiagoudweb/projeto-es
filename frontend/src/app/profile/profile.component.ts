import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProfileService, ProfileData } from './profile.service';
import { EditMentoredModalComponent } from '../auth/edit-mentored-profile-modal/edit-mentored-modal.component';
import { EditMentorProfileModalComponent } from '../auth/edit-mentor-profile-modal/edit-mentor-profile-modal.component';
@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    EditMentorProfileModalComponent,
    EditMentoredModalComponent
  ],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profileData: ProfileData | null = null;
  loading = true;
  error: string | null = null;
  showEditModal = false;

  constructor(private profileService: ProfileService) {}

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
}
