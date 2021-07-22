import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ServerResponse } from './../../../models/server-response.model';

@Component({
  selector: 'ktbz-box-message',
  templateUrl: './box-message.component.html'
})
export class BoxMessageComponent implements OnInit {

  @Input()
  response: ServerResponse = {
    'message': '',
    'code': -1
  };

  @Output()
  closeMessageBox = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  close() {
    console.log('click!')
    this.closeMessageBox.emit(true);
  }

}
