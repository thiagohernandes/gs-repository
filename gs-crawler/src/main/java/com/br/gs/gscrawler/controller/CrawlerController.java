package com.br.gs.gscrawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gs.gscrawler.domain.Produto;
import com.br.gs.gscrawler.service.CrawlerService;

/*
 * REST Api
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {

	@Autowired
	CrawlerService crawlerService;
	
	@GetMapping(value="/{pag-inicial}/{pag-final}")
	public List<Produto> getProdutos(@PathVariable("pag-inicial") int pagInicial, @PathVariable("pag-final") int pagFinal) {
		return crawlerService.getProdutos(pagInicial, pagFinal);
	}
}