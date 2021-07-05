import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SoundService} from '../../../../services/sound.service';
import {IconService} from '../../../../services/icon.service';
import {MatRadioChange} from '@angular/material/radio';
import {$e} from 'codelyzer/angular/styles/chars';

@Component({
  selector: 'app-create-sound-form',
  templateUrl: './create-sound-form.component.html',
  styleUrls: ['./create-sound-form.component.scss']
})
export class CreateSoundFormComponent implements OnInit {

  public createSoundForm!: FormGroup;
  public publicLabel = new FormControl('false');

  public soundPublic = 'false';
  public error!: string;
  public audio!: any;
  public iconImage!: any;
  public soundError = 'Must insert sound name';

  constructor(private formBuilder: FormBuilder, private soundService: SoundService, private iconService: IconService) {}

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
    soundData.append('audioFile', this.audio, this.audio.name );
    soundData.append('soundPublic', this.soundPublic);
    soundData.append('extension', 'svg');
    soundData.append('nameToShow', this.createSoundForm.controls.name.value);
    this.soundService.createSound(soundData).subscribe((data) => {
      this.createSoundForm.reset('');
      this.createSoundForm.clearValidators();
    }, error => {});
  }

  public onAudioUpload($event: FileList): void{
    this.audio = $event.item(0);
  }

  public onIconImageUpload($event: FileList): void {
    this.iconImage = $event.item(0);
  }

  public setSoundPublic($event: MatRadioChange): void {
    this.soundPublic = $event.value;
  }
}
