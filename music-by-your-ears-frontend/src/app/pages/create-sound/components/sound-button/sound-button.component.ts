import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Sound} from '../../../../model/sound.model';

@Component({
  selector: 'app-sound-button',
  templateUrl: './sound-button.component.html',
  styleUrls: ['./sound-button.component.scss']
})
export class SoundButtonComponent implements OnInit, OnDestroy {

  @Input() public sound!: Sound;

  public active = false;
  private audio: any = new Audio();

  constructor() {
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
    !this.active ? this.audio.play() : this.audio.pause();
    this.active = !this.active;
  }
}
