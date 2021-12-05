import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {AuthUser} from '../../model/user.model';
import {first} from 'rxjs/operators';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public formConfiguration!: any;

  private returnUrl!: string;
  public error!: string;

  constructor(private formBuilder: FormBuilder, private router: Router,
              private route: ActivatedRoute, private authService: AuthService) {
   // redirect to home if already logged in
    if (this.authService.getCurrentUserValue()) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    this.initializeFormConfiguration();
  }

  public onSubmit(event: any): void {
    const user: AuthUser = event.values;
    this.authService.login(user).pipe(first()).subscribe(
      () => {
        const url = this.returnUrl ? this.returnUrl : '/';
        this.router.navigate([url]);
      },
        error => {
          this.error = error.statusText;
        }
    );
  }

  private initializeFormConfiguration(): void{
    this.formConfiguration = {
      controls: [{
        controlName: 'username',
        type: 'text',
        placeholder: 'E-mail',
        validators: [Validators.required, Validators.email],
        error: 'E-mail not valid!'
      }, {
        controlName: 'password',
        type: 'password',
        placeholder: 'Password',
        validators: [Validators.required],
        error: 'Password not valid!'
      }],
      navigation: {
        text: 'Don\'t have an account yet? Register',
        path: ['/register']
      }
    };
  }

  public redirectToRegister(): void {
    this.router.navigate(['/register']);
  }
}
