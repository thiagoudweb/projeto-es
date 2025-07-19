import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { REACTIVE_NODE } from '@angular/core/primitives/signals';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  title = 'Cadastro';
  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);
  private passwordsMatchValidator(): ValidatorFn {
    return (group: AbstractControl): ValidationErrors | null => {
      const password = group.get('password')?.value;
      const confirmPassword = group.get('confirmPassword')?.value;
      return password === confirmPassword ? null : { passwordsMismatch: true };
    };
  }

  signupForm: FormGroup = this.fb.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    lastName: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8), this.passwordStrengthValidator()]],
    confirmPassword: ['', [Validators.required]],
    role: ['', Validators.required]
  }, { validators: this.passwordsMatchValidator() });

  onSubmit() {
    if (this.signupForm.valid) {
      console.log('Formulário válido:', this.signupForm.value);
    } else {
      console.log('Formulário inválido');
    }
  }

  private passwordStrengthValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (!value) return null;

      const hasUpperCase = /[A-Z]/.test(value);
      const hasLowerCase = /[a-z]/.test(value);
      const hasNumber = /\d/.test(value);
      const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(value);
      const isValid = hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar;

      return isValid ? null : { weakPassword: true };
    };
  }
}
