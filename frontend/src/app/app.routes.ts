import { Routes } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from "./auth/login/login.component";
import { HomeComponent } from "./home/home.component";
import { authGuard } from './auth/auth-guard';
import { AppComponent } from './app.component';

export const routes: Routes = [
    {
        path: 'register', component: RegisterComponent, title: 'Cadastro'
    },
    {
        path: '', redirectTo: 'login', pathMatch: 'full'
    },
    {
        path: 'login', component: LoginComponent, title: 'Login'
    },
    {
        path: 'home', component: HomeComponent, title: 'Home Page', canActivate: [authGuard]

    },
    {
        path: '**', redirectTo: 'login'
    }
];
