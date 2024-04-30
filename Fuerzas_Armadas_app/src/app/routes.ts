import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DetailsComponent } from './details/details.component';
import { AddCuerpoComponent } from './add-cuerpo/add-cuerpo.component';

const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Home page'
  },
  {
    path: 'details/:id',
    component: DetailsComponent,
    title: 'Home details'
  },
  {
    path: 'add-cuerpo',
    component: AddCuerpoComponent,
    title: 'Add Cuerpo'
  }
];

export default routeConfig;
