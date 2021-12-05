import {Component, OnInit} from '@angular/core';
import {IPlaylist} from '../../model/playlist.model';
import {PlaylistService} from '../../services/playlist.service';
import {NgxSpinnerService} from 'ngx-spinner';
import {Pageable} from '../../model/requests';
import {DownloadService} from '../../services/download.service';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.scss']
})
export class PlaylistsComponent implements OnInit {

  public playlistConfiguration: Array<IPlaylist> = new Array<IPlaylist>();
  private page = 0;
  public showSeeMore = false;

  constructor(private playlistService: PlaylistService, private spinner: NgxSpinnerService,
              private downloadService: DownloadService) {
  }

  ngOnInit(): void {
    this.getPlaylists();
  }

  public onPlaylistDownload($event: any): void {
    this.downloadService.downloadData($event);
  }

  public onDeletePlaylist(): void {
    this.getPlaylists();
  }

  public getPlaylists(): void {
    this.spinner.show();

    const page = new Pageable(this.page, 4);
    this.playlistService.getPlaylists(page).subscribe(
      (data) => {
        this.playlistConfiguration = this.playlistService.mergePlaylists(this.playlistConfiguration, data.content);
        this.showSeeMore = data.pageable.pageNumber < data.totalPages - 1;
        this.page++;
        this.spinner.hide();
      }
    );
  }
}
