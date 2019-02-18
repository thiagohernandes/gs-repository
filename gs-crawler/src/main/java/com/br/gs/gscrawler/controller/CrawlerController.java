package com.br.gs.gscrawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.service.CrawlerService;

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {

	@Autowired
	CrawlerService crawlerService;
	
	@GetMapping(value="/")
	public List<Produto> getProdutos() {
		return crawlerService.getProdutos();
	}
}
