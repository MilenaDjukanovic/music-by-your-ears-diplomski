import {Component, OnInit} from '@angular/core';
import {BackgroundChangeService} from '../../../../services/background-change.service';

@Component({
  selector: 'app-background-control',
  templateUrl: './background-control.component.html',
  styleUrls: ['./background-control.component.scss']
})
export class BackgroundControlComponent implements OnInit {

  public activeIndex!: number;
  public backgroundImages: any;

  constructor(private backgroundChangeService: BackgroundChangeService) {
  }

  ngOnInit(): void {
    this.backgroundImages = this.backgroundChangeService.getBackgroundImages();
    this.loadInitialBackground();
  }

  private loadInitialBackground(): void {
    if (this.backgroundChangeService.getLastSelectedBackground()) {
      this.activeIndex = this.backgroundChangeService.getLastIndex();
    }
  }

  private setDefaultBackground(): void {
    this.activeIndex = 0;
    this.backgroundChangeService.setImage(this.backgroundImages[0].imageUrl, this.activeIndex);
  }

  public onChangeBackground(index: number, imageUrl: string): void {
    this.activeIndex = index;
    this.backgroundChangeService.setImage(imageUrl, this.activeIndex);
  }

  public isActive(index: number): boolean {
    return this.activeIndex === index;
  }

}
