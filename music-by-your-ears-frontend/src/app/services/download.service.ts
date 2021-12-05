import { Injectable } from '@angular/core';
import {base64StringToBlob} from 'blob-util';
import {Sound} from '../model/sound.model';
import {Playlist} from '../model/playlist.model';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  constructor() { }

  public downloadData(data: Sound | Playlist): void {
    const blob = base64StringToBlob(data.audio, data.audioFile);
    const anchor = document.createElement('a');
    anchor.download = data.name;
    anchor.href = (window.webkitURL || window.URL).createObjectURL(blob);
    anchor.click();
    anchor.remove();
  }
}
