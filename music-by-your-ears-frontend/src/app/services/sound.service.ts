import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MixSoundsService} from './mix-sounds.service';
import {Sound} from '../model/sound.model';

@Injectable({
  providedIn: 'root'
})
export class SoundService {

  private readonly BASE_URL: string = 'spring/api/sounds';
  constructor(private httpClient: HttpClient, private mixSoundsService: MixSoundsService) { }

  public createSound(soundData: FormData): Observable<any> {
    return this.httpClient.post(this.BASE_URL, soundData);
  }

  public getSounds(): Observable<any> {
    return this.httpClient.get(this.BASE_URL);
  }

  public getMixedSound(): Observable<Sound> {
    const url = this.BASE_URL + '/mix-sounds';
    return this.httpClient.post<Sound>(url, this.mixSoundsService.getSoundsToMix());
  }
}
