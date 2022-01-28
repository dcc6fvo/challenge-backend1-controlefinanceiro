package com.controlefinanceiro.controller.dto;

import java.math.BigDecimal;

import com.controlefinanceiro.modelo.Conta;
import com.controlefinanceiro.modelo.enums.Categoria;

public class DespesaCategoriaAnoMesDto {

	private BigDecimal valor;
	private Categoria categoria;
	
	public DespesaCategoriaAnoMesDto(Categoria categoria, BigDecimal valor) {
		this.valor = valor;
		this.categoria = categoria;
	}
	
	public DespesaCategoriaAnoMesDto(Conta despesa) {	}
					
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "DespesaCategoriaAnoMesDto [valor=" + valor + ", categoria=" + categoria + "]";
	}
}
