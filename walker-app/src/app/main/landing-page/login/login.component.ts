import { UserService } from 'src/app/core/services/models/user.service';
import { CurrentUserStoreService } from './../../../core/services/store/current-user-store.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { ActionResponse } from 'src/app/models/action-response.model';
import { UserRole } from 'src/app/models/users/regular-user.model';
import { Role } from 'src/app/models/enums/role.model';

@Component({
  selector: 'ktbz-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  isLoggedIn = false;
  isLoginFailed = false;
  roles: Role[] = []
  response?: ActionResponse;
  isMessageBoxVisible = false;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private userStore: CurrentUserStoreService,
    private UserService: UserService,
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
      this.roles = this.userStore.roles;
    }
  }

  loginUser(): void {
    this.authService.loginUser(this.loginForm.value).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.isLoginFailed = false;
        this.isLoggedIn = true;

        this.UserService.getUserData().subscribe(res => {
          this.userStore.save(res);
          this.router.navigate(['home/dashboard']);
        });
      },
      err => {
        this.response = {
          'message': err.error,
          'isSuccess': false
        }
        this.isMessageBoxVisible = true;
      }
    );
  }

  closeMessageBox(event: boolean) {
    this.isMessageBoxVisible = false;
  }
}
