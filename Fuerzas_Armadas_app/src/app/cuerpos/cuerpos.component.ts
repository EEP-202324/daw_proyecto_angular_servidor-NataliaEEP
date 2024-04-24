import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CuerposInterface } from '../cuerposInterface';

@Component({
  selector: 'app-titulaciones',
  standalone: true,
  imports: [],
  templateUrl: './cuerpos.component.html',
  styleUrl: './cuerpos.component.css'
})
export class CuerposComponent {
  @Input() cuerpos!: CuerposInterface;

}
