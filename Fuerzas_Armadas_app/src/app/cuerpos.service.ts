import { Injectable } from '@angular/core';
import { CuerposInterface } from './cuerposInterface';
import { HttpClient } from '@angular/common/http';
import { Peticion } from './peticion';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CuerposService {

  url = 'http://localhost:3000/cuerpos';
  urlPeticion = 'http://localhost:8080/cuerpo';
  constructor(private http: HttpClient) { }

  getCuerpos() {
    return this.http.get<CuerposInterface[]>(this.url);
  }

  getCuerpo(id: number) {
    return this.http.get<CuerposInterface>(`${this.url}/${id}`);
  }

  // async getAllCuerpos(): Promise<CuerposInterface[]> {
  //   const data = await fetch(this.url);
  //   return await data.json() ?? [];
  // }

  // async getCuerposById(id: number): Promise<CuerposInterface | undefined> {
  //   const data = await fetch(`${this.url}/${id}`);
  //   return await data.json() ?? {};
  // }

  submitApplication(cuerpo: string, titulacion: string, requisitos_edad: string, pais: string, photo: string, pdf: string) {
    console.log(`Homes application received: Cuerpo: ${cuerpo}, Titulacion: ${titulacion}, Requisitos_edad: ${requisitos_edad}, Pais: ${pais}, Photo: ${photo}, Pdf: ${pdf}.`);
  }

  enviarPeticion(peticion: Peticion) {
    alert("peticion recibida");
    return this.http.post<Peticion>(this.url, peticion); //aqui tiene que mandar cuerpos en vez de peticiones
  }

  addCuerpos(cuerposDatos: any): Observable<CuerposInterface> {
    return this.http.post<CuerposInterface>(this.url, cuerposDatos);
  }

  borrarPeticion(): Observable<Peticion> {
    return this.http.delete(`${this.url}/c008`);
  }
}
