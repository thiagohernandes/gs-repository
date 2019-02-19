package com.br.gs.gscrawler.interfaces;

import java.io.IOException;
import java.util.List;

import org.jsoup.select.Elements;

import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.enums.ProdutoTipo;

/*
 * Interface Crawler
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

public interface CrawlerInterface {

	public List<Produto> links(ProdutoTipo tipo, int pagina);
	public List<Produto> handleProdutosPaginacao(Elements produtosPaginacao, List<Produto> produtosList, ProdutoTipo tipo,  int pagina);
	public List<Produto> produtosLoop(Elements produtosPaginacao, List<Produto> produtosList, ProdutoTipo tipo);
	public void gerarJSON(ProdutoTipo tipo) throws IOException;
	
}
