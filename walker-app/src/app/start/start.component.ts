import { Component, OnInit } from '@angular/core';
import { ImageService } from '../_services/image.service';

@Component({
  selector: 'ktbz-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit {

  constructor(private imageService: ImageService) { }

  isRegisterStepActive = false;

  ngOnInit(): void {
  }

  openRegisterForm(): void {
    this.isRegisterStepActive = true;
  }
}
