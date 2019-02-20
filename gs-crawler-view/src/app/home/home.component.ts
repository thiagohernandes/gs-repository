import { Component, OnInit } from '@angular/core';
import { HomeService } from './home.service';
import { Produto } from '../interfaces/produto';
import { UtilService } from '../services/util';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public listProdutos: Produto[] = [];
  public paginacao = 0;
  private tipoProduto = 'celular';
  public jsonSuccess = false;

  constructor(private homeService: HomeService, private utilService: UtilService, private router: Router) {
  }

  ngOnInit() {
  }

  setTipoProduto(tipo: string) {
    this.resetMessage();
    this.paginacao = 0;
    this.tipoProduto = tipo;
  }

  getProdutos() {
    this.resetMessage();
    this.homeService.getProdutos(this.tipoProduto, this.paginacao).subscribe(data => {
        this.listProdutos = [];
        this.listProdutos = data;
        this.utilService.updateLoading(false);
    }, err => {
      console.log(err);
      if (!err.ok) {
        this.utilService.updateLoading(false);
        this.router.navigateByUrl('/http-error');
      }
    });
  }

  postJSON() {
    this.homeService.postJSON(this.tipoProduto).subscribe(() => {
        this.utilService.updateLoading(false);
        this.jsonSuccess = true;
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

  resetMessage() {
    this.listProdutos = [];
    if (this.jsonSuccess) {
      this.jsonSuccess = false;
    }
  }

}
