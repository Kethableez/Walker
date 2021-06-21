import { Component } from '@angular/core';
import { NgModule } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Walker';
  clicks = 0;

  onClick() {
    this.clicks += 1;
  }

  onReset() {
    this.clicks = 0;
  }
}
