import { Injectable } from '@angular/core';
import { CuerposInterface } from './cuerposInterface';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Peticion } from './peticion';
import { Observable } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';


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

  // submitApplication(cuerpo: string, titulacion: string, requisitos_edad: string, pais: string, photo: string, pdf: string) {
  //   console.log(`Homes application received: Cuerpo: ${cuerpo}, Titulacion: ${titulacion}, Requisitos_edad: ${requisitos_edad}, Pais: ${pais}, Photo: ${photo}, Pdf: ${pdf}.`);
  // }

  // enviarPeticion(peticion: Peticion) {
  //   alert("peticion recibida");
  //   return this.http.post<Peticion>(this.url, peticion); //aqui tiene que mandar cuerpos en vez de peticiones
  // }

  // addCuerpos(cuerposDatos: any): Observable<CuerposInterface> {
  //   return this.http.post<CuerposInterface>(this.url, cuerposDatos);
  // }

  getMaxId(): Observable<number> {
    return this.getCuerpos().pipe(
      map(cuerpos => {
        const ids: number[] = cuerpos.map(cuerpo => typeof cuerpo.id === 'string' ? parseInt(cuerpo.id) : cuerpo.id);
        return Math.max(...ids);
      })
    );
  }

  addCuerpos(cuerposDatos: Partial<CuerposInterface>): Observable<CuerposInterface> {
    return this.getMaxId().pipe(
      switchMap(maxId => {
        const nuevoId = maxId + 1;
        const nuevoCuerpo: CuerposInterface = {
          id: String(nuevoId),
          cuerpo: cuerposDatos.cuerpo || '',
          titulacion: cuerposDatos.titulacion || '',
          requisitos_edad: cuerposDatos.requisitos_edad || '',
          pais: cuerposDatos.pais || '',
          photo: cuerposDatos.photo || '',
          pdf: cuerposDatos.pdf || ''
        };
        return this.http.post<CuerposInterface>(this.url, nuevoCuerpo);
      })
    );
  }

  // modificarCuerpo(cuerpo: CuerposInterface): Observable<CuerposInterface> {
  //   const urlCuerpo = `${this.url}/${cuerpo.id}`;
  //   return this.http.put<CuerposInterface>(urlCuerpo, cuerpo);
  // }
  modificarCuerpo(cuerpo: CuerposInterface): Observable<CuerposInterface> {
  const urlCuerpo = `${this.url}/${cuerpo.id}`;
  const cuerpoActualizado = {
    id: cuerpo.id,
    cuerpo: cuerpo.cuerpo,
    titulacion: cuerpo.titulacion,
    requisitos_edad: cuerpo.requisitos_edad,
    pais: cuerpo.pais,
    photo: cuerpo.photo,
    pdf: cuerpo.pdf
  };
  return this.http.put<CuerposInterface>(urlCuerpo, cuerpoActualizado);
}

borrarCuerpo(id: number | string): Observable<void> {
  const deleteUrl = `${this.url}/${id}`;
  return this.http.delete<void>(deleteUrl)
    .pipe(
      catchError((error: HttpErrorResponse) => { // Especifica el tipo del parámetro 'error'
        throw 'Error al intentar borrar el cuerpo'; // Maneja errores aquí según tu lógica
      })
    );
}

  borrarPeticion(): Observable<Peticion> {
    return this.http.delete(`${this.url}/c008`);
  }
}
