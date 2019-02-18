package com.br.gs.gscrawler.implementations;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.br.gs.gscrawler.conf.ConfCrawler;
import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.interfaces.CrawlerInterface;
import com.br.gs.gscrawler.util.Util;

public class CrawlerImpl implements CrawlerInterface {
	
	List<Produto> produtosList = new ArrayList<>();
	ConfCrawler confCrawler = new ConfCrawler();
	Util utilCrawler = new Util();
	
	public List<Produto> links(String URL) {
		try {
			Elements produtosSite = utilCrawler.connectCountJsoup(confCrawler.SITE);
			if(produtosSite.size() > 0) {
				for(Element produto : produtosSite){
					produtosList.add(new Produto(produto.getElementsByClass("title").text(),
							new Util().customSplitCurrency(produto.getElementsByClass("price").text())));
				}
			}
			int pageCount = 1;
			Elements produtosPaginacao = utilCrawler.connectCountJsoup(confCrawler.PAGINACAO + pageCount);
			while (produtosPaginacao.size() > 0) {
				for(Element produto : produtosPaginacao){
					produtosList.add(new Produto(produto.getElementsByClass("title").text(),
							new Util().customSplitCurrency(produto.getElementsByClass("price").text())));
				}
				pageCount++;
				produtosPaginacao = utilCrawler.connectCountJsoup(confCrawler.PAGINACAO + pageCount);
			}
			return produtosList;
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return produtosList;
		}
		
	}

}
