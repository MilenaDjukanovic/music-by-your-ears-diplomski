import { Injectable } from '@angular/core';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {Icon} from '../model/icons.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {NgxSpinnerService} from 'ngx-spinner';

@Injectable({
  providedIn: 'root'
})
export class IconService {

  private readonly BASE_URL  = 'spring/api/icons';
  constructor(private matIconRegistry: MatIconRegistry,
              private domSanitizer: DomSanitizer, private httpClient: HttpClient,
              private spinner: NgxSpinnerService) { }

  public registerIcons(): void {
    this.spinner.show();
    this.getIcons().subscribe((data) => {
      this.loadIcons(data.content);
      this.spinner.hide();
    });
  }

  private loadIcons(icons: Array<Icon>): void {
    for (const icon of icons) {
      const objectURL = 'data:image/svg;base64,' + icon.image;
      this.matIconRegistry.addSvgIcon(icon.name, this.domSanitizer.bypassSecurityTrustResourceUrl(objectURL));
    }
  }

  public create(icon: FormData): Observable < any > {
    const url = this.BASE_URL + '/upload';
    return this.httpClient.post(url, icon, {observe: 'response'});
  }

  public getIcons(): Observable < any > {
    const url = this.BASE_URL  + '?extension=svg';
    return this.httpClient.get(url);
  }

}
