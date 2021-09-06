import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WindowManagementService {

  public scrollToTheTop: Subject<any> = new Subject<any>();

  constructor() { }

  public onScrollToTheTop(): void {
    this.scrollToTheTop.next();
  }
}
