package com.br.gs.gscrawler.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.br.gs.gscrawler.enums.ProdutoTipo;

/*
 * Util - Métodos genéricos a serem usados na aplicação
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

public class Util {
	
	private final String simboloMoeda = "R$ ";
	private final String termoQuebraMoeda = " ";
	private final String elementClass = "prd-new";
	private final String elementClassChange = "ofr-new";
	
	public String customSplitCurrency(String valor) {
		return simboloMoeda + valor.split(termoQuebraMoeda)[4];
	}
	
	public String customSplitCurrencyChange(String valor) {
		return simboloMoeda + valor.split(termoQuebraMoeda)[1];
	}
	
	public Elements connectCountJsoup(String URL) {
		try {
			Document elementSite = Jsoup.connect(URL).get();
			return elementSite.body().getElementsByClass(elementClass).size() == 0 ?
				   elementSite.body().getElementsByClass(elementClassChange) : elementSite.body().getElementsByClass(elementClass);
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public ProdutoTipo handleProdutoTipo(String tipo) {
		return tipo.equalsIgnoreCase("notebook") ? ProdutoTipo.NOTEBOOK : ProdutoTipo.CELULAR;
	}

}
