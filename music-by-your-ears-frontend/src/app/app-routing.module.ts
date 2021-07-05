import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HeaderComponent} from './components/common/header/header.component';
import {HomeComponent} from './pages/home/home.component';
import {CreateSoundComponent} from './pages/create-sound/create-sound.component';
import {PlaylistsComponent} from './pages/playlists/playlists.component';
import {UserProfileComponent} from './pages/user-profile/user-profile.component';
import {BasicLayoutComponent} from './layouts/basic-layout/basic-layout.component';
import {LoginComponent} from './pages/login/login.component';
import {RegisterComponent} from './pages/register/register.component';
import {AuthGuard} from './guards/auth.guard';
import {UploadSoundsComponent} from './pages/upload-sounds/upload-sounds.component';

const routes: Routes = [
  {path: '', component:  HeaderComponent, canActivate: [AuthGuard], children: [
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      {path: 'home', component: HomeComponent},
      {path: 'create-sound', component: CreateSoundComponent},
      {path: 'playlists', component: PlaylistsComponent},
      {path: 'upload-sounds', component: UploadSoundsComponent},
      {path: 'user-profile', component: UserProfileComponent}
    ]},
  {path: '', component: BasicLayoutComponent, children: [
      {path: 'login', component: LoginComponent},
      {path: 'register', component: RegisterComponent},
    ]},
  { path: '**', redirectTo: '/home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    scrollPositionRestoration: 'enabled',
    anchorScrolling: 'enabled'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
