import { AdminService } from './../../../../../core/services/models/admin.service';
import { Component, OnInit } from '@angular/core';
import { UserInfo } from 'src/app/models/users/user-info.model';

@Component({
  selector: 'ktbz-users',
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  users = this.adminService.usersList;
  tempUsers: UserInfo[] = [];

  ngOnInit(): void {
    this.users.subscribe(
      res => {
        for (let i = 0; i < 20; i++) {
          this.tempUsers.push(res[0]);
        }
      }
    )
  }

  blockUser(userId: string) {
    console.log('user with id: ' + userId + 'was blocked');
  }

  banUser(userId: string) {
    console.log('user with id: ' + userId + 'was banned');
  }

  unblockUser(userId: string) {
    console.log('user with id: ' + userId + 'was unblocked');
  }

  unbanUser(userId: string) {
    console.log('user with id: ' + userId + 'was unbanned');
  }

  deleteUser(userId: string) {
    console.log('user with id: ' + userId + 'was removed');
  }

}
