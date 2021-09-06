import {Component, OnInit} from '@angular/core';
import {PlaylistService} from '../../services/playlist.service';
import {AuthService} from '../../services/auth.service';
import {IPlaylist, Playlist} from '../../model/playlist.model';
import {ICreateUser, IUser, User} from '../../model/user.model';
import {NgxSpinnerService} from 'ngx-spinner';
import {Pageable} from '../../model/requests';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  public playlists: Array<IPlaylist> = new Array<IPlaylist>();
  public user!: ICreateUser;

  public showSeeMore = false;
  private page = 0;

  constructor(private playlistService: PlaylistService, private authService: AuthService,
              private spinner: NgxSpinnerService) {
  }

  ngOnInit(): void {
    this.getPlaylistsForUser();
  }

  public refreshView(): void {
    this.getPlaylistsForUser();
  }

  public getPlaylistsForUser(): void {
    this.spinner.show();
    const page = new Pageable(this.page, 4);
    this.playlistService.getPlaylistsForLoggedInUser(this.authService.getCurrentUserValue().id, page).subscribe((data) => {
      if (this.playlists.length === 0) {
        this.playlists = data.content;
      } else {
        data.content.forEach((playlist: Playlist) => {
          this.playlists.push(playlist);
        });
      }
      this.showSeeMore = data.pageable.pageNumber < data.totalPages - 1;
      this.page++;
      this.spinner.hide();
    });
  }
}
