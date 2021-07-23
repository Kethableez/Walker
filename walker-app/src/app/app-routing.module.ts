import { HomePageComponent } from './main/home-page/home-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './main/landing-page/landing-page.component';
import { RegisterComponent } from './main/landing-page/register/register.component';
import { ConfirmationComponent } from './main/landing-page/confirmation/confirmation.component';
import { PageNotFoundComponent } from './main/shared/page-not-found/page-not-found.component';
import { DashboardComponent } from './main/home-page/pages/shared/dashboard/dashboard.component';
import { MessagesComponent } from './main/home-page/pages/shared/messages/messages.component';
import { HistoryComponent } from './main/home-page/pages/shared/history/history.component';
import { ProfileComponent } from './main/home-page/pages/shared/profile/profile.component';
import { AuthGuard } from './core/guards/auth.guard';
import { DogCreatorComponent } from './main/home-page/pages/owner/dog-creator/dog-creator.component';
import { WalkPlannerComponent } from './main/home-page/pages/owner/walk-planner/walk-planner.component';
import { OwnerDogsComponent } from './main/home-page/pages/owner/owner-dogs/owner-dogs.component';
import { DogWalksComponent } from './main/home-page/pages/sitter/dog-walks/dog-walks.component';
import { FavouriteComponent } from './main/home-page/pages/sitter/favourite/favourite.component';
import { PlannerComponent } from './main/home-page/pages/sitter/planner/planner.component';
import { SystemComponent } from './main/home-page/pages/admin/system/system.component';
import { BugReportComponent } from './main/home-page/pages/admin/bug-report/bug-report.component';
import { UsersComponent } from './main/home-page/pages/admin/users/users.component';
import { DogsComponent } from './main/home-page/pages/admin/dogs/dogs.component';

const routes: Routes = [
  {
    path: 'start',
    component: LandingPageComponent,
    children: [
      { path: 'register', component: RegisterComponent },
      { path: 'register/:token', component: RegisterComponent },
      { path: 'confirmation/:token', component: ConfirmationComponent },
      { path: '**', component: PageNotFoundComponent}
    ],
  },
  { path: 'home',
    component: HomePageComponent,
    canActivate: [AuthGuard],
    data: {
      role: 'ROLE_USER'
    },
    children: [
      { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], data: {role: 'ROLE_USER'}},
      { path: 'creator', component: DogCreatorComponent, canActivate: [AuthGuard], data: {role: 'ROLE_OWNER'}},
      { path: 'walks', component: DogWalksComponent, canActivate: [AuthGuard], data: {role: 'ROLE_SITTER'}},
      { path: 'system', component: SystemComponent, canActivate: [AuthGuard], data: {role: 'ROLE_ADMIN'}},
      { path: 'reports', component: BugReportComponent, canActivate: [AuthGuard], data: {role: 'ROLE_ADMIN'}},
      { path: 'walk_planner', component: WalkPlannerComponent, canActivate: [AuthGuard], data: {role: 'ROLE_OWNER'}},
      { path: 'planner', component: PlannerComponent, canActivate: [AuthGuard], data: {role: 'ROLE_SITTER'}},
      { path: 'messages', component: MessagesComponent, canActivate: [AuthGuard], data: {role: 'ROLE_USER'}},
      { path: 'users', component: UsersComponent, canActivate: [AuthGuard], data: {role: 'ROLE_ADMIN'}},
      { path: 'system', component: SystemComponent, canActivate: [AuthGuard], data: {role: 'ROLE_ADMIN'}},
      { path: 'user_dogs', component: DogsComponent, canActivate: [AuthGuard], data: {role: 'ROLE_ADMIN'}},
      { path: 'dogs', component: OwnerDogsComponent, canActivate: [AuthGuard], data: {role: 'ROLE_OWNER'}},
      { path: 'favourite', component: FavouriteComponent, canActivate: [AuthGuard], data: {role: 'ROLE_SITTER'}},
      { path: 'history', component: HistoryComponent, canActivate: [AuthGuard], data: {role: 'ROLE_USER'}},
      { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard], data: {role: 'ROLE_USER'}},
      { path: 'profile/:username', component: ProfileComponent, canActivate: [AuthGuard], data: {role: 'ROLE_USER'}},
      { path: 'error404', component: PageNotFoundComponent, canActivate: [AuthGuard], data: {role: 'ROLE_USER'}},
      { path: '**', redirectTo: 'error404'}
    ]

  },
  { path: '**', redirectTo: 'start' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
