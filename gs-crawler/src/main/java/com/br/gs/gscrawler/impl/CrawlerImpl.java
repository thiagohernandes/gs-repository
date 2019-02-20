package com.br.gs.gscrawler.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

/*
 * Implementação da interface CrawlerInterface
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@Component
public class CrawlerImpl implements CrawlerInterface {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CrawlerImpl.class);
	private final String exceptionCrawler = "Site Crawler de consulta offline ou com problemas!";
	private final String termo = "partir";
	private final String classNomeProduto = "title";
	private final String classValorProduto = "price";
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
			produtosList = handleProdutosPaginacao(produtosPaginacao, produtosList, tipo, pagina);	
			long tempFinal = System.currentTimeMillis() - tempInicio;
			LOGGER.debug("*********** Links gerados com sucesso em: " + tempFinal + " milisegundos ***********");
			return produtosList;
		} catch(Exception e) {
			LOGGER.error("*********** Erro ao gerar links: " + e.getMessage() + "***********");
			return produtosList;
		}
	}
	
	public List<Produto> handleProdutosPaginacao(Elements produtosPaginacao, List<Produto> produtosList, ProdutoTipo tipo, int pagina) {
		if (pagina > 0) {
			produtosPaginacao = tipo.equals(ProdutoTipo.CELULAR) 
					? utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_CELULARES + pagina) :
						utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_NOTEBOOKS + pagina);
			if (produtosPaginacao == null || (produtosPaginacao.size() == 0 && pagina == 1)) {
			throw new NullPointerException(exceptionCrawler); 
			}
			produtosList = produtosLoop(produtosPaginacao, produtosList, tipo);
		} else {
			int controlPage = 1;
			int controlconnectCount = utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_CELULARES + controlPage).size();
			while(controlconnectCount > 0) {
				LOGGER.debug("Item -> " + controlPage);
				produtosPaginacao = tipo.equals(ProdutoTipo.CELULAR) 
						? utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_CELULARES + controlPage) :
							utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_NOTEBOOKS + controlPage);
				if (produtosPaginacao == null || (produtosPaginacao.size() == 0 && pagina == 1)) {
				throw new NullPointerException(exceptionCrawler); 
				}
				produtosList = produtosLoop(produtosPaginacao, produtosList, tipo);
				controlPage+=1;
				controlconnectCount = tipo.equals(ProdutoTipo.CELULAR) 
						? utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_CELULARES + controlPage).size() :
							utilCrawler.connectCountJsoup(confCrawler.PAGINACAO_NOTEBOOKS + controlPage).size();
			}
		}
		return produtosList;
	}
	
	public List<Produto> produtosLoop(Elements produtosPaginacao, List<Produto> produtosList, ProdutoTipo tipo) {
		int countItem = 1;
		for(Element produto : produtosPaginacao){
			LOGGER.debug("Item -> " + countItem);
			if (produto.getElementsByClass("price").text().contains(termo)) {
				produtosList.add(new Produto(produto.getElementsByClass(classNomeProduto).text(),
						utilCrawler.customSplitCurrency(produto.getElementsByClass(classValorProduto).text()), tipo));
			} else {
				produtosList.add(new Produto(produto.getElementsByClass(classNomeProduto).text(),
						utilCrawler.customSplitCurrencyChange(produto.getElementsByClass(classValorProduto).text()), tipo));
			}
			countItem++;
		}
		return produtosList;
	}
	
	public void gerarJSON(ProdutoTipo tipo) throws IOException {
		try {
			LOGGER.debug("*********** Gerando JSON ("+ tipo +") ***********");
			List<Produto> listProdutos = links(tipo, 0);
			try (Writer writer = new FileWriter("C:\\tmp\\" +tipo+ "-file.json")) {
			    Gson gson = new GsonBuilder().create();
			    gson.toJson(listProdutos, writer);
			    LOGGER.debug("*********** Sucesso ao gerar o arquivo JSON ("+ tipo +") ***********");
			}
		} catch(JsonIOException e) {
			LOGGER.error("*********** Erro no parse do JSON: " + e.getMessage() + "***********");
		}
	}
	
}
