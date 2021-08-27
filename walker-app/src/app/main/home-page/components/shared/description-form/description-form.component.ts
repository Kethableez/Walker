import { FormBuilder, Validator, Validators } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'ktbz-description-form',
  templateUrl: './description-form.component.html'
})
export class DescriptionFormComponent implements OnInit {

  @Output()
  dataEmitter = new EventEmitter<any>();

  constructor(private builder: FormBuilder) { }

  descriptionForm = this.builder.group({
    description: ['', Validators.required]
  })

  ngOnInit(): void {
  }

  submitData() {
    this.dataEmitter.emit(this.descriptionForm.value);
  }

}
