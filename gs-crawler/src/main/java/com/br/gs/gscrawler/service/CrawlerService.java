package com.br.gs.gscrawler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.impl.CrawlerImpl;

@Service
public class CrawlerService {

	@Autowired
	private CrawlerImpl crawlerImpl;
	
	public List<Produto> getProdutos() {
		return crawlerImpl.links();
	}
}
