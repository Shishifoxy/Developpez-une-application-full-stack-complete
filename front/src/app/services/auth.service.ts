import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of, tap } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { HttpHeaders } from '@angular/common/http';

interface LoginPayload {
  identifier: string;
  password: string;
}

interface RegisterPayload {
  username: string;
  email: string;
  password: string;
}

interface AuthResponse {
  token: string;
  user: {
    id: number;
    username: string;
    email: string;
  };
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}`;
  private tokenKey = 'auth_token';
  private authStatus = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient, private router: Router) {}

  login(identifier: string, password: string): Observable<any> {
    return this.http.post<any>(
      `${this.apiUrl}/api/auth/login`,
      { identifier, password }
    ).pipe(
      tap(response => {
        localStorage.setItem(this.tokenKey, response.token);
        this.authStatus.next(true);
      })
    );
  }

  register(data: RegisterPayload): Observable<AuthResponse> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<AuthResponse>(
      `${this.apiUrl}/api/auth/register`,
      data,
      { headers }
    ).pipe(
      tap((res) => {
        localStorage.setItem(this.tokenKey, res.token);
        this.authStatus.next(true);
      })
    );
  }
  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.authStatus.next(false);
    this.router.navigate(['/auth']);
  }

  isAuthenticated(): boolean {
    return this.authStatus.value;
  }

  authStatus$(): Observable<boolean> {
    return this.authStatus.asObservable();
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  private hasToken(): boolean {
    return !!localStorage.getItem(this.tokenKey);
  }
}
