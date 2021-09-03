import { ActionResponse } from './../../../../../models/action-response.model';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/core/services/models/user.service';

@Component({
  selector: 'ktbz-description-form',
  templateUrl: './description-form.component.html'
})
export class DescriptionFormComponent implements OnInit {

  @Output()
  dataEmitter = new EventEmitter<any>();

  constructor(private builder: FormBuilder,
    private userService: UserService) { }

  descriptionForm = this.builder.group({
    description: ['', Validators.required]
  })

  ngOnInit(): void {
  }

  submitData() {
    this.userService.changeDescription(this.descriptionForm.value).subscribe(
      (res: ActionResponse) => {
        console.log(res);
        this.dataEmitter.emit(this.descriptionForm.value);
      },
      () => console.log('error')
    )

  }

}
