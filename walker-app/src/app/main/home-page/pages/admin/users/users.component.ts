import { ActionResponse } from 'src/app/models/action-response.model';
import { UserWithInfo } from './../../../../../models/users/user-with-info.model';
import { AdminService } from './../../../../../core/services/models/admin.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { removeById } from 'src/app/core/services/utility/utility.model';
import { map, tap } from 'rxjs/operators';
import { modalName } from '../../../components/shared/modal/modal.component';
import { CurrentUserStoreService } from 'src/app/core/services/store/current-user-store.service';

export enum AdminAction {
  BLOCK = 'BLOCK',
  UNBLOCK = 'UNBLOCK',
  BAN = 'BAN',
  UNBAN = 'UNBAN',
  REMOVE = 'REMOVE',
}
@Component({
  selector: 'ktbz-users',
  templateUrl: './users.component.html',
})

// TODO: Ikony, messageBox, scrollBar

export class UsersComponent {
  constructor(
    private adminService: AdminService,
    private userStore: CurrentUserStoreService
  ) {}

  modalSetting = modalName.EMAIL_SENDER;
  users = this.adminService.usersList.pipe(
    map(users => removeById(users, this.userStore.user.id))
    );
  isModalOpened = false;
  selectedUserId!: string;

  get Action() {
    return AdminAction;
  }

  toggleModal(event: boolean, userId?: string): void {
    this.isModalOpened = event;
    if (userId) this.selectedUserId = userId;
  }

  doAdminAction(action: AdminAction, user: UserWithInfo) {
    switch (action) {
      case this.Action.BLOCK:
        this.blockUser(user);
        break;

      case this.Action.BAN:
        this.banUser(user);
        break;

      case this.Action.UNBLOCK:
        this.unblockUser(user);
        break;

      case this.Action.UNBAN:
        this.unbanUser(user);
        break;

      case this.Action.REMOVE:
        this.deleteUser(user);
        break;
    }
  }

  private blockUser(user: UserWithInfo) {
    this.adminService.blockUser(user.id).subscribe(
      (response: ActionResponse) => {
        user.blocked = true;
      }
    );
  }

  private banUser(user: UserWithInfo) {
    this.adminService.banUser(user.id).subscribe(
      (response: ActionResponse) => {
        user.banned = true;
      },
    );
  }

  private unblockUser(user: UserWithInfo) {
    this.adminService.unblockUser(user.id).subscribe(
      (response: ActionResponse) => {
        user.blocked = false;
      },
    );
  }

  private unbanUser(user: UserWithInfo) {
    this.adminService.unbanUser(user.id).subscribe(
      (response: ActionResponse) => {
        user.banned = false;
      },
    );
  }

  private deleteUser(user: UserWithInfo) {
    this.adminService.removeUser(user.id).subscribe(
      (response: ActionResponse) => {
        this.users = this.users.pipe(
          map((users: UserWithInfo[]) => {
            return removeById(users, user.id);
          })
        );
      },
    );
  }
}
