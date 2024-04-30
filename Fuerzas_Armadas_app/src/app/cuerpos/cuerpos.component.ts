import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CuerposInterface } from '../cuerposInterface';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CuerposService } from '../cuerpos.service'; // Importa tu servicio

@Component({
  selector: 'app-cuerpos',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './cuerpos.component.html',
  styleUrl: './cuerpos.component.css'
})
export class CuerposComponent {
  @Input() cuerpos!: CuerposInterface;
  constructor(private cuerposService: CuerposService) {} // Inyecta el servicio

  borrarCuerpo(id: number | string) {
    this.cuerposService.borrarCuerpo(id).subscribe(() => {
      console.log(`Cuerpo con ID ${id} borrado exitosamente.`);
      // Aquí puedes manejar cualquier otra lógica después de borrar el cuerpo, como recargar la lista de cuerpos, etc.
    }, error => {
      console.error('Error al intentar borrar el cuerpo:', error);
    });
  }

}
