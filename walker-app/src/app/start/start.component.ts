import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ImageService } from '../_services/image.service';


const REGISTER = 'register';
const CONFIRMATION = 'confirmation';
@Component({
  selector: 'ktbz-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit {

  constructor(private imageService: ImageService,
    private router: Router,
    private route: ActivatedRoute) { }

  isMainViewActive = true;

  ngOnInit(): void {
    const url = this.route.firstChild?.snapshot.url[0].toString();

    if (url != undefined) {
      this.isMainViewActive = false;
    }
  }

  openRegisterForm(): void {
    this.isMainViewActive = false;
    this.router.navigate(['register'], { relativeTo: this.route });
  }

  backToStartPage(): void {
    this.isMainViewActive = true;
    this.router.navigate(['start'], { relativeTo: this.route });
  }
}
