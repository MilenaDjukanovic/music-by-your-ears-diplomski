import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {MatSliderChange} from '@angular/material/slider';
import {MatDialog} from '@angular/material/dialog';
import {CommentsDialogComponent} from '../comments-dialog/comments-dialog.component';
import {PlaylistService} from '../../../../services/playlist.service';
import {IPlaylist} from '../../../../model/playlist.model';
import {IReview} from '../../../../model/reviews.model';
import {ReviewService} from '../../../../services/review.service';

export interface DialogData {
  playlist: IPlaylist;
  reviews: Array<IReview>;
}

@Component({
  selector: 'app-playlist-card',
  templateUrl: './playlist-card.component.html',
  styleUrls: ['./playlist-card.component.scss']
})
export class PlaylistCardComponent implements OnInit, OnDestroy {

  @Input() playlist!: IPlaylist;
  @Input() showDelete!: boolean;
  @Input() showDownloadButton!: boolean;
  @Output() playlistDeleted: EventEmitter<any> = new EventEmitter<any>();
  @Output() playlistDownloaded: EventEmitter<any> = new EventEmitter<any>();

  public audio: any = new Audio();

  public currentTimePlayed = 0;

  public reviews!: Array<IReview>;

  constructor(public dialog: MatDialog, private playlistService: PlaylistService,
              private reviewsService: ReviewService) {
    this.playlistService.pauseVideo.subscribe(() => {
      this.audio.pause();
    });
  }

  ngOnInit(): void {
    this.initializeAudio();
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

  public onOpenDialog(): void {
    this.reviews = this.getReviews();
    this.openDialog();
  }

  public openDialog(): void {
    this.reviewsService.getReviewsForPlaylist(this.playlist.id).subscribe((data) => {
      this.dialog.open(CommentsDialogComponent, {
        maxHeight: '400px',
        width: '400px',
        data: {playlist: this.playlist, reviews: data.content}
      });
    }, error => {
      return new Array<IReview>();
    });

  }

  ngOnDestroy(): void {
    this.audio.pause();
    this.audio.removeAttribute('src');
  }

  public deletePlaylist(): void {
    this.playlistService.deletePlaylist(this.playlist.id);
    this.playlistDeleted.emit();
  }

  public downloadPlaylist(): void {
    this.playlistDownloaded.emit(this.playlist);
  }

  private getReviews(): any {
    this.reviewsService.getReviewsForPlaylist(this.playlist.id).subscribe((data) => {
      return data.content;
    }, error => {
      return new Array<IReview>();
    });
  }

  private initializeAudio(): void {
    this.loadImages();
    this.loadSounds();
    // this.audio = this.playlist.audio;
    this.audio.load();

    this.audio.addEventListener('timeupdate', () => {
      this.currentTimePlayed = this.audio.currentTime;
    });
  }

  private loadImages(): void {
    if (this.playlist.coverImage.name.split('.')[1] === 'jpg') {
      this.playlist.coverImage.image = 'data:image/jpg;base64,' + this.playlist.coverImage.image;
    }

    if (this.playlist.coverImage.name.split('.')[1] === 'jpeg') {
      this.playlist.coverImage.image = 'data:image/jpeg;base64,' + this.playlist.coverImage.image;
    }
  }

  private loadSounds(): void {
    if (this.playlist.name.split('.')[1] === 'mp3') {
      this.audio = new Audio('data:audio/mp3;base64,' + this.playlist.audio);
    }

    if (this.playlist.name.split('.')[1] === 'wav') {
      this.audio = new Audio('data:audio/wav;base64,' + this.playlist.audio);
    }
  }
}
