import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../core/services/api.service';

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
  private baseEndpoint = 'api/comments';

  constructor(private api: ApiService) {}

  getCommentsByArticle(articleId: number): Observable<Comment[]> {
    return this.api.get<Comment[]>(`${this.baseEndpoint}/${articleId}`);
  }

  addComment(articleId: number, content: string): Observable<Comment> {
    return this.api.post<Comment>(`${this.baseEndpoint}/${articleId}`, { content });
  }

  deleteComment(commentId: number): Observable<void> {
    return this.api.delete<void>(`${this.baseEndpoint}/${commentId}`);
  }
}
