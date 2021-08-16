import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CreateReview, IReview} from '../model/reviews.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private readonly BASE_URL = 'spring/api/review';

  constructor(private httpClient: HttpClient) { }

  public getReviewsForPlaylist(playlistId: number): Observable<any> {
    const url = this.BASE_URL + '/' + playlistId;
    return this.httpClient.get(url);
  }

  public createReview(createReview: CreateReview): Observable<IReview> {
    return this.httpClient.post<IReview>(this.BASE_URL, createReview);
  }

  public deleteReview(reviewId: number): Observable<any> {
    const deleteUrl = this.BASE_URL + '/delete/' + reviewId;
    return this.httpClient.delete(deleteUrl);
  }
}
