import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import {CreateUser} from '../../model/user.model';
import {AuthService} from '../../services/auth.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  public formConfiguration!: any;

  public error!: string;

  constructor(private router: Router, private authService: AuthService){

  }

  ngOnInit(): void{
    this.initializeFormConfiguration();
  }

  public registerUser(event: any): void{
    const user: CreateUser = event.values;

    if (user.password !== user.rePassword){
      this.error = 'Passwords do not match! Please try again.';
      return;
    }

    this.authService.register(user).pipe(first()).subscribe(
      data => {
        this.router.navigate(['login']);
      },
      error => {
        this.error = error;
      }
    )
  }

  public redirectToLogin(): void{
    this.router.navigate(['login']);
  }

  private initializeFormConfiguration(): void{
    this.formConfiguration = {
      controls: [{
        controlName: 'firstName',
        type: 'text',
        placeholder: 'First Name',
        validators: [Validators.required],
        error: 'First Name not valid!'
      }, {
        controlName: 'lastName',
        type: 'text',
        placeholder: 'Last Name',
        validators: [Validators.required],
        error: 'Last Name not valid!'
      }, {
        controlName: 'username',
        type: 'text',
        placeholder: 'E-Mail',
        validators: [Validators.required, Validators.email],
        error: 'E-Mail not valid!'
      }, {
        controlName: 'password',
        type: 'password',
        placeholder: 'Password',
        validators: [Validators.required],
        error: 'Password not valid!'
      }, {
        controlName: 'rePassword',
        type: 'password',
        placeholder: 'Password',
        validators: [Validators.required],
        error: 'Password not valid!'
      }],
      navigation: {
        text: 'Back to Login',
        path: ['login']
      }
    };
  }
}
