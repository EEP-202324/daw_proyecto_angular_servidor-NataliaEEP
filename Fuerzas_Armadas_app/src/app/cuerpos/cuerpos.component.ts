import { Component, Input, OnInit } from '@angular/core';
import { CuerposInterface } from '../cuerposInterface';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CuerposService } from '../cuerpos.service';
import { EventService } from '../event.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cuerpos',
  standalone: true,
  imports: [
    RouterLink,
    RouterOutlet,
    FormsModule,
    CommonModule
  ],
  templateUrl: './cuerpos.component.html',
  styleUrl: './cuerpos.component.css'
})
export class CuerposComponent implements OnInit {
  @Input() cuerpos!: CuerposInterface;
  paises: string[] = [];
  constructor(private cuerposService: CuerposService, private eventService: EventService) {
    this.obtenerPaises();
  }

  ngOnInit(): void {
    this.obtenerPaises();
  }
  obtenerPaises(): void {
    this.cuerposService.getPaises().subscribe(paises => {
      this.paises = paises;
    });
  }

  actualizarPais(nuevoPais: string): void {
    const cuerpoId = typeof this.cuerpos.id === 'string' ? parseInt(this.cuerpos.id, 10) : this.cuerpos.id;

    const paisAnterior = this.cuerpos.pais;

    this.cuerposService.modificarPais(cuerpoId, nuevoPais).subscribe({
      next: () => {
        console.log(`El país del cuerpo "${this.cuerpos.cuerpo}" se actualizó a "${nuevoPais}".`);
        this.cuerpos.pais = nuevoPais;
      },
      error: error => {
        console.error('Error al intentar actualizar el país del cuerpo:', error);
        this.cuerpos.pais = paisAnterior;
      }
    });
  }



  borrarCuerpo(id: number | string) {
    this.cuerposService.borrarCuerpo(id).subscribe({
      next: () => {
        alert(`El cuerpo "${this.cuerpos.cuerpo}" ha sido borrado.`);
        this.eventService.emitCuerpoDeleted();
      },
      error: error => {
        console.error('Error al intentar borrar el cuerpo:', error);
      }
    });
  }
}
