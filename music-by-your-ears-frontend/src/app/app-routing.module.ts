import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HeaderComponent} from './components/common/header/header.component';
import {HomeComponent} from './pages/home/home.component';
import {CreateSoundComponent} from './pages/create-sound/create-sound.component';
import {PlaylistsComponent} from './pages/playlists/playlists.component';

const routes: Routes = [
  {path: '', component:  HeaderComponent, children: [
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      {path: 'home', component: HomeComponent},
      {path: 'create-sound', component: CreateSoundComponent},
      {path: 'playlists', component: PlaylistsComponent}
    ]},
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    scrollPositionRestoration: 'enabled',
    anchorScrolling: 'enabled'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
