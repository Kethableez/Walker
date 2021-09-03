import { ActionResponse } from './../../../../../models/action-response.model';
import { UserService } from 'src/app/core/services/models/user.service';
import { CurrentUserStoreService } from './../../../../../core/services/store/current-user-store.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'ktbz-data-form',
  templateUrl: './data-form.component.html'
})
export class DataFormComponent implements OnInit {
  @Output()
  dataEmitter = new EventEmitter<any>();

  constructor(private builder: FormBuilder,
    private userStore: CurrentUserStoreService,
    private userService: UserService) { }

  dataForm = this.builder.group({
    firstName: [this.userStore.regularUser.firstName, Validators.required],
    lastName: [this.userStore.regularUser.lastName, Validators.required],
    city: [this.userStore.regularUser.firstName, Validators.required],
    birthdate: [this.userStore.regularUser.birthdate, Validators.required],
    isSubscribed: ['true', Validators.required]
  })

  passwordForm = this.builder.group({
    oldPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  })

  ngOnInit(): void {
  }

  submitData() {
    this.userService.chagneData(this.dataForm.value).subscribe(
      (res: ActionResponse) => {
        console.log(res);
        this.dataEmitter.emit(this.dataForm.value);
      },
      () => console.log('error')
    )

    this.dataEmitter.emit(this.dataForm.value);
  }

  submitPassword() {
    this.userService.changePassword(this.passwordForm.value).subscribe(
      (res: ActionResponse) => {
        console.log(res);
      },
      () => console.log('error')
    )
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
