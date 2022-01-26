package com.controlefinanceiro.controller.dto;

import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;

public class DespesaCategoriaAnoMesDto {

	private double valor;
	private Categoria categoria;
	
	public DespesaCategoriaAnoMesDto(Categoria categoria, double valor) {
		this.valor = valor;
		this.categoria = categoria;
	}
	
	public DespesaCategoriaAnoMesDto(Despesa despesa) {	}
		
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
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
