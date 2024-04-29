import { Component, inject } from '@angular/core';
import { CuerposComponent } from '../cuerpos/cuerpos.component';
import { CommonModule } from '@angular/common';
import { CuerposInterface } from '../cuerposInterface';
import { CuerposService } from '../cuerpos.service';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    CuerposComponent
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
    this.cuerposService.getAllCuerpos().then((cuerposList: CuerposInterface[]) => {
      this.cuerposList = cuerposList;
      this.filteredLocationList = cuerposList;
    });
  }
}
