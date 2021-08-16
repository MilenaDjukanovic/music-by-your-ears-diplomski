import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DialogData} from '../playlist-card/playlist-card.component';
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {CreateReview} from '../../../../model/reviews.model';
import {AuthService} from '../../../../services/auth.service';
import {ReviewService} from '../../../../services/review.service';

@Component({
  selector: 'app-button-dialog',
  templateUrl: './comments-dialog.component.html',
  styleUrls: ['./comments-dialog.component.scss']
})
export class CommentsDialogComponent implements OnInit {

  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  public commentForm!: FormGroup;
  public error!: string;
  public reviews!: any[];

  public showDeleteButton = false;
  public userId!: number;

  constructor(public dialogRef: MatDialogRef<CommentsDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData, private formBuilder: FormBuilder,
              private authService: AuthService, private reviewService: ReviewService) {

    this.reviews = this.data.reviews;
    this.userId = this.authService.getCurrentUserValue().id;
    this.displayDeleteButton();
  }

  ngOnInit(): void {
    this.commentForm = this.formBuilder.group({
      comment: ['', Validators.required]
    });
  }

  public onNoClick(): void {
    this.dialogRef.close();
  }

  public createComment(): void {
    if (this.commentForm.invalid) {
      this.error = 'Can not create comment';
      return;
    }

    const createReview: CreateReview = new CreateReview(this.commentForm.controls.comment.value,
      this.data.playlist.id, this.authService.getCurrentUserValue().id);

    this.reviewService.createReview(createReview).subscribe((data) => {
      this.getReviews();
      this.clearForm();
    }, error => {
    });
  }

  public deleteReview(reviewId: number): void {
    this.reviewService.deleteReview(reviewId).subscribe(data => {
      this.getReviews();
    });
  }

  private getReviews(): void {
    this.reviewService.getReviewsForPlaylist(this.data.playlist.id).subscribe((data) => {
      this.reviews = data.content;
    });
  }

  private displayDeleteButton(): void {
    if (this.data.playlist.id === this.userId) {
      this.showDeleteButton = true;
    }
  }

  private clearForm(): void {
    setTimeout(() => {
      this.formGroupDirective?.resetForm();
    }, 0);
  }
}
