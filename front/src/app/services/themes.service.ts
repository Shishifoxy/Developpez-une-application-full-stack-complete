import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../core/services/api.service';

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
  private baseEndpoint = 'api/themes';

  constructor(private api: ApiService) {}

  getAllThemes(): Observable<Theme[]> {
    return this.api.get<Theme[]>(this.baseEndpoint);
  }

  subscribeToTheme(themeId: number): Observable<boolean> {
    return this.api.post<boolean>(`${this.baseEndpoint}/subscribe/${themeId}`, {});
  }

  unsubscribeFromTheme(themeId: number): Observable<boolean> {
    return this.api.post<boolean>(`${this.baseEndpoint}/unsubscribe/${themeId}`, {});
  }

  getUserSubscriptions(): Observable<Theme[]> {
    return this.api.get<Theme[]>(`${this.baseEndpoint}/subscriptions`);
  }

  getThemeById(id: number): Observable<Theme> {
    return this.api.get<Theme>(`${this.baseEndpoint}/${id}`);
  }
}
