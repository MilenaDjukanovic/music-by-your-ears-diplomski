import { Injectable } from '@angular/core';
import {Sound} from '../model/sound.model';
import {Playlist} from '../model/playlist.model';
import {Icon} from '../model/icons.model';

@Injectable({
  providedIn: 'root'
})
export class FileConverterService {

  constructor() { }

  public convertAudio(audio: Sound | Playlist): HTMLAudioElement {
    const soundParts = audio.name.split('.');
    const soundExtension = soundParts[soundParts.length - 1];
    return  new Audio('data:audio/' + soundExtension + ';base64,' + audio.audio);
  }

  public convertImages(image: Icon): string {
    const imageNameParts = image.name.split('.');
    const imageExtension = imageNameParts[imageNameParts.length - 1];
    return  'data:image/' + imageExtension + ';base64,' + image.image;

  }
}
