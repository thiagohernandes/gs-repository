package com.br.gs.gscrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * Main App
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@SpringBootApplication
public class GsCrawlerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GsCrawlerApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(GsCrawlerApplication.class);
	}

}
