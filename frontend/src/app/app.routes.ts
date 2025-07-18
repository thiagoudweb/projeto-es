import { Routes } from '@angular/router';
import { HomeComponent } from './home/home';
import { UnauthorizedComponent } from './auth/unauthorized/unauthorized';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: '' }
];
