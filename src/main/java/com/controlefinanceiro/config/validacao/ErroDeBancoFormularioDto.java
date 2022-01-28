package com.controlefinanceiro.config.validacao;

public class ErroDeBancoFormularioDto extends Erro {
	
	private String sql;
	
	public ErroDeBancoFormularioDto(String sql, String erro) {
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
