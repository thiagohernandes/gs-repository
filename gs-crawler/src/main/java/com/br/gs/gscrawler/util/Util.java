package com.br.gs.gscrawler.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Util {
	
	private final String simboloMoeda = "R$ ";
	private final String termoQuebraMoeda = " ";
	private final String elementClass = "prd-new";
	
	public String customSplitCurrency(String valor) {
		return simboloMoeda + valor.split(termoQuebraMoeda)[4];
	}
	
	public Elements connectCountJsoup(String URL) {
		try {
			Document elementSite = Jsoup.connect(URL).get();
			return elementSite.body().getElementsByClass(elementClass);
		} catch(Exception e) {
			return new Elements();
		}
		
	}
	

}
