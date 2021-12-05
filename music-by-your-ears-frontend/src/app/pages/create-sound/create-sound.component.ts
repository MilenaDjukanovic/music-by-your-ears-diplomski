import {Component, OnInit} from '@angular/core';
import {SoundService} from '../../services/sound.service';
import {ISound} from '../../model/sound.model';
import {NgxSpinnerService} from 'ngx-spinner';
import {MixSoundsService} from '../../services/mix-sounds.service';
import {DownloadService} from '../../services/download.service';
import {FileConverterService} from '../../services/file-converter.service';

@Component({
  selector: 'app-create-sound',
  templateUrl: './create-sound.component.html',
  styleUrls: ['./create-sound.component.scss']
})
export class CreateSoundComponent implements OnInit {

  public soundConfiguration: Array<ISound> = new Array<ISound>();

  constructor(private soundService: SoundService, private spinner: NgxSpinnerService,
              private mixSoundService: MixSoundsService, private downloadService: DownloadService,
              private fileConverterService: FileConverterService) {
  }

  ngOnInit(): void {
    this.spinner.show();
    this.soundService.getSounds().subscribe(
      (data) => {
        this.soundConfiguration = data.content;
        this.loadSounds();
      }, () => {

      }
    );
  }

  private async loadSounds(): Promise<void> {
    for (const sound of this.soundConfiguration) {
      sound.audio = this.fileConverterService.convertAudio(sound);
    }
    await this.spinner.hide();
  }

  public downloadMix(): void {
    this.soundService.getMixedSound().subscribe((data) => {
        this.downloadService.downloadData(data);
      }
    );
  }

  public showDownloadButton(): boolean {
    return this.mixSoundService.getSoundsToMix().length === 0;
  }

}
