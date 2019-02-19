import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Produto } from '../interfaces/produto';
import { Observable, Subject } from 'rxjs';
import { UtilService } from '../services/util';

const REST_API = 'http://localhost:8080/api/crawler/';

@Injectable()
export class HomeService {

  constructor(private http: HttpClient, private utilService: UtilService) {
  }

  getProdutos(tipo: string, pagina: number): Observable<Produto[]> {
    this.utilService.updateLoading(true);
    const URL = `${REST_API}${tipo}/${pagina}`;
    return this.http.get<Produto[]>(URL);
  }

}
