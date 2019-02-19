package com.br.gs.gscrawler.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.enums.ProdutoTipo;
import com.br.gs.gscrawler.impl.CrawlerImpl;
import com.br.gs.gscrawler.util.Util;

/*
 * Service Crawler
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@Service
public class CrawlerService {

	@Autowired
	private CrawlerImpl crawlerImpl;
	private Util util = new Util();
	
	public List<Produto> getProdutos(String tipo, int pagina) {
		ProdutoTipo tipoProduto =  util.handleProdutoTipo(tipo);
		return crawlerImpl.links(tipoProduto, pagina);
	}
	
	public void gerarJSON(String tipo) throws IOException {
		ProdutoTipo tipoProduto =  util.handleProdutoTipo(tipo);
		crawlerImpl.gerarJSON(tipoProduto);
	}
}
