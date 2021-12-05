import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {IPageable} from '../model/requests';
import {Playlist} from '../model/playlist.model';

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

  public getPlaylists(pageable: IPageable): Observable<any> {
    const getUrl = this.BASE_URL + '/all' + '?page=' + pageable.page + '&size=' + pageable.size;
    return this.httpClient.get(getUrl);
  }

  public getPlaylistsForLoggedInUser(userId: number, pageable: IPageable): Observable<any> {
    const url = this.BASE_URL + '/' + userId + '?page=' + pageable.page + '&size=' + pageable.size;
    return this.httpClient.get(url);
  }

  public deletePlaylist(playlistId: number): Observable<any> {
    const deleteUrl = this.BASE_URL + '/delete/' + playlistId;
    return this.httpClient.delete(deleteUrl);
  }

  public mergePlaylists(playlistOne: Playlist[], playlistTwo: Playlist[]): Playlist[] {
    if (playlistOne.length === 0) {
      playlistOne = playlistTwo;
    } else {
      playlistTwo.forEach((playlist: Playlist) => {
        playlistOne.push(playlist);
      });
    }
    return playlistOne;
  }
}
