import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/common/header/header.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {CreateSoundComponent} from './pages/create-sound/create-sound.component';
import {SoundButtonComponent} from './pages/create-sound/components/sound-button/sound-button.component';
import {HomeComponent} from './pages/home/home.component';
import {PlaylistsComponent} from './pages/playlists/playlists.component';
import {MatCardModule} from '@angular/material/card';
import {BackgroundControlComponent} from './pages/create-sound/components/background-control/background-control.component';
import {PlaylistCardComponent} from './pages/playlists/components/playlist-card/playlist-card.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatSliderModule} from '@angular/material/slider';
import {MatTooltipModule} from '@angular/material/tooltip';
import { CommentsDialogComponent } from './pages/playlists/components/comments-dialog/comments-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {CommentCardComponent} from './pages/home/components/comment-card/comment-card.component';
import {FlipCardComponent} from './pages/home/components/flip-card/flip-card.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFileUploadModule} from 'mat-file-upload';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';

import { BasicLayoutComponent } from './layouts/basic-layout/basic-layout.component';
import {BaseRegisterComponent} from './components/common/base-register/base-register.component';
import {LoginComponent} from './pages/login/login.component';
import {RegisterComponent} from './pages/register/register.component';
import { UploadSoundsComponent } from './pages/upload-sounds/upload-sounds.component';
import {CreateSoundFormComponent} from './pages/upload-sounds/components/create-sound-form/create-sound-form.component';
import {CreatePlaylistFormComponent} from './pages/upload-sounds/components/create-playlist-form/create-playlist-form.component';
import {JwtInterceptor} from './interceptors/jwt.interceptor';
import {ErrorInterceptor} from './interceptors/error.interceptor';
import { UserCardComponent } from './pages/user-profile/components/user-card/user-card.component';
import { EditProfileDialogComponent } from './pages/user-profile/components/edit-profile-dialog/edit-profile-dialog.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CreateSoundComponent,
    SoundButtonComponent,
    HomeComponent,
    PlaylistsComponent,
    BackgroundControlComponent,
    PlaylistCardComponent,
    CommentsDialogComponent,
    CommentCardComponent,
    FlipCardComponent,
    UserProfileComponent,
    CreateSoundFormComponent,
    CreatePlaylistFormComponent,
    BasicLayoutComponent,
    BaseRegisterComponent,
    LoginComponent,
    RegisterComponent,
    UploadSoundsComponent,
    UserCardComponent,
    EditProfileDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    MatCardModule,
    MatListModule,
    MatProgressBarModule,
    MatSliderModule,
    MatTooltipModule,
    MatDialogModule,
    HttpClientModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatFileUploadModule,
    MatInputModule,
    MatRadioModule,
    MatSnackBarModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
              { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
