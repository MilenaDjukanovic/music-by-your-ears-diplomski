import {Component, OnInit, ViewChild} from '@angular/core';
import {BackgroundChangeService} from '../../../services/background-change.service';
import {Subscription} from 'rxjs';
import {MatSidenavContent} from '@angular/material/sidenav';
import {AuthService} from '../../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  private subscription!: Subscription;
  public backgroundImage!: string;

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
  }

  public logout(): void {
    this.authService.logout();
    this.backgroundChangeService.resetBackground();
    this.router.navigate(['/login']);
  }

  public onRouteChange(): void{
  //  this.sideNavContent.getElementRef().nativeElement.scroll(0, 0);
  }
}
