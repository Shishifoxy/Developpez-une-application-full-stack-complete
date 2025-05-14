import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Comment {
  id: number;
  content: string;
  username: string;
  createdAt: string;
}

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private apiUrl = `${environment.apiUrl}/api/comments`;

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('auth_token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      ...(token ? { 'Authorization': `Bearer ${token}` } : {})
    });
  }

  getCommentsByArticle(articleId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(
      `${this.apiUrl}/${articleId}`,
      { headers: this.getHeaders() }
    );
  }

  addComment(articleId: number, content: string): Observable<Comment> {
    return this.http.post<Comment>(
      `${this.apiUrl}/${articleId}`,
      { content },
      { headers: this.getHeaders() }
    );
  }

  deleteComment(commentId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${commentId}`,
      { headers: this.getHeaders() }
    );
  }
}
