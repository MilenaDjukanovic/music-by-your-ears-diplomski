import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-base-register',
  templateUrl: './base-register.component.html',
  styleUrls: ['./base-register.component.css']
})
export class BaseRegisterComponent implements OnInit {

  @Input() public formConfiguration!: any;
  @Input() public error!: string;
  @Input() public headerMessage!: string;
  @Input() public buttonLabel!: string;
  @Input() public header!: string;
  @Input() public showUploadImage!: boolean;

  @Output() public formSubmitted: EventEmitter<any> = new EventEmitter<any>();

  public coverImage!: any;
  public imageError!: string;

  public registrationForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  public ngOnInit(): void {
    if (!this.formConfiguration){
      this.error = 'Invalid form configuration!';
      return;
    }

    this.buttonLabel = this.buttonLabel ? this.buttonLabel : 'Register';
    this.header = this.header ? this.header : 'Register';

    this.initializeForm();
  }

  private initializeForm(): void{
    const controlConfig: {[key: string]: any} = {};
    const formControls: {[key: string]: any} = this.formConfiguration.controls;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < formControls.length; ++i){
      const formControl = formControls[i];
      const formControlValidators: Array<string> = formControl.validators;

      const controlName: string = formControl.controlName;
      controlConfig[controlName] = ['', formControlValidators];
    }

    this.registrationForm = this.formBuilder.group(controlConfig);
  }

  public onFormSubmit(): void{
    if (this.registrationForm.invalid){
      return;
    }

    if (this.showUploadImage && !this.coverImage) {
      this.imageError = 'You must insert image';
      return;
    }

    const formValues = this.getFormValues();
    if (this.showUploadImage) {
      this.formSubmitted.emit({values: formValues, coverImage: this.coverImage});
    } else {
      this.formSubmitted.emit({values: formValues, coverImage: this.coverImage});
    }
  }

  public redirect(): void{
    this.router.navigate(this.formConfiguration.navigation.path);
  }

  private getFormValues(): {[key: string]: string}{
    const formControls = this.registrationForm.controls;
    const formControlKeys = Object.keys(formControls);

    const formValues: {[key: string]: string} = {};
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < formControlKeys.length; ++i){
      const formKey = formControlKeys[i];
      const formControl = formControls[formKey];
      formValues[formKey] = formControl.value;
    }

    return formValues;
  }

  public onIconImageUpload($event: FileList): void {
    this.coverImage = $event.item(0);
  }
}
