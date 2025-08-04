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
  providers: [DatePipe],
})
export class SessionHistoryComponent implements OnInit {
  sessions: any[] = [];
  filteredSessions: any[] = []; // Add filtered sessions array

  // Status filter options
  statusFilters = [
    { value: 'PENDING', label: 'Pendente', checked: true },
    { value: 'ACCEPTED', label: 'Aceito', checked: true },
    { value: 'REJECTED', label: 'Rejeitado', checked: true },
    { value: 'COMPLETED', label: 'Concluído', checked: true },
    { value: 'CANCELLED', label: 'Cancelado', checked: true },
  ];

  private profileService = inject(ProfileService);
  private router = inject(Router);

  ngOnInit(): void {
    this.loadSessionHistory();
  }

  loadSessionHistory(): void {
    this.profileService.getSessionHistory().subscribe({
      next: (data) => {
        this.sessions = data;
        this.applyFilters(); // Apply filters after loading
      },
      error: (err) => {
        console.error('Erro ao carregar histórico de sessões:', err);
      },
    });
  }

  // Toggle filter checkbox
  onFilterChange(status: string): void {
    const filter = this.statusFilters.find((f) => f.value === status);
    if (filter) {
      filter.checked = !filter.checked;
      this.applyFilters();
    }
  }

  // Apply selected filters
  applyFilters(): void {
    const selectedStatuses = this.statusFilters
      .filter((filter) => filter.checked)
      .map((filter) => filter.value);

    this.filteredSessions = this.sessions.filter((session) =>
      selectedStatuses.includes(session.status)
    );
  }

  // Select/Deselect all filters
  toggleAllFilters(selectAll: boolean): void {
    this.statusFilters.forEach((filter) => (filter.checked = selectAll));
    this.applyFilters();
  }

  // Check if all filters are selected
  get allFiltersSelected(): boolean {
    return this.statusFilters.every((filter) => filter.checked);
  }

  // Check if no filters are selected
  get noFiltersSelected(): boolean {
    return this.statusFilters.every((filter) => !filter.checked);
  }

  getStatusClass(status: string): string {
    return `status-${status.toLowerCase()}`;
  }

  goBack(): void {
    this.router.navigate(['/home']);
  }
}
