import { Injectable } from '@angular/core';
import {defaultPlaylistConfiguration} from '../configuration/defaultPlaylistConfiguration';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  public pauseVideo: Subject<any> = new Subject<any>();

  constructor() { }

  public onPlayVideo(): void {
    this.pauseVideo.next();
  }

}
