import { NotificationPayload } from './../../../models/notification-payload.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActionResponse } from '../../../models/action-response.model';
import { Subscription } from 'rxjs';
import { NotificationService } from 'src/app/core/services/utility/notification.service';

@Component({
  selector: 'ktbz-box-message',
  templateUrl: './box-message.component.html',
})
export class BoxMessageComponent implements OnInit {
  messageVisibilityTime = 3500;
  showNotification: boolean = false;
  notificationBody?: string = '';
  isError?: boolean = false;
  private subscription!: Subscription;

  constructor(private notification: NotificationService) {}

  ngOnInit(): void {
    // setTimeout(() => this.close(), this.messageVisibilityTime );
    this.subscription = this.notification.showMessageSubscription.subscribe(
      (showMessage: NotificationPayload) => {
        console.log(showMessage);
        this.showNotification = showMessage.showNotification;
        this.notificationBody = showMessage.notificationBody;
        this.isError = showMessage.isError;
      }
    );
  }

  close() {
    this.showNotification = false;
  }
}
