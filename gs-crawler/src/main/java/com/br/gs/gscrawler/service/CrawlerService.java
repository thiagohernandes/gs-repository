package com.br.gs.gscrawler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.impl.CrawlerImpl;

/*
 * Service Crawler
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@Service
public class CrawlerService {

	@Autowired
	private CrawlerImpl crawlerImpl;
	
	public List<Produto> getProdutos(int pagInicial, int pagFinal) {
		return crawlerImpl.links(pagInicial, pagFinal);
	}
}
