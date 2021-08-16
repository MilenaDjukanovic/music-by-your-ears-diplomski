import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {PlaylistService} from '../../../../services/playlist.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-playlist-form',
  templateUrl: './create-playlist-form.component.html',
  styleUrls: ['./create-playlist-form.component.scss']
})
export class CreatePlaylistFormComponent implements OnInit {

  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  public createPlaylistForm!: FormGroup;

  public soundPublic = 'false';
  public error!: string;
  public audio!: any;
  public coverImage!: any;
  public artistError = 'Must insert artist';
  public playlistError = 'Must insert playlist name';

  constructor(private formBuilder: FormBuilder, private playlistService: PlaylistService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.createPlaylistForm = this.formBuilder.group({
      name: ['', Validators.required],
      artist: ['', Validators.required]
    });
  }

  public onSubmit(): void {
    if (!this.createPlaylistForm.valid || !this.audio || !this.coverImage) {
      this.error = 'Form not valid, please try again!';
      return;
    }

    const formValues = this.createPlaylistForm.value;
    const playlistData = new FormData();
    playlistData.append('imageFile', this.coverImage, this.coverImage.name);
    playlistData.append('audioFile', this.audio, this.audio.name);
    playlistData.append('extension', 'png');
    playlistData.append('artist', formValues.artist);
    playlistData.append('nameToShow', formValues.name);

    this.playlistService.createPlaylist(playlistData).subscribe((data) => {
      this.clearForm();
      this.openSnackBar('You have successfully created new playlist!');
    }, error => {
      this.openSnackBar(error.message);
    });
  }

  public onAudioUpload($event: FileList): void {
    if ($event) {
      this.audio = $event.item(0);
    } else {
      this.audio = null;
    }
  }

  public onIconImageUpload($event: FileList): void {
    if ($event) {
      this.coverImage = $event.item(0);
    } else {
      this.coverImage = null;
    }
  }

  private clearForm(): void {
    setTimeout(() => {
      this.formGroupDirective?.resetForm();
    }, 0);
  }

  private openSnackBar(message: string): void {
    this.snackBar.open(message, '', {
      duration: 2000,
      verticalPosition: 'top',
      panelClass: ['primary']
    });
  }
}
