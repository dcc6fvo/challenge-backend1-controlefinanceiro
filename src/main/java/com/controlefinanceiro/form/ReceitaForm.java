package com.controlefinanceiro.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.controlefinanceiro.modelo.Receita;

public class ReceitaForm {
	
	@NotNull @NotEmpty @Length(min = 3)
	private String descricao;
	
	@NotNull
	private BigDecimal valor;

	@NotNull
	private LocalDate data;
			
	public ReceitaForm(@NotNull @NotEmpty @Length(min = 3) String descricao, @NotNull BigDecimal valor,
			@NotNull LocalDate data) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Receita converter() {
		return new Receita (getDescricao(),getValor(),getData());
	}
}
