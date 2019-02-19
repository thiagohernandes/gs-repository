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
import com.br.gs.gscrawler.enums.ProdutoTipo;
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
	private static final String exceptionCrawler = "Site Crawler de consulta offline ou com problemas!";
	private static final String termo = "partir";
	private static final String classNomeProduto = "title";
	private static final String classValorProduto = "price";
	List<Produto> produtosList = new ArrayList<>();
	ConfCrawler confCrawler = new ConfCrawler();
	Util utilCrawler = new Util();
	Elements produtosPaginacao;
	
	public List<Produto> links(ProdutoTipo tipo, int pagina) {
		try {
			LOGGER.debug("*********** Gerando links ***********");
			long tempInicio = System.currentTimeMillis();
			produtosList = new ArrayList<>();
			produtosPaginacao = new Elements();			
			produtosPaginacao = tipo.equals(ProdutoTipo.CELULAR) 
								? utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_CELULARES + pagina) :
									utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_NOTEBOOKS + pagina);
			if (produtosPaginacao == null || (produtosPaginacao.size() == 0 && pagina == 1)) {
				throw new NullPointerException(exceptionCrawler); 
			}
			for(Element produto : produtosPaginacao){
				if (produto.getElementsByClass("price").text().contains(termo)) {
					produtosList.add(new Produto(produto.getElementsByClass(classNomeProduto).text(),
							utilCrawler.customSplitCurrency(produto.getElementsByClass(classValorProduto).text()), tipo));
				} else {
					produtosList.add(new Produto(produto.getElementsByClass(classNomeProduto).text(),
							utilCrawler.customSplitCurrencyChange(produto.getElementsByClass(classValorProduto).text()), tipo));
				}
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
