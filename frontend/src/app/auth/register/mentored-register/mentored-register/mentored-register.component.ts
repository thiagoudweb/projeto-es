import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../auth.service';
import { Mentored } from '../../../../entity/mentored';
import { User } from '../../../../entity/user';

@Component({
  selector: 'app-mentored-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './mentored-register.component.html',
  styleUrl: './mentored-register.component.css'
})
export class MentoredRegisterComponent implements OnInit {
  title = 'Finalizar Cadastro';
  userData: User | null = null;

  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  signupForm: FormGroup = this.fb.group({
    course: ['', [Validators.required, Validators.minLength(3)]],
    academicSummary: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(500)]],
    interestArea: ['', [Validators.required]],
  })

  ngOnInit() {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.userData = navigation.extras.state['userData'];
    }else if (history.state && history.state.userData) {
      this.userData = history.state.userData;
    }
    else{
      console.error('Nenhum dado recebido do cadastro inicial.');
    }

    if (!this.userData) {
      this.router.navigate(['/register']);
    }
  }

  onSubmit() {
    if (this.signupForm.valid && this.userData) {
      const mentoredData: Mentored = {
        fullName: this.userData.fullName,
        cpf: this.userData.cpf,
        birthDate: this.userData.birthDate,
        course: this.signupForm.value.course,
        academicSummary: this.signupForm.value.academicSummary,
        interestArea: this.signupForm.value.interestArea,
        email: this.userData.email,
        password: this.userData.password,
        role: 'MENTORED'
      };

      this.authService.registerMentored(mentoredData).then(
        () => {
          this.router.navigate(['/home'], )
            .catch(error => {
              console.error('Erro ao registrar Mentor:', error);
            });
        }
      )
    }
  }
}
