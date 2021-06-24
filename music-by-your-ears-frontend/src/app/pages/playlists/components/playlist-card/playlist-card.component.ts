import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {MatSliderChange, MatSliderModule} from '@angular/material/slider';
import {MatDialog} from '@angular/material/dialog';
import {ButtonDialogComponent} from '../button-dialog/button-dialog.component';
import {PlaylistService} from '../../../../services/playlist.service';

export interface DialogData {
  icons: any;
}

@Component({
  selector: 'app-playlist-card',
  templateUrl: './playlist-card.component.html',
  styleUrls: ['./playlist-card.component.scss']
})
export class PlaylistCardComponent implements OnInit, OnDestroy {

  @Input() title!: string;
  @Input() artist!: string;
  @Input() image!: string;
  @Input() sound!: string;
  @Input() icons!: any;

  public audio = new Audio();

  public currentTimePlayed = 0;

  constructor(public dialog: MatDialog, private playlistService: PlaylistService) {
    this.playlistService.pauseVideo.subscribe(() => {
      this.audio.pause();
    });
  }

  ngOnInit(): void {
    this.initializeAudio();
  }

  private initializeAudio(): void {
    this.audio.src = this.sound;
    this.audio.load();

    this.audio.addEventListener('timeupdate', () => {
      this.currentTimePlayed = this.audio.currentTime;
    });
  }

  public playAudio(): void {
    this.playlistService.onPlayVideo();
    this.audio.play();
  }

  public pauseAudio(): void {
    this.audio.pause();
  }

  public formatLabel(value: number): number {
    return Math.round((value + Number.EPSILON) * 100) / 100;
  }

  public onInputChanged(event: MatSliderChange): void {
    if (typeof event.value === 'number') {
      this.audio.currentTime = event.value;
    }
  }

  public openDialog(): void {
    this.dialog.open(ButtonDialogComponent, {
      maxHeight: '400px',
      width: '400px',
      data: {icons: this.icons}
    });

  }

  ngOnDestroy(): void {
    this.audio.pause();
    this.audio.removeAttribute('src');
  }
}
