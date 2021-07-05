import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {PlaylistService} from '../../../../services/playlist.service';

@Component({
  selector: 'app-create-playlist-form',
  templateUrl: './create-playlist-form.component.html',
  styleUrls: ['./create-playlist-form.component.scss']
})
export class CreatePlaylistFormComponent implements OnInit {

  public createPlaylistForm!: FormGroup;

  public soundPublic = 'false';
  public error!: string;
  public audio!: any;
  public coverImage!: any;
  public artistError = 'Must insert artist';
  public playlistError = 'Must insert playlist name';

  constructor(private formBuilder: FormBuilder, private playlistService: PlaylistService) { }

  ngOnInit(): void {
    this.createPlaylistForm = this.formBuilder.group({
      name: ['', Validators.required],
      artist: ['', Validators.required]
    });
  }

  public onSubmit(formDirective: FormGroupDirective): void {
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
          this.createPlaylistForm.reset('');
          formDirective.reset();
    });
  }

  public onAudioUpload($event: FileList): void {
      this.audio = $event.item(0);
  }

  public onIconImageUpload($event: FileList): void {
      this.coverImage = $event.item(0);
  }
}
