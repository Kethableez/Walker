import { ChangeDetectorRef, Directive, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Directive({
  selector: '[ktbzButton]'
})
export class ButtonDirective implements OnInit{

  activeRoute: string = '';
  previousPath?: string;

  constructor(
    protected router: Router,
    protected route: ActivatedRoute,
  ) {
    route.url.subscribe(() => {
      this.activeRoute = route.snapshot.firstChild?.routeConfig?.path as string;
    })
  }

  ngOnInit() {
    this.router.navigate([this.activeRoute], {relativeTo: this.route});
  }

  navigator(path: string): void {
    this.activeRoute = path;
    switch(this.previousPath) {
      case undefined:
        this.setButtonActive(this.getElement(path));
        this.previousPath = path;
        this.router.navigate([path], {relativeTo: this.route});
        break;

      case this.activeRoute:
        this.router.navigate([path], {relativeTo: this.route});
        break;

      default:
        this.setButtonActive(this.getElement(path));
        this.setButtonInActive(this.getElement(this.previousPath));
        this.previousPath = path;
        this.router.navigate([path], {relativeTo: this.route});
        break;
    }
  }

  setButtonActive(element: HTMLElement) {
    element.setAttribute('style' ,'background-color: #99ACB1');
  }

  setButtonInActive(element: HTMLElement) {
    element.setAttribute('style', 'background-color: #65858C')
  }

  getElement(path: string): HTMLElement {
    return document.getElementById(path) as HTMLElement
  }
}
