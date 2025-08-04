import { Component, OnInit, inject } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { ProfileService } from '../profile/profile.service';

@Component({
  selector: 'app-session-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './session-history.component.html',
  styleUrls: ['./session-history.component.css'],
  providers: [DatePipe]
})
export class SessionHistoryComponent implements OnInit {
  sessions: any[] = [];

  private profileService = inject(ProfileService);
  private router = inject(Router);

  ngOnInit(): void {
    this.loadSessionHistory();
  }

  loadSessionHistory(): void {
    this.profileService.getSessionHistory().subscribe({
      next: (data) => {
        this.sessions = data;
      },
      error: (err) => {
        console.error('Erro ao carregar histórico de sessões:', err);
      }
    });
  }

  getStatusClass(status: string): string {
    return 'status-' + status.toLowerCase();
  }

  goBack(): void {
    this.router.navigate(['/home']);
  }
}
