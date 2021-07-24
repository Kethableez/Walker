import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ServerResponse } from './../../../models/server-response.model';

@Component({
  selector: 'ktbz-box-message',
  templateUrl: './box-message.component.html'
})
export class BoxMessageComponent implements OnInit {

  @Input()
  response?: ServerResponse;

  @Output()
  closeMessageBox = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  close() {
    this.closeMessageBox.emit(true);
  }

}
