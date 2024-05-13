import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private cuerpoDeletedSubject = new Subject<void>();

  cuerpoDeleted$ = this.cuerpoDeletedSubject.asObservable();

  emitCuerpoDeleted() {
    this.cuerpoDeletedSubject.next();
  }
}
