import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProfileService } from '../profile/profile.service';

@Component({
  selector: 'app-mentor-search',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './mentor-search.html',
  styleUrl: './mentor-search.css'
})
export class MentorSearchComponent {
  // Propriedades para busca de mentorados (usado por mentores)
  mentoreds: any[] = [];
  mentoredSearchPerformed = false;
  selectedInterestAreaMentor: string = '';

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

  constructor(private profileService: ProfileService) {}

  onMentoredSearch(): void {
    this.profileService.searchMentoreds(this.selectedInterestAreaMentor).subscribe({
      next: (mentoreds) => {
        this.mentoreds = mentoreds;
        this.mentoredSearchPerformed = true;
      },
      error: (err) => {
        console.error('Erro ao buscar mentorados:', err);
        this.mentoreds = [];
        this.mentoredSearchPerformed = true;
      }
    });
  }
}
