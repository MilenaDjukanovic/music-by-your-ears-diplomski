import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DialogData} from '../playlist-card/playlist-card.component';

@Component({
  selector: 'app-button-dialog',
  templateUrl: './button-dialog.component.html',
  styleUrls: ['./button-dialog.component.scss']
})
export class ButtonDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ButtonDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

  ngOnInit(): void {
  }

  public onNoClick(): void {
    this.dialogRef.close();
  }

}
