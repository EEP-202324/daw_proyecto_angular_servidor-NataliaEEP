import { Component } from '@angular/core';
import { CuerposComponent } from '../cuerpos/cuerpos.component';
import { CommonModule } from '@angular/common';
import { CuerposInterface } from '../cuerposInterface';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    CuerposComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  cuerpos: CuerposInterface = {

    cuerpo: 'Cuerpos generales e infantería de Marina',
    titulacion: 'Titulaciones de grado del ANEXO I de la Orden Def. 462/2022 de 25 de mayo',
    requisitos_edad: 'Ingreso directo con exigencia previa de titulación de grado universitario: 27 años',
    pais: 'ESP',
    photo: `https://s3.abcstatics.com/Media/201202/28/marinabosnia--644x362.jpg"`,
  };
}
