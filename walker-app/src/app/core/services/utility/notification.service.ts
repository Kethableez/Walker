import { NotificationPayload } from './../../../models/notification-payload.model';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }

  showMessageSubscription = new Subject<NotificationPayload>();

  dispatchNotification(showNotification: boolean, notificationBody?: string, isError?: boolean) {
      const notification: NotificationPayload = { showNotification, notificationBody, isError };
      this.showMessageSubscription.next(notification);
  }
}
