import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CuerposService } from '../cuerpos.service';
import { CuerposInterface } from '../cuerposInterface';
import { CommonModule } from '@angular/common';

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
export class DetailsComponent implements OnInit {
  cuerposLocation: CuerposInterface | undefined;
  applyForm: FormGroup;
  mostrarDatos = false; // Variable para controlar la visibilidad de los datos existentes

  constructor(
    private route: ActivatedRoute,
    private cuerposService: CuerposService
  ) {
    this.applyForm = new FormGroup({
      cuerpo: new FormControl(''),
      titulacion: new FormControl(''),
      requisitos_edad: new FormControl(''),
      pais: new FormControl(''),
      photo: new FormControl(''),
      pdf: new FormControl('')
    });
  }

  ngOnInit(): void {
    // Obtener el ID del cuerpo de la URL
    const cuerpoId = Number(this.route.snapshot.params['id']);
    // Obtener los datos del cuerpo correspondiente al ID
    this.cuerposService.getCuerpo(cuerpoId).subscribe({
      next: cuerpo => {
        this.cuerposLocation = cuerpo;
        // Rellenar el formulario con los datos del cuerpo obtenido
        this.applyForm.patchValue({
          cuerpo: cuerpo.cuerpo,
          titulacion: cuerpo.titulacion,
          requisitos_edad: cuerpo.requisitos_edad,
          pais: cuerpo.pais,
          photo: cuerpo.photo,
          pdf: cuerpo.pdf
        });
      },
      error: error => {
        console.log('Error al obtener el cuerpo', error);
      }
    });
  }

  onSubmit(): void {
    if (this.applyForm.valid) {
      // Obtener los datos del formulario
      const cuerpoDatos = this.applyForm.value;
      // Conservar el ID original en el objeto de datos
      cuerpoDatos.id = this.cuerposLocation?.id;

      // Modificar el cuerpo con los nuevos datos
      this.cuerposService.modificarCuerpo(cuerpoDatos).subscribe({
        next: cuerpoModificado => {
          console.log('Cuerpo modificado', cuerpoModificado);
          alert('Cuerpo modificado exitosamente');
        },
        error: error => {
          console.log('Error al modificar el cuerpo', error);
          alert('Ha ocurrido un error al modificar el cuerpo');
        }
      });
    }
  }

  // Método para cambiar la visibilidad de los datos existentes en el formulario
  toggleDatos(): void {
    this.mostrarDatos = !this.mostrarDatos;
  }

  toggleFormulario(): void {
    this.mostrarDatos = true;
  }

  cancelar(): void {
    // Cerrar el formulario de modificación
    this.mostrarDatos = false;
  }
}
