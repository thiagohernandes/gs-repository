package com.br.gs.gscrawler.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping(value="/{tipo}/{pagina}")
	public List<Produto> getProdutos(@PathVariable("tipo") String  tipo, @PathVariable("pagina") int pagina) {
		return crawlerService.getProdutos(tipo, pagina);
	}
	
	@ResponseBody
	@PostMapping("/{tipo}")
	public void gerarJSON(@PathVariable("tipo") String  tipo) throws IOException {
		crawlerService.gerarJSON(tipo);
	}
}
