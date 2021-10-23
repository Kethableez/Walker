import { Component, Input, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/models/user.service';
import { Notification, NotificationType } from 'src/app/models/notification.model';

@Component({
  selector: 'ktbz-notification',
  templateUrl: './notification.component.html'
})
export class NotificationComponent implements OnInit {

  @Input()
  notification!: Notification;

  parsedMessage: string = '';

  constructor(private userService: UserService) { }

  ngOnInit() {}

  markAsRead() {
    if (!this.notification.isRead) {
      this.userService.markAsRead(this.notification?.id).subscribe(
        () => this.notification.isRead = true,
      );
    }
  }

  get notificationType() {
    return NotificationType;
  }

}
