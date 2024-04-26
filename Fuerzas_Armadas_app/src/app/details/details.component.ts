import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { CuerposService } from '../cuerpos.service';
import { CuerposInterface } from '../cuerposInterface';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})

export class DetailsComponent {

  route: ActivatedRoute = inject(ActivatedRoute);
  cuerposService = inject(CuerposService);
  cuerposLocation: CuerposInterface | undefined;

  constructor() {
    const cuerposId = Number(this.route.snapshot.params['id']);
    this.cuerposLocation = this.cuerposService.getCuerposById(cuerposId);
  }
}
