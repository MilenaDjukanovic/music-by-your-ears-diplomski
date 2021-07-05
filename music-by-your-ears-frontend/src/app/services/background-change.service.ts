import { Injectable } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {backgroundImages} from '../configuration/backgroundImages';

@Injectable({
  providedIn: 'root'
})
export class BackgroundChangeService {

  private subject = new Subject<any>();
  private lastSelectedBackground: string | undefined;
  private lastSelectedIndex!: number;

  private backgroundImages = backgroundImages;

  constructor(){}

  public setImage(image: string, index: number): void {
    this.lastSelectedIndex = index;
    this.lastSelectedBackground = image;
    this.subject.next({backgroundImage: image});
  }

  public setDefaultBackground(): void{
    this.setImage(backgroundImages[0].imageUrl, 0);
  }

  public getBackgroundImage(): Observable<any>{
    return this.subject.asObservable();
  }

  public getLastSelectedBackground(): string {
    if (this.lastSelectedBackground) {
      return this.lastSelectedBackground;
    }
    return '';
  }

  public getLastIndex(): number {
    return this.lastSelectedIndex;
  }

  public getBackgroundImages(): any{
    return this.backgroundImages;
  }

  public resetBackground(): void {
    this.lastSelectedBackground = undefined;
  }
}
