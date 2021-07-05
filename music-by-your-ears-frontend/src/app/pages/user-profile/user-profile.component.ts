import { Component, OnInit } from '@angular/core';
import {PlaylistService} from '../../services/playlist.service';
import {AuthService} from '../../services/auth.service';
import {IPlaylist} from '../../model/playlist.model';
import {User} from '../../model/user.model';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  public playlists: Array<IPlaylist> = new Array<IPlaylist>();

  constructor(private playlistService: PlaylistService, private authService: AuthService) { }

  ngOnInit(): void {
    this.getPlaylistsForUser();
  }

  public refreshView(): void {
    this.getPlaylistsForUser();
  }

  private getPlaylistsForUser(): void {
    debugger
    this.playlistService.getPlaylistsForLoggedInUser(this.authService.getCurrentUserValue().id).subscribe((data) => {
        debugger
        this.playlists = data.content;
      });
  }
}
