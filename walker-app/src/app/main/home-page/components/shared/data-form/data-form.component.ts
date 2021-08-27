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
    private userStore: CurrentUserStoreService) { }

  dataForm = this.builder.group({
    firstName: [this.userStore.regularUser.firstName, Validators.required],
    lastName: [this.userStore.regularUser.lastName, Validators.required],
    city: [this.userStore.regularUser.firstName, Validators.required],
    birthdate: [this.userStore.regularUser.birthdate, Validators.required]
  })

  passwordForm = this.builder.group({
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  })

  ngOnInit(): void {
  }

  submitData() {
    this.dataEmitter.emit(this.dataForm.value);
  }

  submitPassword() {

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
