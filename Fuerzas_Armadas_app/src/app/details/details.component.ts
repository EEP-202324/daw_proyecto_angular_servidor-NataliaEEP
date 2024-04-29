import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { CuerposService } from '../cuerpos.service';
import { CuerposInterface } from '../cuerposInterface';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Peticion } from '../peticion';

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
    id: new FormControl(''),
    cuerpo: new FormControl(''),
    titulacion: new FormControl(''),
    requisitos_edad: new FormControl(''),
    pais: new FormControl(''),
    photo: new FormControl(''),
    pdf: new FormControl('')
  });

  constructor() {
    const cuerpoId = parseInt(this.route.snapshot.params['id']);
    this.cuerposService.getCuerpo(cuerpoId).subscribe(
      cuerpo => {
      this.cuerposLocation = cuerpo;
    });
  }

  submitApplication() {
    this.cuerposService.submitApplication(
      this.applyForm.value.id ?? '',
      this.applyForm.value.cuerpo ?? '',
      this.applyForm.value.titulacion ?? '',
      this.applyForm.value.requisitos_edad ?? '',
      this.applyForm.value.pais ?? '',
      this.applyForm.value.photo ?? '',
      this.applyForm.value.pdf ?? ''
    );
    const peticion: Peticion = {...(this.applyForm.value)};
    this.cuerposService.enviarPeticion(peticion).subscribe({
      next: peticion => {
        alert(peticion.id);
        console.log(peticion)
      },
      error: error => {
        console.log(error)
      }
  });
  }
}
