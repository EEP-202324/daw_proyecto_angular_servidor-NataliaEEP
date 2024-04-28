import { Injectable } from '@angular/core';
import { CuerposInterface } from './cuerposInterface';

@Injectable({
  providedIn: 'root'
})
export class CuerposService {

  url = 'http://localhost:3000/locations';

  async getAllCuerpos(): Promise<CuerposInterface[]> {
    const data = await fetch(this.url);
    return await data.json() ?? [];
  }

  async getCuerposById(id: number): Promise<CuerposInterface | undefined> {
    const data = await fetch(`${this.url}/${id}`);
    return await data.json() ?? {};
  }

  submitApplication(nombre: string, apellidos: string, dni: string, email: string) {
    console.log(`Homes application received: Nombre: ${nombre}, Apellidos: ${apellidos}, Dni: ${dni}, email: ${email}.`);
  }
}
