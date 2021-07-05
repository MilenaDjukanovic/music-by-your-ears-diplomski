import { Component, OnInit } from '@angular/core';
import {SoundService} from '../../services/sound.service';
import {ISound} from '../../model/sound.model';

@Component({
  selector: 'app-create-sound',
  templateUrl: './create-sound.component.html',
  styleUrls: ['./create-sound.component.scss']
})
export class CreateSoundComponent implements OnInit {

  public soundConfiguration: Array<ISound> = new Array<ISound>();

  constructor(private soundService: SoundService) {
  }

  ngOnInit(): void {
    this.soundService.getSounds().subscribe(
      (data) => {
        this.soundConfiguration = data.content;
        this.loadSounds();
      }, error => {

      }
    );
  }

  private async loadSounds(): Promise<void>{
    for (const sound of this.soundConfiguration) {
      if (sound.name.split('.')[1] === 'mp3') {
        sound.audio = new Audio('data:audio/mp3;base64,' + sound.audio);
      }

      if (sound.name.split('.')[1] === 'wav') {
        sound.audio = new Audio('data:audio/wav;base64,' + sound.audio);
      }
   }
  }

}
