import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';
import { User } from '../../entity/user';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  title = 'Cadastro';

  // Mensagem de erro geral exibida ao usuário
  errorMessage: string | null = null;

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
    cpf: ['', [Validators.required]],
    birthDate: ['', [Validators.required]],
    password: ['', [Validators.required, Validators.minLength(8), this.passwordStrengthValidator()]],
    confirmPassword: ['', [Validators.required]],
    role: ['', Validators.required]
  }, { validators: this.passwordsMatchValidator() });

  onSubmit() {
    this.errorMessage = null; // limpa mensagem anterior

    if (this.signupForm.valid) {
      // Juntando o primeiro nome e o ultimo em um só
      const name = `${this.signupForm.value.nome} ${this.signupForm.value.lastName}`;

      // Atribuindo os valores do formulário
      const { email, password, role } = this.signupForm.value;

      // Criando o novo usuário
      const newUser: User = { fullName: name, email, password, role };

      this.authService.register(newUser).then(
        () => {
          if (role === 'MENTOR') {
            this.router.navigate(['/register-mentor'], { 
              state: { userData: newUser } 
            });
          } else if (role === 'MENTORADO') {
            this.router.navigate(['/register-mentored'], { 
              state: { userData: newUser } 
            });
          }
        }
      )
      .catch(error => {
        console.error('Erro ao registrar usuário:', error);

        // Mensagens específicas com base no status
        if (error.status === 409) {
          this.errorMessage = 'Este e-mail já está em uso.';
        } else if (error.status === 400) {
          this.errorMessage = 'Os dados fornecidos são inválidos.';
        } else {
          this.errorMessage = 'Erro ao se conectar com o servidor. Tente novamente mais tarde.';
        }
      });

    } else {
      console.log('Formulário inválido');
      this.errorMessage = 'Por favor, corrija os erros no formulário.';
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
