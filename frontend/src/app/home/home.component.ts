import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { ProfileService } from '../profile/profile.service';
import { Mentor } from '../entity/mentor';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit, OnDestroy {
  title = 'Mentoria';
  mentors: Mentor[] = [];
  mentorSearchPerformed = false;

  private authService = inject(AuthService);
  private router = inject(Router);
  private profileService = inject(ProfileService);

  searchMentors() {
    // lógica de busca
    this.mentorSearchPerformed = true;


  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  hasMentorRole(): boolean {
    return this.authService.hasRole('MENTOR');
  }
  private searchSubject = new Subject<string>();

  ngOnInit() {
    this.searchSubject
      .pipe(debounceTime(322), distinctUntilChanged())
      .subscribe((searchTerm) => {
        this.onSearchInput(searchTerm, undefined);
      });
  }

  ngOnDestroy() {
    this.searchSubject.complete();
  }

  onSearchInput(interestArea: string, specializations?: string): void {
    this.profileService.searchMentors(interestArea, specializations).subscribe({
      next: (mentors) => {
        this.mentors = mentors;
        this.mentorSearchPerformed = true;
      },
      error: (err) => {
        console.error('Erro ao buscar mentores:', err);
        this.mentors = [];
        this.mentorSearchPerformed = true;
      }
    });
  }


  results: any[] = [];

  interestAreas = [
    { value: 'TECNOLOGIA_DA_INFORMACAO', label: 'Tecnologia da Informação' },
    { value: 'DESENVOLVIMENTO_DE_SOFTWARE', label: 'Desenvolvimento de Software' },
    { value: 'CIENCIA_DE_DADOS_E_IA', label: 'Ciência de Dados e Inteligência Artificial' },
    { value: 'CIBERSEGURANCA', label: 'Cibersegurança' },
    { value: 'UX_UI_DESIGN', label: 'UX/UI Design' },
    { value: 'ENGENHARIA_GERAL', label: 'Engenharia' },
    { value: 'ENGENHARIA_CIVIL', label: 'Engenharia Civil' },
    { value: 'ENGENHARIA_DE_PRODUCAO', label: 'Engenharia de Produção' },
    { value: 'MATEMATICA_E_ESTATISTICA', label: 'Matemática e Estatística' },
    { value: 'FISICA', label: 'Física' },
    { value: 'ADMINISTRACAO_E_GESTAO', label: 'Administração e Gestão' },
    { value: 'EMPREENDEDORISMO_E_INOVACAO', label: 'Empreendedorismo e Inovação' },
    { value: 'FINANCAS_E_CONTABILIDADE', label: 'Finanças e Contabilidade' },
    { value: 'RECURSOS_HUMANOS', label: 'Recursos Humanos' },
    { value: 'LOGISTICA_E_CADEIA_DE_SUPRIMENTOS', label: 'Logística e Cadeia de Suprimentos' },
    { value: 'MARKETING_E_COMUNICACAO', label: 'Marketing e Comunicação' },
    { value: 'MARKETING_DIGITAL', label: 'Marketing Digital' },
    { value: 'JORNALISMO', label: 'Jornalismo' },
    { value: 'PUBLICIDADE_E_PROPAGANDA', label: 'Publicidade e Propaganda' },
    { value: 'COMUNICACAO_INSTITUCIONAL', label: 'Comunicação Institucional' },
    { value: 'CIENCIAS_BIOLOGICAS_E_SAUDE', label: 'Ciências Biológicas e Saúde' },
    { value: 'MEDICINA', label: 'Medicina' },
    { value: 'PSICOLOGIA', label: 'Psicologia' },
    { value: 'NUTRICAO', label: 'Nutrição' },
    { value: 'BIOTECNOLOGIA', label: 'Biotecnologia' },
    { value: 'EDUCACAO', label: 'Educação' },
    { value: 'ARTES_E_DESIGN', label: 'Artes e Design' },
    { value: 'CIENCIAS_HUMANAS_E_SOCIAIS', label: 'Ciências Humanas e Sociais' },
    { value: 'LETRAS', label: 'Letras' },
    { value: 'HISTORIA', label: 'História' },
    { value: 'GEOGRAFIA', label: 'Geografia' },
    { value: 'SOCIOLOGIA', label: 'Sociologia' }
  ];

  selectedInterestArea: string = '';
  specializationsInput: string = '';

  onMentorSearch(): void {
    const specializations = this.specializationsInput
      ? this.specializationsInput.split(',').map(s => s.trim()).filter(Boolean)
      : undefined;
    this.profileService.searchMentors(this.selectedInterestArea, specializations).subscribe({
      next: (mentors) => {
        this.mentors = mentors;
        this.mentorSearchPerformed = true;
      },
      error: (err) => {
        console.error('Erro ao buscar mentores:', err);
        this.mentors = [];
        this.mentorSearchPerformed = true;
      }
    });
  }
}
