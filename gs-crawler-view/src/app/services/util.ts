import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable()
export class UtilService {

  private loadingSubject = new Subject<boolean>();

  constructor() {
  }

  updateLoading(param: boolean) {
    this.loadingSubject.next(param);
  }

  getLoading(): Observable<boolean> {
      return this.loadingSubject.asObservable();
  }

}
