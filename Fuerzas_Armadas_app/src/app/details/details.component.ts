import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { CuerposService } from '../cuerpos.service';
import { CuerposInterface } from '../cuerposInterface';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})

export class DetailsComponent {

  route: ActivatedRoute = inject(ActivatedRoute);
  cuerposService = inject(CuerposService);
  cuerposLocation: CuerposInterface | undefined;

  applyForm = new FormGroup({
    nombre: new FormControl(''),
    apellidos: new FormControl(''),
    dni: new FormControl(''),
    email: new FormControl('')
  });

  constructor() {
    const cuerposId = parseInt(this.route.snapshot.params['id'], 10);
    this.cuerposService.getCuerposById(cuerposId).then(cuerposLocation => {
      this.cuerposLocation = cuerposLocation;
    });
  }

  submitApplication() {
    this.cuerposService.submitApplication(
      this.applyForm.value.nombre ?? '',
      this.applyForm.value.apellidos ?? '',
      this.applyForm.value.dni ?? '',
      this.applyForm.value.email ?? ''
    );
  }
}
