import { NotificationService } from 'src/app/core/services/utility/notification.service';
import { ActionResponse } from './../../../../../models/action-response.model';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/core/services/models/user.service';

@Component({
  selector: 'ktbz-description-form',
  templateUrl: './description-form.component.html',
})
export class DescriptionFormComponent implements OnInit {
  @Output()
  dataEmitter = new EventEmitter<any>();

  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private notification: NotificationService
  ) {}

  descriptionForm = this.builder.group({
    description: ['', Validators.required],
  });

  ngOnInit(): void {}

  submitData() {
    this.userService.changeDescription(this.descriptionForm.value).subscribe(
      (response) => {
        this.notification.dispatchNotification(true, response.message, false);
        this.dataEmitter.emit(this.descriptionForm.value);
      },
      (error) => this.notification.dispatchNotification(true, error.error, true)
    );
  }
}
