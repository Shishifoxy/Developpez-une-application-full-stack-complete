import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { ThemeService, Theme } from '../../services/themes.service';
import { ArticlesService } from '../../services/article.service';

@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.scss']
})
export class NewArticleComponent implements OnInit {
  articleForm: FormGroup;
  themes: Theme[] = [];

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private router: Router,
    private themeService: ThemeService,
    private articlesService: ArticlesService
  ) {
    this.articleForm = this.fb.group({
      theme: ['', Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.themeService.getAllThemes().subscribe((themes) => {
      this.themes = themes;
    });
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    if (this.articleForm.valid) {
      const formValue = this.articleForm.value;

      this.articlesService.createArticle({
        title: formValue.title,
        content: formValue.content,
        theme: formValue.theme.id
      }).subscribe({
        next: () => this.router.navigate(['/articles']),
        error: (err) => console.error('Erreur création article:', err)
      });
    }
  }

}
