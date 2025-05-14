import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Theme {
  id: number;
  title: string;
  description: string;
  subscribed?: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private apiUrl = `${environment.apiUrl}/api/themes`;

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('auth_token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      ...(token ? { 'Authorization': `Bearer ${token}` } : {})
    });
  }

  getAllThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.apiUrl, {
      headers: this.getHeaders()
    });
  }

  subscribeToTheme(themeId: number): Observable<boolean> {
    return this.http.post<boolean>(
      `${this.apiUrl}/subscribe/${themeId}`,
      {},
      { headers: this.getHeaders() }
    );
  }

  unsubscribeFromTheme(themeId: number): Observable<boolean> {
    return this.http.post<boolean>(
      `${this.apiUrl}/unsubscribe/${themeId}`,
      {},
      { headers: this.getHeaders() }
    );
  }

  getUserSubscriptions(): Observable<Theme[]> {
    return this.http.get<Theme[]>(
      `${this.apiUrl}/subscriptions`,
      { headers: this.getHeaders() }
    );
  }

  getThemeById(id: number): Observable<Theme> {
    return this.http.get<Theme>(
      `${this.apiUrl}/${id}`,
      { headers: this.getHeaders() }
    );
  }
}
