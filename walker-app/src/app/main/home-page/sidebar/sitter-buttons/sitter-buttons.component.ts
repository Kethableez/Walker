import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ButtonDirective } from '../button.directive';

@Component({
  selector: 'ktbz-sitter-buttons',
  templateUrl: './sitter-buttons.component.html'
})
export class SitterButtonsComponent extends ButtonDirective implements OnInit {

  constructor(
    protected router: Router,
    protected route: ActivatedRoute,
  ) {
    super(router, route);
  }

  ngOnInit() {
    let element = this.getElement(this.activeRoute);
    if (element != undefined && element != null) {
        this.setButtonActive(element);
        this.previousPath = this.activeRoute;
    }
  }

}
