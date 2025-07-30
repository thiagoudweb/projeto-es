import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../../auth/auth.service';

export interface MentoredDTO {
  id: number;
  fullName: string;
  course: string;
  interestAreas: string[];
}

@Injectable({
  providedIn: 'root',
})
export class MentorService {
  private baseUrl = 'http://localhost:8080/mentor'; // Adjust to your backend URL

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  searchMentoredByInterest(interestName: string): Observable<MentoredDTO[]> {
    const headers = this.getHeaders();
    return this.http.get<MentoredDTO[]>(
      `${this.baseUrl}/interests/mentored/${interestName}`,
      { headers }
    );
  }
}
