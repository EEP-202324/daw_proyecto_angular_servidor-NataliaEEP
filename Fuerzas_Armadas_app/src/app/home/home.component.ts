import { Component, Inject, OnInit } from '@angular/core';
import { CuerposComponent } from '../cuerpos/cuerpos.component';
import { CommonModule } from '@angular/common';
import { CuerposInterface } from '../cuerposInterface';
import { CuerposService } from '../cuerpos.service';
import { RouterModule } from '@angular/router';
import { EventService } from '../event.service';

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
export class HomeComponent implements OnInit {

  cuerposList: CuerposInterface[] = [];
  filteredLocationList: CuerposInterface[] = [];

  pageNumber = 0;
  totalPages = 0;

  constructor(private cuerposService: CuerposService, private eventService: EventService) {
    this.getCuerposPaginable(this.pageNumber);
  }
  ngOnInit() {
    this.getCuerposPaginable(this.pageNumber);
    this.eventService.cuerpoDeleted$.subscribe(() => {
      this.getCuerposPaginable(this.pageNumber);
    });
  }

  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.cuerposList;
      return;
    }

    this.cuerposService.searchCuerpos(text).subscribe({
      next: cuerpos => {
        this.filteredLocationList = cuerpos;
      },
      error: error => {
        console.error('Error al buscar cuerpos:', error);
      }
    });
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
        this.cuerposList = cuerpos;
        this.filteredLocationList = cuerpos;
        this.totalPages = totalPages;
      }
    );
  }
}
