package com.br.gs.gscrawler.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*
 * Configuração de CORS
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@Configuration
public class ConfCORS extends WebMvcConfigurationSupport  {
	public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/api/**")
	.allowedOrigins("http://localhost:4200","http://localhost:8080")
	.allowedMethods("*")
	.allowedHeaders("*")
	.allowCredentials(false)
	.maxAge(3600);
	}

}
