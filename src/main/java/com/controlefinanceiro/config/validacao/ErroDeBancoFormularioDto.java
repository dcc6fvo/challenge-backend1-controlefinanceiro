package com.controlefinanceiro.config.validacao;


public class ErroDeBancoFormularioDto {
	
	private String sql;
	private String erro;
	
	public ErroDeBancoFormularioDto(String sql, String erro) {
		this.sql = sql;
		this.erro = erro;
	}

	public String getSql() {
		return sql;
	}

	public String getErro() {
		return erro;
	}
}
