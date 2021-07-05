import {Component, Input, OnDestroy, OnInit} from '@angular/core';

@Component({
  selector: 'app-sound-button',
  templateUrl: './sound-button.component.html',
  styleUrls: ['./sound-button.component.scss']
})
export class SoundButtonComponent implements OnInit, OnDestroy {

  @Input() public title!: string;
  @Input() public soundUrl!: string;
  @Input() public icon!: string;

  public active = false;
  private audio: any = new Audio();

  constructor() {
  }

  ngOnInit(): void {
    this.audio = this.soundUrl;
    // this.audio.src = this.soundUrl;
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
