// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { HomeComponent } from './home/home';
import { UnauthorizedComponent } from './auth/unauthorized/unauthorized';
import { RegisterComponent } from './auth/register/register.component';

export const routes: Routes = [
  { path: '', component: HomeComponent, title: 'Plataforma de Mentoria' },
  { path: 'register', component: RegisterComponent, title: 'Cadastro' },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: '' }
];
