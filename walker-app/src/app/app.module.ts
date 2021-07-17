import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { StartComponent } from './start/start.component';
import { ConfirmationComponent } from './auth/confirmation/confirmation.component';
import { HomeComponent } from './home/home.component';
import { SidebarComponent } from './home/shared/sidebar/sidebar.component';
import { SidebarOwnerButtonsComponent } from './home/shared/sidebar-owner-buttons/sidebar-owner-buttons.component';
import { SidebarSitterButtonsComponent } from './home/shared/sidebar-sitter-buttons/sidebar-sitter-buttons.component';
import { SidebarAdminButtonsComponent } from './home/shared/sidebar-admin-buttons/sidebar-admin-buttons.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    StartComponent,
    ConfirmationComponent,
    HomeComponent,
    SidebarComponent,
    SidebarOwnerButtonsComponent,
    SidebarSitterButtonsComponent,
    SidebarAdminButtonsComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
