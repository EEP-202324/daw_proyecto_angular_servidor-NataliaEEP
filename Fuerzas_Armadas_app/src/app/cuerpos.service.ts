import { Injectable } from '@angular/core';
import { CuerposInterface } from './cuerposInterface';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Peticion } from './peticion';
import { Observable } from 'rxjs';
import { map, switchMap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CuerposService {

  // url = 'http://localhost:3000/cuerpos';
  url = 'http://localhost:8080/cuerpos';
  constructor(private http: HttpClient) { }

  getCuerpos() {
    return this.http.get<CuerposInterface[]>(this.url);
  }
  searchCuerpos(searchTerm: string): Observable<CuerposInterface[]> {
    return this.http.get<CuerposInterface[]>(`${this.url}/search?searchTerm=${searchTerm}`);
  }

  getCuerposPaginable(pageNumber: number): Observable<{ cuerpos: CuerposInterface[], totalPages: number }> {
    return this.http.get<any>(`${this.url}?page=${pageNumber}&size=3&sort=cuerpo`).pipe(
      map(response => ({
        cuerpos: response.content,
        totalPages: response.totalPages
      }))
    );
  }

  getCuerpo(id: number) {
    return this.http.get<CuerposInterface>(`${this.url}/${id}`);
  }

  addCuerpos(cuerposDatos: Partial<CuerposInterface>): Observable<CuerposInterface> {
    return this.http.post<CuerposInterface>(this.url, cuerposDatos);
  }

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
      catchError((error: HttpErrorResponse) => {
        throw 'Error al intentar borrar el cuerpo';
      })
    );
}
}
