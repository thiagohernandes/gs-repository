package com.br.gs.gscrawler;

import com.br.gs.gscrawler.conf.ConfCrawler;
import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.implementations.CrawlerImpl;
import com.br.gs.gscrawler.interfaces.CrawlerInterface;

public class Teste {

	public static void main(String... args) {
		CrawlerInterface x = new CrawlerImpl();
		System.out.println(x.links(new ConfCrawler().SITE).size());
	
		for(Produto elemento : x.links(new ConfCrawler().SITE)) {
			System.out.println(elemento.getDescricao() + " - " + elemento.getValor());
		}
	}
	
}
