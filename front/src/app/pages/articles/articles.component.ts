import { Component, OnInit } from '@angular/core';
import { ArticlesService, Article } from '../../services/article.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];

  constructor(private articlesService: ArticlesService) {}

  ngOnInit() {
    this.articlesService.getAllArticles().subscribe({
      next: (articles) => {
        console.log('Réponse complète:', articles);
        this.articles = articles;
        console.log('Articles:', this.articles);
      },
      error: (err) => {
        console.error('Erreur complète:', err);
        if (err.error) {
          console.error('Détails:', err.error);
        }
      }
    });
  }

  formatDate(dateString: string): string {
    return new Date(dateString).toLocaleDateString('fr-FR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric'
    });
  }
}
