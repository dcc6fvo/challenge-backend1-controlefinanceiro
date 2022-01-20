package com.controlefinanceiro.controller.form;

import java.time.YearMonth;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.controlefinanceiro.modelo.Receita;
import com.controlefinanceiro.modelo.enums.TipoReceita;

public class ReceitaForm {
	
	@NotNull @NotEmpty @Length(min = 3)
	private String descricao;
	
	@NotNull
	private double valor;

	@NotNull
	private YearMonth data;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoReceita tipoReceita;
		
	public ReceitaForm(@NotNull @NotEmpty @Length(min = 3) String descricao, @NotNull double valor,
			@NotNull YearMonth data, @NotNull TipoReceita tipoReceita ) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.tipoReceita = tipoReceita ;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public YearMonth getData() {
		return data;
	}

	public void setData(YearMonth data) {
		this.data = data;
	}
		
	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public Receita converter() {
		return new Receita (getDescricao(),getValor(),getData(),getTipoReceita());
	}
}
