import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { NotfoundComponent } from './notfound/notfound.component';


export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'not-found', component: NotfoundComponent },
  { path: '**', pathMatch: 'full', component: NotfoundComponent }
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: false });
