import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {AuthLandingComponent} from "./pages/auth-landing/auth-landing.component";
import {AuthGuard} from "./guards/auth.guard";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ArticlesComponent} from "./pages/articles/articles.component";
import {NewArticleComponent} from "./pages/new-article/new-article.component";
import {ThemesComponent} from "./pages/themes/themes.component";
import {ArticleDetailComponent} from "./pages/article-detail/article-detail.component";
import {ProfilUserComponent} from "./pages/profil-user/profil-user.component";

const routes: Routes = [
  { path: '', redirectTo: 'auth', pathMatch: 'full' },
  { path: 'auth', component: AuthLandingComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'home', component: HomeComponent},
  { path: 'articles', component: ArticlesComponent, canActivate: [AuthGuard] },
  { path: 'new-article', component: NewArticleComponent },
  { path: 'themes', component: ThemesComponent },
  { path: 'articles/:id', component: ArticleDetailComponent },
  { path: 'profile', component: ProfilUserComponent, canActivate: [AuthGuard] }

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
