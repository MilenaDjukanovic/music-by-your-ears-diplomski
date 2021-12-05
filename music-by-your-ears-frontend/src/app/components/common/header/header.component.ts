import {Component, OnInit, ViewChild} from '@angular/core';
import {BackgroundChangeService} from '../../../services/background-change.service';
import {fromEvent, Observable, Subscription} from 'rxjs';
import {MatSidenavContent} from '@angular/material/sidenav';
import {AuthService} from '../../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  private resizeObservable!: Observable<Event>;
  private resizeSubscription!: Subscription;

  private subscription!: Subscription;
  public backgroundImage!: string;
  public isFullScreen!: boolean;

  @ViewChild('sidenavContent')
  public sideNavContent!: MatSidenavContent;

  constructor(private backgroundChangeService: BackgroundChangeService, private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.subscription = this.backgroundChangeService.getBackgroundImage().subscribe(data => {
      this.backgroundImage = 'url(' + data.backgroundImage + ')';
    });

    if (!this.backgroundChangeService.getLastSelectedBackground()){
      this.backgroundChangeService.setDefaultBackground();
    }

    this.setInitialWidth();
    this.onResize();

  }

  public logout(): void {
    this.authService.logout();
    this.backgroundChangeService.resetBackground();
    this.router.navigate(['/login']);
  }

  public onRouteChange(): void{
  //  this.sideNavContent.getElementRef().nativeElement.scroll(0, 0);
  }

  private setInitialWidth(): void {
    this.isFullScreen = window.innerWidth >= 480;
  }

  private onResize(): void {
    this.resizeObservable = fromEvent(window, 'resize');
    this.resizeSubscription = this.resizeObservable.subscribe(evt => {
      const width = evt.target as Window;
      this.isFullScreen = width.innerWidth > 480;
    });
  }
}

