import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CreateSound} from '../model/sound.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SoundService {

  private readonly BASE_URL: string = 'spring/api/sounds';
  constructor(private httpClient: HttpClient) { }

  public createSound(soundData: FormData): Observable<any> {
    return this.httpClient.post(this.BASE_URL, soundData);
  }

  public getSounds(): Observable<any> {
    return this.httpClient.get(this.BASE_URL);
  }
}
