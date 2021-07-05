import {Component, OnInit} from '@angular/core';
import {IPlaylist, Playlist} from '../../model/playlist.model';
import {PlaylistService} from '../../services/playlist.service';
import {base64StringToBlob} from 'blob-util';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.scss']
})
export class PlaylistsComponent implements OnInit {

  public playlistConfiguration: Array<IPlaylist> = new Array<IPlaylist>();

  constructor(private playlistService: PlaylistService) {
  }

  ngOnInit(): void {
    this.playlistService.getPlaylists().subscribe(
      (data) => {
        this.playlistConfiguration = data.content;
      }, error => {

      }
    );
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
}
