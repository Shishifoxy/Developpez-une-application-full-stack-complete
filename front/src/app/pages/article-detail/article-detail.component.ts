import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { ArticlesService, Article } from '../../services/article.service';
import { Theme, ThemeService } from '../../services/themes.service';
import { CommentService } from '../../services/comment.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit {
  article!: Article;
  articleId!: number;
  newComment = '';
  themeName = '';

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private articlesService: ArticlesService,
    private themeService: ThemeService,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    this.articleId = Number(this.route.snapshot.paramMap.get('id'));

    this.articlesService.getArticleById(this.articleId).subscribe(article => {
      this.article = article;
      console.log('Article:', this.article);
      this.themeService.getThemeById(article.theme).subscribe(theme => {
        this.themeName = theme.title;
      });
      this.loadComments();
    });
  }

  loadComments(): void {
    this.commentService.getCommentsByArticle(this.articleId).subscribe(comments => {
      if (this.article) {
        this.article.comments = comments;
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  submitComment(): void {
    const content = this.newComment.trim();
    if (!content || !this.articleId) return;

    this.commentService.addComment(this.articleId, content).subscribe({
      next: (newComment) => {
        if (!this.article.comments) {
          this.article.comments = [];
        }
        this.article.comments.push(newComment);
        this.newComment = '';
      },
      error: (err) => {
        console.error('Erreur:', err);
        alert('Impossible d\'ajouter le commentaire');
      }
    });
  }
}
