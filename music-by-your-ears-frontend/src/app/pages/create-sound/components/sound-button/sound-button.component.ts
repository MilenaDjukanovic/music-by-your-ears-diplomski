import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Sound} from '../../../../model/sound.model';
import {MixSoundsService} from '../../../../services/mix-sounds.service';

@Component({
  selector: 'app-sound-button',
  templateUrl: './sound-button.component.html',
  styleUrls: ['./sound-button.component.scss']
})
export class SoundButtonComponent implements OnInit, OnDestroy {

  @Input() public sound!: Sound;

  public active = false;
  private audio: any = new Audio();

  constructor(private mixSoundService: MixSoundsService) {
  }

  ngOnInit(): void {
    this.audio = this.sound.audio;
    this.audio.loop = true;
    this.audio.load();
  }

  ngOnDestroy(): void {
    this.audio.pause();
    this.audio.removeAttribute('src');
    this.audio =  null;
  }

  public playAudio(): void {
    if (!this.active) {
      this.audio.play();
      this.mixSoundService.addSoundForMixing(this.sound);
    } else  {
      this.audio.pause();
      this.mixSoundService.removeSoundForMixing(this.sound);
    }
    this.active = !this.active;
  }
}
