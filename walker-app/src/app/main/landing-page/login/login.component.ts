import { SitterData } from './../../../models/users/sitter.model';
import { PastWalkInfo } from './../../../models/walks/past-walk-info.model';
import { PastWalkCard } from 'src/app/models/walks/past-walk-card.model';
import { SitterStoreService } from './../../../core/services/store/sitter-store.service';
import { WalkInfo } from 'src/app/models/walks/walk-info.model';
import { SitterService } from 'src/app/core/services/models/sitter.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { CurrentUserStoreService } from './../../../core/services/store/current-user-store.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { ActionResponse } from 'src/app/models/action-response.model';
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
    private userService: UserService,
    private userStore: CurrentUserStoreService,
    private sitterService: SitterService,
    private sitterStore: SitterStoreService,
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
        this.saveAuthTokenAfterLogin(data.token);
        this.setActionAfterSuccess();
        this.storeUserDataAfterSuccess();
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

  private saveAuthTokenAfterLogin(token: any) {
    this.tokenStorage.saveToken(token);
  }

  private setActionAfterSuccess() {
    this.isLoginFailed = false;
    this.isLoggedIn = true;
  }

  private storeUserDataAfterSuccess() {
    this.userService.getUserData().subscribe(res => {
      this.userStore.save(res);
      this.storeItemsForCorespondingRole(this.userStore.role);
      this.router.navigate(['home/dashboard']);
    });
  }

  private storeItemsForCorespondingRole(role: Role) {
    switch(role) {
      case Role.ROLE_OWNER:
        break;

      case Role.ROLE_SITTER:
        this.storeSitterItems();
        break;

      default:
        break;
    }
  }

  // Przenieść do store
  private storeSitterItems() {
    this.sitterService.getWalks().subscribe(
      (response: WalkInfo[]) => this.sitterStore.saveIncomingWalks(response)
    )

    this.sitterService.getHistory().subscribe(
      (response: PastWalkInfo[]) => this.sitterStore.savePastWalks(response)
    )

    this.sitterService.getSitterData().subscribe(
      (response: SitterData) => this.sitterStore.saveSitterData(response)
    )
  }

  closeMessageBox(event: boolean) {
    this.isMessageBoxVisible = false;
  }
}
