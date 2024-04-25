import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CuerposInterface } from '../cuerposInterface';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-cuerpos',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './cuerpos.component.html',
  styleUrl: './cuerpos.component.css'
})
export class CuerposComponent {
  @Input() cuerpos!: CuerposInterface;

}
