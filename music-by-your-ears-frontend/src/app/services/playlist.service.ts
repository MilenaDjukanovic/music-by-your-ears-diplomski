import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  private readonly BASE_URL: string = 'spring/api/playlists';

  public pauseVideo: Subject<any> = new Subject<any>();

  constructor(private httpClient: HttpClient) { }

  public onPlayVideo(): void {
    this.pauseVideo.next();
  }

  public createPlaylist(playlistData: FormData): Observable<any> {
    return this.httpClient.post(this.BASE_URL, playlistData);
  }

  public getPlaylists(): Observable<any> {
    const getUrl = this.BASE_URL + '/all';
    return this.httpClient.get(getUrl);
  }

  public getPlaylistsForLoggedInUser(userId: number): Observable<any> {
    const url = this.BASE_URL + '/' + userId;
    return this.httpClient.get(url);
  }

  public deletePlaylist(playlistId: number): Observable<any> {
    const deleteUrl = this.BASE_URL + '/delete/' + playlistId;
    return this.httpClient.delete(deleteUrl);
  }
}
