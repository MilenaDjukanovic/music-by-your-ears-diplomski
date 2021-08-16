import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';;
import {AuthService} from '../../../../services/auth.service';
import {CreateUser} from '../../../../model/user.model';

@Component({
  selector: 'app-edit-profile-dialog',
  templateUrl: './edit-profile-dialog.component.html',
  styleUrls: ['./edit-profile-dialog.component.scss']
})
export class EditProfileDialogComponent implements OnInit {
  // TODO
  public formConfiguration!: any;

  public error!: string;
  public coverImage!: any;
  public user!: CreateUser;

  constructor(public dialogRef: MatDialogRef<EditProfileDialogComponent>, private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  public close(): void {
    this.dialogRef.close();
  }

  public updateUser(): void {
    if (!this.coverImage) {
      this.error = 'Must upload image in order to save it';
      return;
    }
    const userData = new FormData();
    userData.append('imageFile', this.coverImage, this.coverImage.name);

    this.authService.updateUserImage(userData).subscribe((data) => {
      this.user = data;
    });
  }

  public onIconImageUpload($event: FileList): void {
    if ($event) {
      this.coverImage = $event.item(0);
    } else {
      this.coverImage = null;
    }
  }
}
