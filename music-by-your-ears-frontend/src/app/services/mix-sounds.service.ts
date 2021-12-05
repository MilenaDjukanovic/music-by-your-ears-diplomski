import { Injectable } from '@angular/core';
import {Sound} from '../model/sound.model';

@Injectable({
  providedIn: 'root'
})
export class MixSoundsService {

  private soundsToMix: number[] = [];

  constructor() { }

  public addSoundForMixing(sound: Sound): void {
    this.soundsToMix.push(sound.id);
  }

  public removeSoundForMixing(sound: Sound): void {
    let index = -1;
    for (let i = 0; i < this.soundsToMix.length; i++) {
      if (this.soundsToMix[i] === sound.id) {
        index = i;
        break;
      }
    }
    if (index !== -1){
      this.soundsToMix.splice(index, 1);
    }
  }

  public getSoundsToMix(): number[] {
    return this.soundsToMix;
  }
}
