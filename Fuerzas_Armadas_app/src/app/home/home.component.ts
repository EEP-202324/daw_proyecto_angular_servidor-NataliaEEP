import { Component, Inject } from '@angular/core';
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
  filteredLocationList: CuerposInterface[] = [];
  cuerposService: CuerposService;

  pageNumber = 0; // Página actual
  totalPages = 0; // Total de páginas

  constructor(@Inject(CuerposService) cuerposService: CuerposService) {
    this.cuerposService = cuerposService;
    this.getCuerposPaginable(this.pageNumber);
  }

  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.cuerposList;
      return;
    }

    this.filteredLocationList = this.cuerposList.filter(
      cuerposLocation => cuerposLocation?.cuerpo.toLowerCase().includes(text.toLowerCase())
    );
  }

  previousPage() {
    if (this.pageNumber > 0) {
      this.pageNumber--;
      this.getCuerposPaginable(this.pageNumber);
    }
  }

  nextPage() {
    if (this.pageNumber < this.totalPages - 1) {
      this.pageNumber++;
      this.getCuerposPaginable(this.pageNumber);
    }
  }

  getCuerposPaginable(pageNumber: number) {
    this.cuerposService.getCuerposPaginable(pageNumber).subscribe(
      ({ cuerpos, totalPages }) => {
        this.cuerposList = cuerpos; // Asignar la lista de cuerpos desde la respuesta
        this.filteredLocationList = cuerpos; // Asignar la lista filtrada
        this.totalPages = totalPages; // Asignar el número total de páginas
      }
    );
  }

}
