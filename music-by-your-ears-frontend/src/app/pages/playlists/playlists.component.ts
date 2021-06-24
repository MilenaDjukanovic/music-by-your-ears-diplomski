import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {defaultPlaylistConfiguration} from '../../configuration/defaultPlaylistConfiguration';
import {ViewportScroller} from '@angular/common';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.scss']
})
export class PlaylistsComponent implements OnInit{

  public playlistConfiguration = defaultPlaylistConfiguration;

  constructor() { }

  ngOnInit(): void {
  }

}
