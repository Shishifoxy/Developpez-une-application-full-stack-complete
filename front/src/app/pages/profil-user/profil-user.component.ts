import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ThemeService, Theme } from '../../services/themes.service';
import { UserService, UserProfile } from '../../services/user.service';

@Component({
  selector: 'app-profil-user',
  templateUrl: './profil-user.component.html',
  styleUrls: ['./profil-user.component.scss']
})
export class ProfilUserComponent implements OnInit {
  profileForm: FormGroup;
  subscriptions: Theme[] = [];

  constructor(
    private fb: FormBuilder,
    private themesService: ThemeService,
    private userService: UserService
  ) {
    this.profileForm = this.fb.group({
      username: [''],
      email: [''],
      password: [''],
    });
  }

  ngOnInit(): void {
    this.loadProfile();
    this.loadSubscriptions();
  }

  loadProfile(): void {
    this.userService.getProfile().subscribe((profile: UserProfile) => {
      this.profileForm.patchValue(profile);
    });
  }

  loadSubscriptions(): void {
    this.themesService.getUserSubscriptions().subscribe((themes: Theme[]) => {
      this.subscriptions = themes;
    });
  }

  saveProfile(): void {
    if (this.profileForm.valid) {
      this.userService.updateProfile(this.profileForm.value).subscribe((success) => {
        if (success) {
          console.log('Profil mis à jour !');
        }
      });
    }
  }

  unsubscribe(theme: Theme): void {
    this.themesService.unsubscribeFromTheme(theme.id).subscribe(() => {
      this.subscriptions = this.subscriptions.filter(t => t.id !== theme.id);
    });
  }
}
