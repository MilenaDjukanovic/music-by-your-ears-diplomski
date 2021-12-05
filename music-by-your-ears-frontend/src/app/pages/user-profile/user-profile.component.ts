import {Component, OnInit} from '@angular/core';
import {PlaylistService} from '../../services/playlist.service';
import {AuthService} from '../../services/auth.service';
import {IPlaylist} from '../../model/playlist.model';
import {ICreateUser} from '../../model/user.model';
import {NgxSpinnerService} from 'ngx-spinner';
import {Pageable} from '../../model/requests';

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
  private pageSize = 4;

  constructor(private playlistService: PlaylistService, private authService: AuthService,
              private spinner: NgxSpinnerService) {
  }

  ngOnInit(): void {
    this.getPlaylistsForUser();
  }

  public refreshView(): void {
    this.playlists = [];
    const page = new Pageable(0, this.pageSize * (this.page + 1));
    this.getPlaylistsForUser(page);
  }

  public getPlaylistsForUser(page?: Pageable): void {
    this.spinner.show();
    if (!page) {
      page = new Pageable(this.page, this.pageSize);
    }
    this.playlistService.getPlaylistsForLoggedInUser(this.authService.getCurrentUserValue().id, page).subscribe((data) => {
      this.playlists = this.playlistService.mergePlaylists(this.playlists, data.content);
      this.showSeeMore = data.pageable.pageNumber < data.totalPages - 1;
      this.page++;
      this.spinner.hide();
    });
  }
}
