package com.br.gs.gscrawler.enums;

public enum ProdutoTipo {
	CELULAR("celular"), NOTEBOOK("notebook");
    
    private final String valor;
    
    ProdutoTipo(String valorOpcao){
        valor = valorOpcao;
    }
    public String getValor(){
        return valor;
    }
}
