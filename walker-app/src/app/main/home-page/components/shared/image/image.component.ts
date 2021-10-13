import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'ktbz-image',
  templateUrl: './image.component.html',
})
export class ImageComponent implements OnInit {

  @Input()
  image: string = '';

  @Output()
  isGalleryOpen = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  closeGallery() {
    this.isGalleryOpen.emit(false);
  }

}
