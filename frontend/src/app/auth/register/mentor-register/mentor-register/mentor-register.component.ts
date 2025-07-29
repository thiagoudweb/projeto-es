import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../auth.service';
import { User } from '../../../../entity/user';
import { Mentor } from '../../../../entity/mentor';

@Component({
  selector: 'app-mentor-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './mentor-register.component.html',
  styleUrl: './mentor-register.component.css'
})
export class MentorRegisterComponent implements OnInit {
  title = 'Finalizar Cadastro';
  userData: User | null = null;

  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  signupForm: FormGroup = this.fb.group({
    course: ['', [Validators.required, Validators.minLength(3)]],
    professionalSummary: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(500)]],
    interestList: ['', [Validators.required]],
    affiliationType: ['', [Validators.required]],
    specializations: ['', [Validators.required]],
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
      const mentorData: Mentor = {
        ...this.userData,
        ...this.signupForm.value
      };

      //integrar o cadastro do mentor ao backend
    }
  }

}
