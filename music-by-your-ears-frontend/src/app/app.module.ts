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
import { ButtonDialogComponent } from './pages/playlists/components/button-dialog/button-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {HttpClientModule} from '@angular/common/http';
import {CommentCardComponent} from './pages/home/components/comment-card/comment-card.component';
import {FlipCardComponent} from './pages/home/components/flip-card/flip-card.component';

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
    ButtonDialogComponent,
    CommentCardComponent,
    FlipCardComponent
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
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
