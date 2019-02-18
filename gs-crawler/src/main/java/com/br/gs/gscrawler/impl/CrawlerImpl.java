package com.br.gs.gscrawler.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.br.gs.gscrawler.conf.ConfCrawler;
import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.interfaces.CrawlerInterface;
import com.br.gs.gscrawler.util.Util;

/*
 * Implementação da interface CrawlerInterface
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@Component
public class CrawlerImpl implements CrawlerInterface {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerImpl.class);
	List<Produto> produtosList = new ArrayList<>();
	ConfCrawler confCrawler = new ConfCrawler();
	Util utilCrawler = new Util();
	Elements produtosPaginacao;
	
	public List<Produto> links(int pagInicial, int pagFinal) {
		try {
			LOGGER.debug("*********** Gerando links ***********");
			long tempInicio = System.currentTimeMillis();
			int pageCount = pagInicial;
			produtosPaginacao = new Elements();
			produtosList = new ArrayList<>();
			produtosPaginacao = utilCrawler.connectCountJsoup(confCrawler.PAGINACAO + pageCount);
			while (produtosPaginacao.size() > 0 && pageCount <= pagFinal) {
				for(Element produto : produtosPaginacao){
					produtosList.add(new Produto(produto.getElementsByClass("title").text(),
							new Util().customSplitCurrency(produto.getElementsByClass("price").text())));
				}
				pageCount++;
				produtosPaginacao = utilCrawler.connectCountJsoup(confCrawler.PAGINACAO + pageCount);
			}
			long tempFinal = System.currentTimeMillis() - tempInicio;
			LOGGER.debug("*********** Links gerados com sucesso em: " + tempFinal + " milisegundos ***********");
			return produtosList;
		} catch(Exception e) {
			LOGGER.error("*********** Erro ao gerar links: " + e.getMessage() + "***********");
			return produtosList;
		}
		
	}

}
