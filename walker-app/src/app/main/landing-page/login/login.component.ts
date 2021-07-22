import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';

@Component({
  selector: 'ktbz-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = 'Eror';
  roles: string[] = [];

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private builder: FormBuilder,
    private router: Router) { }


    get username() {
      return this.loginForm.get('username');
    }

    get password() {
      return this.loginForm.get('password');
    }

    loginForm = this.builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    })

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  loginUser(): void {
    this.authService.loginUser(this.loginForm.value).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
      },
      err => {
        console.log('failure')
        this.errorMessage = err.error;
        this.isLoginFailed = true;
        console.log(this.errorMessage)
      }
    );
  }
}
