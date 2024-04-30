import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CuerposService } from '../cuerpos.service';

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
    id: new FormControl(''),
    cuerpo: new FormControl(''),
    titulacion: new FormControl(''),
    requisitos_edad: new FormControl(''),
    pais: new FormControl(''),
    photo: new FormControl(''),
    pdf: new FormControl('')
  });
  constructor(private cuerposService: CuerposService) {}

  enviar() {
    const cuerpoDatos = this.applyForm.value;
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
