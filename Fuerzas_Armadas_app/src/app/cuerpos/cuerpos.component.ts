import { Component, Input } from '@angular/core';
import { CuerposInterface } from '../cuerposInterface';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CuerposService } from '../cuerpos.service';

@Component({
  selector: 'app-cuerpos',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './cuerpos.component.html',
  styleUrl: './cuerpos.component.css'
})
export class CuerposComponent {
  @Input() cuerpos!: CuerposInterface;
  constructor(private cuerposService: CuerposService) {}

  borrarCuerpo(id: number | string) {
    this.cuerposService.borrarCuerpo(id).subscribe({
      next: () => {
        alert(`El cuerpo "${this.cuerpos.cuerpo}" ha sido borrado.`);
      },
      error: error => {
        console.error('Error al intentar borrar el cuerpo:', error);
      }
    });
  }
}
