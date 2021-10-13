import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { RegularUser } from 'src/app/models/users/regular-user.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

export enum modalName {
  AVATAR = 'AVATAR',
  DATA = 'DATA',
  DESCRIPTION = 'DESCRIPTION',
  SITTER_REVIEW = 'SITTER_REVIEW',
  DOG_REVIEW = 'DOG_REVIEW',
  REPORT_PROBLEM = 'REPORT_PROBLEM',
  EMAIL_SENDER = 'EMAIL_SENDER'
}

@Component({
  selector: 'ktbz-modal',
  templateUrl: './modal.component.html'
})
export class ModalComponent implements OnInit {

  @Input()
  settingSelector: string = '';

  @Input()
  walkId: string = '';

  @Input()
  userId: string = '';

  @Output()
  closeModal = new EventEmitter<boolean>();

  @Output()
  upadtedData = new EventEmitter<RegularUser>();

  constructor(private userStore: CurrentUserStoreService) { }

  modalName = '';
  userData = this.userStore.regularUser;

  ngOnInit(): void {
    console.log(this.walkId);
    switch (this.settingSelector) {
      case modalName.AVATAR:
        this.modalName = 'Edytuj avatar';
        break;

      case modalName.DATA:
        this.modalName = 'Edytuj dane';
        break;

      case modalName.DESCRIPTION:
        this.modalName = 'Edytuj opis';
        break;

      case modalName.SITTER_REVIEW:
        this.modalName = 'Oceń opiekuna';
        break;

      case modalName.DOG_REVIEW:
        this.modalName = 'Oceń psa';
        break;

      case modalName.REPORT_PROBLEM:
        this.modalName = 'Zgłoś problem';
        break;

      case modalName.EMAIL_SENDER:
        this.modalName = 'Wyślij wiadomość';
        break;
    }
  }

  close() {
    this.closeModal.emit(false);
  }

  updateData() {
    let updatedUser = this.userStore.regularUser;
    updatedUser = this.userData;
    this.userStore.update(updatedUser);
    this.upadtedData.emit(this.userData);
  }

  getDescription(description: any) {
    this.userData.description = description.description;
    this.updateData();
  }

  getData(data: any) {
    this.userData.firstName = data.firstName;
    this.userData.lastName = data.lastName;
    this.userData.birthdate = data.birthdate;
    this.updateData();
  }

  getAvatar(avatar: any) {
    let avatarUrl = this.userData.avatar.split('/');
    avatarUrl[avatarUrl.length - 1] = avatar.avatar;
    this.userData.avatar = avatarUrl.join('/');
    this.updateData();
  }
}
