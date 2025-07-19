import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  template: `
    <section>
      <div class="login-container">
        <h2>Login</h2>
        <form [formGroup]="loginForm" (ngSubmit)="onSubmit()">
          <label>Email</label>
          <input type="email" formControlName="email" placeholder="Digite seu email" />
           <div *ngIf="loginForm.get('email')?.touched && loginForm.get('email')?.errors" class="error">
              <div *ngIf="loginForm.get('email')?.hasError('required')">O e-mail é obrigatório.</div>
              <div *ngIf="loginForm.get('email')?.hasError('email')">Por favor, insira um e-mail válido.</div>
           </div>

          <label>Password</label>
          <input type="password" formControlName="password" placeholder="Digite sua senha" />
           <div *ngIf="loginForm.get('password')?.touched && loginForm.get('password')?.errors" class="error">
              <div *ngIf="loginForm.get('password')?.hasError('required')">A senha é obrigatória.</div>
           </div>

          <button type="submit" [disabled]="loginForm.invalid">Login</button>
        </form>
        <p>No account?  <a [routerLink]="['/register']">Register</a></p>
      </div>
    </section>
  `,
 // styleUrls: ['./login.component.css']
})

export class LoginComponent {
  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required]
  });

  onSubmit() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password)
      .then(user => this.router.navigate(['/home']))
      .catch(err => alert('Login error: ' + err));
    }
  }
}
