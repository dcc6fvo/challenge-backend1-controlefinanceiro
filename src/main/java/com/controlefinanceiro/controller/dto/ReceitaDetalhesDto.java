package com.controlefinanceiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.controlefinanceiro.modelo.Receita;

public class ReceitaDetalhesDto {

	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	
	public ReceitaDetalhesDto(Receita receita) {
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
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

	public static List<ReceitaDetalhesDto> converter(List<Receita> receitas) {
		return receitas.stream().map(ReceitaDetalhesDto::new).collect(Collectors.toList());
	}
}
