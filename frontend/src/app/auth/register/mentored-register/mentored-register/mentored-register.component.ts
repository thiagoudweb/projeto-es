import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../auth.service';

@Component({
  selector: 'app-mentored-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './mentored-register.component.html',
  styleUrl: './mentored-register.component.css'
})
export class MentoredRegisterComponent {
  title = 'Finalizar Cadastro';

  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  signupForm: FormGroup = this.fb.group({
    course: ['', [Validators.required, Validators.minLength(3)]],
    academicSummary: ['', [Validators.required, Validators.minLength(3)]],
    interestList: ['', [Validators.required]],
  })

  onSubmit() {

  
  }
}
