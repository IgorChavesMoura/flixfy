import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  public vcDeletedEvent:EventEmitter<void> = new EventEmitter();

  constructor() { }
}
