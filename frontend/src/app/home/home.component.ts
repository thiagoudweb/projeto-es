import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  template: `
    <style>
      .welcome-message {
        display: block;
        text-align: center;
        margin-top: 2rem;
        font-size: 2rem;
        font-weight: bold;
        font-family: 'Segoe UI', sans-serif;
      }
    </style>
    <p class="welcome-message">Bem vindo!</p>
  `,
  //  styleUrl: './home.css'
})
export class HomeComponent {}
