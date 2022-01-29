package com.controlefinanceiro.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;
import com.controlefinanceiro.uteis.AnoMesDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class DespesaForm {
	
	@NotNull @NotEmpty @Length(min = 3)
	private String descricao;
	
	@NotNull
	private BigDecimal valor;

	@NotNull
	@JsonDeserialize(using = AnoMesDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM")
	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public DespesaForm() {	}
	
	public DespesaForm(@NotNull @NotEmpty @Length(min = 3) String descricao, @NotNull BigDecimal valor,
			@NotNull LocalDate data) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}
	
	public DespesaForm(@NotNull @NotEmpty @Length(min = 3) String descricao, @NotNull BigDecimal valor,
			@NotNull LocalDate data, Categoria categoria) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Despesa converter() {
		
		if (getCategoria() != null)
			return new Despesa (getDescricao(),getValor(),getData(),getCategoria());
		else
			return new Despesa (getDescricao(),getValor(),getData());
	}
}
