import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../auth.service';

@Component({
  selector: 'app-mentor-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './mentor-register.component.html',
  styleUrl: './mentor-register.component.css'
})
export class MentorRegisterComponent {
  title = 'Finalizar Cadastro';

  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  signupForm: FormGroup = this.fb.group({
    course: ['', [Validators.required, Validators.minLength(3)]],
    professionalSummary: ['', [Validators.required, Validators.minLength(3)]],
    interestList: ['', [Validators.required]],
    affiliationType: ['', [Validators.required]],
    specializations: ['', [Validators.required]],
  })

  onSubmit() {

  
  }

}
