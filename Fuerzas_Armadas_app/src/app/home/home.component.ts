import { Component, inject } from '@angular/core';
import { CuerposComponent } from '../cuerpos/cuerpos.component';
import { CommonModule } from '@angular/common';
import { CuerposInterface } from '../cuerposInterface';
import { CuerposService } from '../cuerpos.service';
import { of } from 'rxjs';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    CuerposComponent,
    RouterModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  cuerposList: CuerposInterface[] = [];
  cuerposService: CuerposService = inject(CuerposService);
  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.cuerposList;
      return;
    }

    this.filteredLocationList = this.cuerposList.filter(
      cuerposLocation => cuerposLocation?.cuerpo.toLowerCase().includes(text.toLowerCase())
    );
  }
  filteredLocationList: CuerposInterface[] = [];

  constructor() {
    this.cuerposService.getCuerpos().subscribe(
      cuerpos => {
        this.cuerposList = cuerpos;
        this.filteredLocationList = cuerpos;
    });
  }
}
