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
    interestList: ['', [Validators.required]],
  })

  ngOnInit() {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.userData = navigation.extras.state['userData'];
    }

    if (!this.userData) { //caso queira entrar direto na tela de mentores comentar esse bloco
      this.router.navigate(['/register']);
    }
  }

  onSubmit() {
    if (this.signupForm.valid && this.userData) {
      const mentoredData: Mentored = {
        ...this.userData,
        ...this.signupForm.value
      };

      //integrar o cadastro do mentor ao backend
    }
  }
}
