import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { ApiService } from '../core/services/api.service';

export interface Article {
  id: number;
  title: string;
  content: string;
  author: {
    id: number;
    username: string;
  };
  date: string;
  theme: number;
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
  private baseEndpoint = 'api/articles';

  constructor(private api: ApiService) {}

  getAllArticles(): Observable<Article[]> {
    return this.api.get<Article[]>(this.baseEndpoint).pipe(
      map((articles: any[]) => articles.map(article => ({
        ...article,
        createdAt: article.createdAt || new Date().toISOString(),
        author: article.author || { username: 'Auteur inconnu' },
        theme: article.theme || { title: 'Thème inconnu' }
      })))
    );
  }

  createArticle(articleData: { title: string; content: string; theme: string }): Observable<Article> {
    return this.api.post<Article>(this.baseEndpoint, articleData);
  }

  getArticleById(id: number): Observable<Article> {
    return this.api.get<Article>(`${this.baseEndpoint}/${id}`);
  }
}
