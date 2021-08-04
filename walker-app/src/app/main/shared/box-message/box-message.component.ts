import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActionResponse } from '../../../models/action-response.model';

@Component({
  selector: 'ktbz-box-message',
  templateUrl: './box-message.component.html'
})
export class BoxMessageComponent implements OnInit {

  @Input()
  response?: ActionResponse;

  @Output()
  closeMessageBox = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  close() {
    this.closeMessageBox.emit(true);
  }

}
