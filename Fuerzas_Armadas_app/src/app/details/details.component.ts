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
  cuerposId = -1;
  constructor() {
      this.cuerposId = Number(this.route.snapshot.params['id']);
  }
}
