import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DetailsComponent } from './details/details.component';
import { AddCuerpoComponent } from './add-cuerpo/add-cuerpo.component';

const routeConfig: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Fuerzas Armadas'
  },
  {
    path: 'details/:id',
    component: DetailsComponent,
    title: 'Cuerpo FA'
  },
  {
    path: 'add-cuerpo',
    component: AddCuerpoComponent,
    title: 'Añadir Cuerpo FA'
  }
];

export default routeConfig;
