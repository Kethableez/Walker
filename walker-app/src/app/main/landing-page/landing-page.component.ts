import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'ktbz-landing-page',
  templateUrl: './landing-page.component.html'
})
export class LandingPageComponent implements OnInit {

  constructor(
    private router: Router,
    private route: ActivatedRoute) { }

  isMainViewActive = true;

  ngOnInit(): void {
    const url = this.route.firstChild?.snapshot.url[0];

    if (url != undefined) {
      this.isMainViewActive = false;
    }
  }

  openRegisterForm(): void {
    this.isMainViewActive = false;
    this.router.navigate(['register'], { relativeTo: this.route });
  }

  backToLandingPage(): void {
    this.isMainViewActive = true;
    this.router.navigate(['start']);
  }

}
