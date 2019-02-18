package com.br.gs.gscrawler.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/*
 * Produto domain/dto
 * @since: 18-02-2019
 * @author: Thiago Hernandes de Souza
 * */

@JsonInclude(Include.ALWAYS)
public class Produto {

	private String descricao;
	private String valor;
	
	public Produto(String descricao, String valor) {
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
