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
import { BugReportComponent } from './main/home-page/pages/admin/bug-report/bug-report.component';
import { DogsComponent } from './main/home-page/pages/admin/dogs/dogs.component';
import { SystemComponent } from './main/home-page/pages/admin/system/system.component';
import { UsersComponent } from './main/home-page/pages/admin/users/users.component';
import { DogCreatorComponent } from './main/home-page/pages/owner/dog-creator/dog-creator.component';
import { OwnerDogsComponent } from './main/home-page/pages/owner/owner-dogs/owner-dogs.component';
import { WalkPlannerComponent } from './main/home-page/pages/owner/walk-planner/walk-planner.component';
import { DashboardComponent } from './main/home-page/pages/shared/dashboard/dashboard.component';
import { HistoryComponent } from './main/home-page/pages/shared/history/history.component';
import { MessagesComponent } from './main/home-page/pages/shared/messages/messages.component';
import { ProfileComponent } from './main/home-page/pages/shared/profile/profile.component';
import { DogWalksComponent } from './main/home-page/pages/sitter/dog-walks/dog-walks.component';
import { FavouriteComponent } from './main/home-page/pages/sitter/favourite/favourite.component';
import { PlannerComponent } from './main/home-page/pages/sitter/planner/planner.component';
import { ButtonDirective } from './main/home-page/sidebar/button.directive';
import { DogComponent } from './main/home-page/pages/shared/dog/dog.component';
import { WalkComponent } from './main/home-page/pages/shared/walk/walk.component';
import { DogCardComponent } from './main/home-page/components/shared/dog-card/dog-card.component';
import { ModalComponent } from './main/home-page/components/shared/modal/modal.component';
import { AvatarFormComponent } from './main/home-page/components/shared/avatar-form/avatar-form.component';
import { DataFormComponent } from './main/home-page/components/shared/data-form/data-form.component';
import { DescriptionFormComponent } from './main/home-page/components/shared/description-form/description-form.component';
import { SitterReviewFormComponent } from './main/home-page/components/owner/sitter-review-form/sitter-review-form.component';
import { DogReviewFormComponent } from './main/home-page/components/sitter/dog-review-form/dog-review-form.component';
import { NotificationTestComponent } from './main/notification-test/notification-test.component';

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
    BugReportComponent,
    DogsComponent,
    SystemComponent,
    UsersComponent,
    DogCreatorComponent,
    OwnerDogsComponent,
    WalkPlannerComponent,
    DashboardComponent,
    HistoryComponent,
    MessagesComponent,
    ProfileComponent,
    DogWalksComponent,
    FavouriteComponent,
    PlannerComponent,
    ButtonDirective,
    DogComponent,
    WalkComponent,
    DogCardComponent,
    ModalComponent,
    AvatarFormComponent,
    DataFormComponent,
    DescriptionFormComponent,
    SitterReviewFormComponent,
    DogReviewFormComponent,
    NotificationTestComponent,
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
