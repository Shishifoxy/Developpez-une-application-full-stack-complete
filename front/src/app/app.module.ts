import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthLandingComponent } from './pages/auth-landing/auth-landing.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import { NavbarComponent } from './shared/navbar/navbar.component';
import {MatDividerModule} from "@angular/material/divider";
import { ArticlesComponent } from './pages/articles/articles.component';
import {MatCardModule} from "@angular/material/card";
import { NewArticleComponent } from './pages/new-article/new-article.component';
import {MatSelectModule} from "@angular/material/select";
import { ThemesComponent } from './pages/themes/themes.component';
import { ArticleDetailComponent } from './pages/article-detail/article-detail.component';
import { ProfilUserComponent } from './pages/profil-user/profil-user.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatMenuModule} from "@angular/material/menu";
import {HttpClientModule} from "@angular/common/http";
import localeFr from '@angular/common/locales/fr';
import {registerLocaleData} from "@angular/common";



@NgModule({
  declarations: [AppComponent, HomeComponent, AuthLandingComponent, LoginComponent, RegisterComponent, NavbarComponent, ArticlesComponent, NewArticleComponent, ThemesComponent, ArticleDetailComponent, ProfilUserComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDividerModule,
    MatCardModule,
    MatSelectModule,
    FormsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor() {
    registerLocaleData(localeFr, 'fr');
  }
}
