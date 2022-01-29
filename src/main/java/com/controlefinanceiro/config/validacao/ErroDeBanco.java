package com.controlefinanceiro.config.validacao;

public class ErroDeBanco extends Erro {
	
	private String sql;
	
	public ErroDeBanco(String sql, String erro) {
		super(erro);
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
