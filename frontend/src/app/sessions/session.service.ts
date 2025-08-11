import { Injectable } from '@angular/core';
import { Session } from '../entity/session';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private apiUrl = environment.apiUrl + "/sessions";

  async registerSession(newSession: Session): Promise<Session | null> {
      try {
        const response = await fetch(this.apiUrl, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
          body: JSON.stringify(newSession),
        });
  
        if (!response.ok) {
          const errorData = await response
            .json()
            .catch(() => ({ message: 'Erro desconhecido' }));
          const error = new Error(errorData.message || 'Registro falhou');
          (error as any).status = response.status;
          throw error;
        }
  
        return await response.json();
      } catch (error) {
        console.error('Erro ao registrar Session no SessionService:', error);
        throw error;
      }
    }
}
