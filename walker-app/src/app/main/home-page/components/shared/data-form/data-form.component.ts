import { NotificationService } from 'src/app/core/services/utility/notification.service';
import { ActionResponse } from './../../../../../models/action-response.model';
import { UserService } from 'src/app/core/services/models/user.service';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'ktbz-data-form',
  templateUrl: './data-form.component.html',
})
export class DataFormComponent implements OnInit {
  @Output()
  dataEmitter = new EventEmitter<any>();

  constructor(
    private builder: FormBuilder,
    private userStore: CurrentUserStoreService,
    private userService: UserService,
    private notification: NotificationService
  ) {}

  dataForm = this.builder.group({
    firstName: [this.userStore.regularUser.firstName, Validators.required],
    lastName: [this.userStore.regularUser.lastName, Validators.required],
    city: [this.userStore.regularUser.firstName, Validators.required],
    birthdate: [this.userStore.regularUser.birthdate, Validators.required],
    isSubscribed: ['true', Validators.required],
  });

  passwordForm = this.builder.group({
    oldPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    confirmPassword: ['', Validators.required],
  });

  ngOnInit(): void {}

  submitData() {
    this.userService.chagneData(this.dataForm.value).subscribe(
      (response) => {
        this.notification.dispatchNotification(true, response.message, false);
        this.dataEmitter.emit(this.dataForm.value);
      },
      (error) => this.notification.dispatchNotification(true, error.error, true)
    );

    this.dataEmitter.emit(this.dataForm.value);
  }

  submitPassword() {
    this.userService.changePassword(this.passwordForm.value).subscribe();
  }
  // @Output()
  // dataEmitter = new EventEmitter<any>();

  // constructor(private builder: FormBuilder) { }

  // descriptionForm = this.builder.group({
  //   description: ['', Validators.required]
  // })

  // ngOnInit(): void {
  // }

  // submitData() {
  //   this.dataEmitter.emit(this.descriptionForm.value);
  // }
}
