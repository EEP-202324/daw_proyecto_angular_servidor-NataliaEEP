import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CuerposService } from '../cuerpos.service';
import { CuerposInterface } from '../cuerposInterface';

@Component({
  selector: 'app-add-cuerpo',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './add-cuerpo.component.html',
  styleUrl: './add-cuerpo.component.css'
})
export class AddCuerpoComponent {

  applyForm = new FormGroup({
    cuerpo: new FormControl(''),
    titulacion: new FormControl(''),
    requisitos_edad: new FormControl(''),
    pais: new FormControl(''),
    photo: new FormControl(''),
    pdf: new FormControl('')
  });
  constructor(private cuerposService: CuerposService) {}

  enviar() {
    const cuerpoDatos: Partial<CuerposInterface> = {
      cuerpo: this.applyForm.get('cuerpo')?.value ?? '',
      titulacion: this.applyForm.get('titulacion')?.value ?? '',
      requisitos_edad: this.applyForm.get('requisitos_edad')?.value ?? '',
      pais: this.applyForm.get('pais')?.value ?? '',
      photo: this.applyForm.get('photo')?.value ?? '',
      pdf: this.applyForm.get('pdf')?.value ?? ''

    };
    this.cuerposService.addCuerpos(cuerpoDatos).subscribe({
      next: cuerpoNuevo => {
        console.log('Nuevo cuerpo creado', cuerpoNuevo);
        alert('Nuevo cuerpo creado');
      },
      error: error => {
        console.log('Error al agregar el cuerpo', error);
        alert('Ha ocurrido un error al agregar el cuerpo');
      }
    });
  }
}
