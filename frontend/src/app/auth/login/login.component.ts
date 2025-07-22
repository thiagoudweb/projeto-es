import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);
  errorMessage: string | null = null;

  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
  });

  onSubmit() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService
        .login(email, password)
        .then((user) => {
          console.log('Login successful:', user);
          this.router.navigate(['/home']);
        })
        .catch((err) => {
          console.error('Erro ao fazer login:', err);

          // Handle different HTTP status codes
          if (err.status === 403) {
            this.errorMessage = 'Acesso negado. Verifique suas credenciais.';
          } else if (err.status === 401) {
            this.errorMessage = 'Email ou senha inválidos';
          } else if (err.status === 0) {
            this.errorMessage = 'Erro de conexão com o servidor';
          } else {
            this.errorMessage =
              'Erro ao tentar fazer login. Tente novamente mais tarde.';
          }

          console.log('Status do erro:', err.status);
          console.log('Mensagem de erro:', this.errorMessage);
        });
    }
  }
}
