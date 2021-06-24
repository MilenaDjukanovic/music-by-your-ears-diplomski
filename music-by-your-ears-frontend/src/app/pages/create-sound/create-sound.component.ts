import { Component, OnInit } from '@angular/core';
import {defaultSoundConfiguration} from '../../configuration/defaultSoundConfiguration';
import {BackgroundChangeService} from '../../services/background-change.service';

@Component({
  selector: 'app-create-sound',
  templateUrl: './create-sound.component.html',
  styleUrls: ['./create-sound.component.scss']
})
export class CreateSoundComponent implements OnInit {

  public soundConfiguration = defaultSoundConfiguration;

  constructor() {
  }

  ngOnInit(): void {
    window.scroll(0, 0);
  }
}
