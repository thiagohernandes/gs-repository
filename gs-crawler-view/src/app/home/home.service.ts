import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Produto } from '../interfaces/produto';
import { Observable, Subject } from 'rxjs';
import { UtilService } from '../services/util';

const REST_API_DEV = 'http://localhost:8080/api/crawler/';
const REST_API_PROD = 'http://localhost:8080/gs-crawler/api/crawler/';

@Injectable()
export class HomeService {

  constructor(private http: HttpClient, private utilService: UtilService) {
  }

  getProdutos(tipo: string, pagina: number): Observable<Produto[]> {
    this.utilService.updateLoading(true);
    const URL = `${REST_API_DEV}${tipo}/${pagina}`;
    return this.http.get<Produto[]>(URL);
  }

  postJSON(tipo: string): Observable<void> {
    this.utilService.updateLoading(true);
    const URL = `${REST_API_DEV}${tipo}`;
    return this.http.post<void>(URL, {});
  }

}
