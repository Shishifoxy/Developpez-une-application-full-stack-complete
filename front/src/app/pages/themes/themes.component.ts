import { Component, OnInit } from '@angular/core';
import { ThemeService, Theme } from '../../services/themes.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {
  themes: Theme[] = [];

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.loadThemes();
  }

  loadThemes(): void {
    this.themeService.getAllThemes().subscribe(allThemes => {
      this.themeService.getUserSubscriptions().subscribe(userThemes => {
        this.themes = allThemes.map(theme => ({
          ...theme,
          subscribed: userThemes.some(t => t.id === theme.id)
        }));
      });
    });
  }

  subscribe(theme: Theme): void {
    if (!theme.subscribed) {
      this.themeService.subscribeToTheme(theme.id).subscribe(() => {
        theme.subscribed = true;
      });
    }
  }
}
