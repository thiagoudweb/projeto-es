import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit, OnDestroy {
  title = 'Mentoria';

  private authService = inject(AuthService);
  private router = inject(Router);

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
        this.searchMentoredByInterest(searchTerm);
      });
  }

  ngOnDestroy() {
    this.searchSubject.complete();
  }

  onSearchInput(value: string): void {
    this.searchSubject.next(value);
  }

  performSearch(interest: string): void {
    if (!interest.trim()) {
      this.results = [];
      return;
    }
    this.results = this.mentoredMockList.filter((mentored) =>
      mentored.interests.some((i: string) =>
        i.toLowerCase().includes(interest.toLowerCase())
      )
    );
    this.results.sort((a, b) => a.name.localeCompare(b.name));
    console.log('Search results:', this.results);
  }

  results: any[] = [];

  searchMentoredByInterest(interest: string): void {
    this.performSearch(interest);
  }

  mentoredMockList: any[] = [
    {
      id: 1,
      name: 'João Silva',
      interests: ['Tecnologia', 'Carreira', 'Programação'],
      description:
        'Estudante de Engenharia de Software buscando orientação em carreira tech',
      experience: 'Iniciante',
      location: 'São Paulo, SP',
    },
    {
      id: 2,
      name: 'Maria Oliveira',
      interests: ['Empreendedorismo', 'Inovação', 'Tecnologia'],
      description: 'Empreendedora iniciante no setor de tecnologia',
      experience: 'Intermediário',
      location: 'Rio de Janeiro, RJ',
    },
    {
      id: 3,
      name: 'Carlos Pereira',
      interests: ['Desenvolvimento Pessoal', 'Liderança', 'Carreira'],
      description: 'Profissional buscando crescimento em liderança',
      experience: 'Avançado',
      location: 'Belo Horizonte, MG',
    },
    {
      id: 4,
      name: 'Ana Costa',
      interests: ['Programação', 'Inteligência Artificial', 'Tecnologia'],
      description: 'Desenvolvedora focada em IA e Machine Learning',
      experience: 'Intermediário',
      location: 'Brasília, DF',
    },
    {
      id: 5,
      name: 'Pedro Santos',
      interests: ['Empreendedorismo', 'Marketing Digital', 'Inovação'],
      description: 'Fundador de startup de marketing digital',
      experience: 'Avançado',
      location: 'Florianópolis, SC',
    },
    {
      id: 6,
      name: 'Juliana Lima',
      interests: ['Carreira', 'Desenvolvimento Pessoal', 'Comunicação'],
      description: 'Coach executiva especializada em comunicação',
      experience: 'Especialista',
      location: 'Salvador, BA',
    },
    {
      id: 7,
      name: 'Rafael Mendes',
      interests: ['Tecnologia', 'Programação', 'DevOps'],
      description: 'Engenheiro DevOps com experiência em cloud computing',
      experience: 'Avançado',
      location: 'Porto Alegre, RS',
    },
    {
      id: 8,
      name: 'Fernanda Rocha',
      interests: ['Liderança', 'Gestão de Projetos', 'Desenvolvimento Pessoal'],
      description: 'Gerente de projetos em busca de aprimoramento em liderança',
      experience: 'Intermediário',
      location: 'Curitiba, PR',
    },
    {
      id: 9,
      name: 'Lucas Barbosa',
      interests: ['Inovação', 'Tecnologia', 'Startups'],
      description: 'Cofundador de startup de fintech',
      experience: 'Avançado',
      location: 'São Paulo, SP',
    },
    {
      id: 10,
      name: 'Camila Ferreira',
      interests: ['Marketing Digital', 'Empreendedorismo', 'E-commerce'],
      description: 'Especialista em marketing digital para e-commerce',
      experience: 'Especialista',
      location: 'Recife, PE',
    },
    {
      id: 11,
      name: 'Bruno Alves',
      interests: ['Programação', 'Carreira', 'Tecnologia'],
      description: 'Desenvolvedor júnior buscando crescimento profissional',
      experience: 'Iniciante',
      location: 'Fortaleza, CE',
    },
    {
      id: 12,
      name: 'Larissa Gomes',
      interests: ['Comunicação', 'Liderança', 'Gestão de Pessoas'],
      description: 'Líder de equipe focada em desenvolvimento de pessoas',
      experience: 'Avançado',
      location: 'Manaus, AM',
    },
    {
      id: 13,
      name: 'Thiago Nascimento',
      interests: ['Inteligência Artificial', 'Programação', 'Pesquisa'],
      description: 'Pesquisador em IA com foco em deep learning',
      experience: 'Especialista',
      location: 'Campinas, SP',
    },
    {
      id: 14,
      name: 'Isabela Martins',
      interests: ['Startups', 'Empreendedorismo', 'Investimentos'],
      description: 'Analista de investimentos especializada em startups',
      experience: 'Avançado',
      location: 'São Paulo, SP',
    },
    {
      id: 15,
      name: 'Gabriel Rodrigues',
      interests: ['DevOps', 'Tecnologia', 'Infraestrutura'],
      description: 'Especialista em infraestrutura e automação',
      experience: 'Especialista',
      location: 'Rio de Janeiro, RJ',
    },
  ];
}
