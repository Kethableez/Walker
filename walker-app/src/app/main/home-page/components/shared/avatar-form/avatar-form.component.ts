import { NotificationService } from 'src/app/core/services/utility/notification.service';
import { ActionResponse } from './../../../../../models/action-response.model';
import { UserService } from 'src/app/core/services/models/user.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'ktbz-avatar-form',
  templateUrl: './avatar-form.component.html',
})
export class AvatarFormComponent implements OnInit {
  @Output()
  avatarEmitter = new EventEmitter<any>();

  selectedFile: string = ' ';

  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private notification: NotificationService
  ) {}

  avatarForm = this.builder.group({
    avatar: ['', Validators.required],
  });

  ngOnInit(): void {}

  submitAvatar() {
    let userPhoto = new FormData();
    userPhoto.append('imageFile', this.selectedFile);
    this.userService.changeAvatar(userPhoto).subscribe(
      (res) => {
        this.notification.dispatchNotification(true, res.message, false);
        this.avatarEmitter.emit(this.avatarForm.value);
      },
      (error) => this.notification.dispatchNotification(true, error.error, true)
    );
  }

  onSelectFile(e: any) {
    if (e.target.files.length > 0) {
      const f = e.target.files[0];
      this.selectedFile = f;
      this.avatarForm.patchValue({
        avatar: e.target.files[0].name,
      });
    }
  }
}
