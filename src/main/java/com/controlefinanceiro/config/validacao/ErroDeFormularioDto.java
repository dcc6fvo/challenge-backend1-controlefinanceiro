package com.controlefinanceiro.config.validacao;

public class ErroDeFormularioDto extends Erro {
	
	private String campo;
	
	public ErroDeFormularioDto(String campo, String erro) {
		super(erro);
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}
}
