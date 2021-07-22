import { HomePageComponent } from './main/home-page/home-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './main/landing-page/landing-page.component';
import { RegisterComponent } from './main/landing-page/register/register.component';
import { ConfirmationComponent } from './main/landing-page/confirmation/confirmation.component';
import { PageNotFoundComponent } from './main/shared/page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: 'start',
    component: LandingPageComponent,
    children: [
      {
        path: 'register',
        component: RegisterComponent,
      },
      {
        path: 'register/:token',
        component: RegisterComponent,
      },
      {
        path: 'confirmation/:token',
        component: ConfirmationComponent,
      },
      {
        path: '**',
        component: PageNotFoundComponent
      }
    ],
  },
  { path: 'home',
    component: HomePageComponent,
    children: [
      {
        path: 'sadbitch',
        component: PageNotFoundComponent
      }
    ]

  },
  { path: '**', redirectTo: 'start' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
