import {Component, OnInit} from '@angular/core';
import {CreateUser, UpdateUser} from '../../../../model/user.model';
import {MatDialog} from '@angular/material/dialog';
import {EditProfileDialogComponent} from '../edit-profile-dialog/edit-profile-dialog.component';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../../services/auth.service';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.scss']
})
export class UserCardComponent implements OnInit {

  public user!: CreateUser;
  public userProfileImage!: string;

  public updateUserForm!: FormGroup;

  public fieldsDisabled = true;
  public error!: string;

  constructor(public dialog: MatDialog, private formBuilder: FormBuilder, private authService: AuthService) {
    this.getLoggedInUser();
  }

  ngOnInit(): void {
  }

  public loadImage(): void {
    if (this.user && this.user.profileImage) {
      if (this.user.profileImage?.image) {
        const imageNameParts = this.user.profileImage.name.split('.');
        const imageExtension = imageNameParts[imageNameParts.length - 1];
        this.userProfileImage = 'data:image/' + imageExtension + ';base64,' + this.user.profileImage.image;
      }
    }else {
      this.userProfileImage = '/assets/defaultProfileImages/profile.jpg';
    }
  }

  public openDialog(): void {
    const dialogRef = this.dialog.open(EditProfileDialogComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe( result => {
      this.user = result;
      this.getLoggedInUser();
    });
  }

  public enableFields(): void {
    this.updateUserForm.enable();
  }

  private getLoggedInUser(): void {
    this.authService.getLoggedInUser().subscribe((data) => {
      this.user = data;
      this.loadImage();
      this.initializeForm();
    });
  }

  private initializeForm(): void {
    this.updateUserForm = this.formBuilder.group({
        firstName: [this.user.firstName, Validators.required],
        lastName: [this.user.lastName, Validators.required],
        about: [this.user.about, Validators.required]
      }
    );
    if (this.fieldsDisabled) {
      this.updateUserForm.disable();
    }
  }

  public updateUser(): void{
    if (!this.updateUserForm.valid) {
      this.error = 'Invalid form. Must input all fields';
      return;
    }
    const userForUpdate: UpdateUser = this.updateUserForm.value;

    this.authService.updateUser(userForUpdate).subscribe((data) => {
      this.user = data;
      this.initializeForm();
    });

  }
}
