import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './main/landing-page/login/login.component';
import { RegisterComponent } from './main/landing-page/register/register.component';
import { ConfirmationComponent } from './main/landing-page/confirmation/confirmation.component';
import { LandingPageComponent } from './main/landing-page/landing-page.component';
import { HomePageComponent } from './main/home-page/home-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BoxMessageComponent } from './main/shared/box-message/box-message.component';
import { SidebarComponent } from './main/home-page/sidebar/sidebar.component';
import { SitterButtonsComponent } from './main/home-page/sidebar/sitter-buttons/sitter-buttons.component';
import { OwnerButtonsComponent } from './main/home-page/sidebar/owner-buttons/owner-buttons.component';
import { AdminButtonsComponent } from './main/home-page/sidebar/admin-buttons/admin-buttons.component';
import { PageNotFoundComponent } from './main/shared/page-not-found/page-not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { authInterceptorProviders } from './core/helpers/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ConfirmationComponent,
    LandingPageComponent,
    HomePageComponent,
    BoxMessageComponent,
    SidebarComponent,
    SitterButtonsComponent,
    OwnerButtonsComponent,
    AdminButtonsComponent,
    PageNotFoundComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
