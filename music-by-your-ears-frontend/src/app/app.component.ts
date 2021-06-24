import {Component, OnInit} from '@angular/core';
import {IconService} from './services/icon.service';
import {NavigationEnd, Event, Router} from '@angular/router';
import {filter} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'music-by-your-ears';

  constructor(private iconService: IconService, private router: Router) {
    router.events
      .pipe(filter((routerEvent: Event) => routerEvent instanceof NavigationEnd))
      .subscribe(() => window.scrollTo(0, 0));
  }

  ngOnInit(): void {
    this.iconService.registerIcons();
  }
}
