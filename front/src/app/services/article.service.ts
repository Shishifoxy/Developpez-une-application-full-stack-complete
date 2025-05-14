import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {catchError, map, Observable, throwError} from 'rxjs';
import { environment } from '../../environments/environment';

export interface Article {
  id: number;
  title: string;
  content: string;
  author: {
    id: number;
    username: string;
  };
  date: string;
  theme: number ;
  comments?: Comment[];
}

export interface Comment {
  id?: number;
  content: string;
  username: string;
  createdAt?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {
  private apiUrl = `${environment.apiUrl}/api/articles`;

  constructor(private http: HttpClient) {}

private getHeaders(): HttpHeaders {
  const token = localStorage.getItem('auth_token');
  return new HttpHeaders({
    'Content-Type': 'application/json',
    ...(token ? { 'Authorization': `Bearer ${token}` } : {})
  });
}

  getAllArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.apiUrl, {
      headers: this.getHeaders()
    }).pipe(
      map((articles: any[]) => articles.map(article => ({
        ...article,
        createdAt: article.createdAt || new Date().toISOString(),
        author: article.author || { username: 'Auteur inconnu' },
        theme: article.theme || { title: 'Thème inconnu' }
      })))
    );
  }
  addComment(articleId: number, content: string): Observable<Comment> {
    return this.http.post<Comment>(
      `${this.apiUrl}/${articleId}/comments`,
      { content },
      { headers: this.getHeaders() }
    ).pipe(
      map(response => ({
        ...response,
        username: response.username || 'Anonyme'
      }))
    );
  }

  createArticle(articleData: { title: string; content: string; theme: string }): Observable<Article> {
    return this.http.post<Article>(
      this.apiUrl,
      articleData,
      { headers: this.getHeaders() }
    );
  }
getArticleById(id: number): Observable<Article> {
  return this.http.get<Article>(`${this.apiUrl}/${id}`, {
    headers: this.getHeaders()
  });
}

}
