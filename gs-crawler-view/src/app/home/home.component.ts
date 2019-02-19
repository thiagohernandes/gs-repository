import { Component, OnInit } from '@angular/core';
import { HomeService } from './home.service';
import { Produto } from '../interfaces/produto';
import { UtilService } from '../services/util';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public listProdutos: Produto[] = [];
  public paginacao = 0;
  private tipoProduto = 'celular';

  constructor(private homeService: HomeService, private utilService: UtilService) {
  }

  ngOnInit() {
  }

  setTipoProduto(tipo: string) {
    this.paginacao = 0;
    this.tipoProduto = tipo;
  }

  getProdutos() {
    this.homeService.getProdutos(this.tipoProduto, this.paginacao).subscribe(data => {
        this.listProdutos = [];
        this.listProdutos = data;
        this.utilService.updateLoading(false);
    });
  }

  nextPaginacao() {
    this.paginacao += 1;
    this.getProdutos();
  }

  fowardPaginacao() {
    this.paginacao -= 1;
    this.getProdutos();
  }

}
