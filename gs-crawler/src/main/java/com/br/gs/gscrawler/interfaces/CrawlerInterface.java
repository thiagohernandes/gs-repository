package com.br.gs.gscrawler.interfaces;

import java.util.List;

import com.br.gs.gscrawler.domain.Produto;

/*
 * Interface Crawler
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

public interface CrawlerInterface {

	public List<Produto> links(int pagInicial, int pagFinal);
	
}
