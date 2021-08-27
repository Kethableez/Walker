import { FormBuilder, Validators } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'ktbz-avatar-form',
  templateUrl: './avatar-form.component.html'
})
export class AvatarFormComponent implements OnInit {

  @Output()
  avatarEmitter = new EventEmitter<any>();

  constructor(private builder: FormBuilder) { }

  avatarForm = this.builder.group({
    avatar: ['', Validators.required]
  })

  ngOnInit(): void {
  }

  submitAvatar() {
    this.avatarEmitter.emit(this.avatarForm.value);
  }

  onSelectFile(e: any) {
    if (e.target.files.length > 0) {
      const f = e.target.files[0];
      this.avatarForm.patchValue({
        avatar: e.target.files[0].name,
      });

      console.log(this.avatarForm.value);
    }
  }

}
