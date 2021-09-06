import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {SoundService} from '../../../../services/sound.service';
import {IconService} from '../../../../services/icon.service';
import {MatRadioChange} from '@angular/material/radio';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-sound-form',
  templateUrl: './create-sound-form.component.html',
  styleUrls: ['./create-sound-form.component.scss']
})
export class CreateSoundFormComponent implements OnInit {

  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  public createSoundForm!: FormGroup;
  public publicLabel = new FormControl('false');

  public soundPublic = 'false';
  public error!: string;
  public audio!: any;
  public iconImage!: any;
  public soundError = 'Must insert sound name';

  constructor(private formBuilder: FormBuilder, private soundService: SoundService,
              private iconService: IconService, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.createSoundForm = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  public onSubmit(): void {
    if (!this.createSoundForm.valid || !this.audio || !this.iconImage) {
      this.error = 'Form not valid, please try again!';
      return;
    }

    const soundData = new FormData();
    soundData.append('imageFile', this.iconImage, this.iconImage.name);
    soundData.append('audioFile', this.audio, this.audio.name);
    soundData.append('soundPublic', this.soundPublic);
    soundData.append('extension', 'svg');
    soundData.append('nameToShow', this.createSoundForm.controls.name.value);
    this.soundService.createSound(soundData).subscribe((data) => {
      this.clearForm();
      this.openSnackBar('You have successfully created your sound');
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
      this.iconImage = $event.item(0);
    } else {
      this.iconImage = null;
    }
  }

  public setSoundPublic($event: MatRadioChange): void {
    this.soundPublic = $event.value;
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
