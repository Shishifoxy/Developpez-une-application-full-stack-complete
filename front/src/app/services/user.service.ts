import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export interface UserProfile {
  username: string;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = `${environment.apiUrl}/api/user`;
  private tokenKey = 'auth_token';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem(this.tokenKey);
    return new HttpHeaders({
      'Content-Type': 'application/json',
      ...(token ? { 'Authorization': `Bearer ${token}` } : {}),
    });
  }

  getProfile(): Observable<UserProfile> {
    return this.http.get<UserProfile>(this.apiUrl, { headers: this.getHeaders() });
  }

  updateProfile(profile: UserProfile): Observable<boolean> {
    return this.http.put<boolean>(this.apiUrl, profile, { headers: this.getHeaders() });
  }
}
