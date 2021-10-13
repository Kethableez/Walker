import { modalName } from './components/shared/modal/modal.component';
import { CurrentUserStoreService } from './../../core/services/store/current-user-store.service';
import { Component, OnInit } from '@angular/core';
import { ActionResponse } from '../../models/action-response.model';
import { TokenStorageService } from 'src/app/core/services/auth/token-storage.service';
import { UserService } from 'src/app/core/services/models/user.service';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { User } from 'src/app/models/users/user.model';


@Component({
  selector: 'ktbz-home-page',
  templateUrl: './home-page.component.html'
})
export class HomePageComponent implements OnInit {

  modalSetting = modalName.REPORT_PROBLEM;
  isLoggedIn = false;
  isModalOpened = false;
  currentUser: any;
  response?: ActionResponse;

  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    if (this.token.getToken()) {
      this.isLoggedIn = true;
    }
  }

  reportProblem(): void {

  }

  toggleModal(event: boolean) {
    this.isModalOpened = event;
  }
}
