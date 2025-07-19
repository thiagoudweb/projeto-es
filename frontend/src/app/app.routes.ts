import { Routes } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { AppComponent } from './app.component';

export const routes: Routes = [
    {
        path: 'register', component: RegisterComponent,
        title: 'Cadastro'
    },
    {
        path: '', component: AppComponent,
        title: 'Plataforma de Mentoria'
    }
];
