import { Component, OnInit } from '@angular/core';
import { ArticlesService, Article } from '../../services/article.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];
  sortAscending = true; // Par défaut, tri ascendant

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

  // Fonction pour trier les articles
  toggleSort() {
    this.sortAscending = !this.sortAscending;
    this.sortArticles();
  }

  // Fonction qui applique le tri sur les articles
  sortArticles() {
    this.articles.sort((a, b) => {
      const comparison = this.sortAscending ? 1 : -1;
      return a.date.localeCompare(b.date) * comparison;
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
