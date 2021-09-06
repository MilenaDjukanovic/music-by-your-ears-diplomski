import {Component, HostListener, Inject, OnInit} from '@angular/core';
import {IPlaylist, Playlist} from '../../model/playlist.model';
import {PlaylistService} from '../../services/playlist.service';
import {base64StringToBlob} from 'blob-util';
import {NgxSpinnerService} from 'ngx-spinner';
import {Pageable} from '../../model/requests';
import {WindowManagementService} from '../../services/window-management.service';

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
              private windowManagementService: WindowManagementService) {
  }

  ngOnInit(): void {
    this.getPlaylists();
  }

  public onPlaylistDownload($event: any): void {
    const playlist = $event;

    const blob = base64StringToBlob(playlist.audio, playlist.audioFile);
    const anchor = document.createElement('a');
    anchor.download = playlist.name;
    anchor.href = (window.webkitURL || window.URL).createObjectURL(blob);
    anchor.click();
    anchor.remove();
  }

  public onDeletePlaylist(): void {
    this.getPlaylists();
  }

  public getPlaylists(): void {
    this.spinner.show();

    const page = new Pageable(this.page, 4);
    this.playlistService.getPlaylists2(page).subscribe(
      (data) => {
        if (this.playlistConfiguration.length === 0) {
          this.playlistConfiguration = data.content;
        } else {
          data.content.forEach((playlist: Playlist) => {
            this.playlistConfiguration.push(playlist);
          });
        }
        this.showSeeMore = data.pageable.pageNumber < data.totalPages - 1;
        this.page++;
        this.spinner.hide();
      }
    );
  }
}
