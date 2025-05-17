import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../core/services/api.service';

export interface UserProfile {
  username: string;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseEndpoint = 'api/user';

  constructor(private api: ApiService) {}

  getProfile(): Observable<UserProfile> {
    return this.api.get<UserProfile>(this.baseEndpoint);
  }

  updateProfile(profile: UserProfile): Observable<boolean> {
    return this.api.put<boolean>(this.baseEndpoint, profile);
  }
}
