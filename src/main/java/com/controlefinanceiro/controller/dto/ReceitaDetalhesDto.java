package com.controlefinanceiro.controller.dto;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import com.controlefinanceiro.modelo.Receita;
import com.controlefinanceiro.modelo.enums.Categoria;


public class ReceitaDetalhesDto {

	private String descricao;
	private double valor;
	private YearMonth data;
	private Categoria categoria;
	
	public ReceitaDetalhesDto(Receita receita) {
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
		this.categoria = receita.getCategoria();
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
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public static List<ReceitaDetalhesDto> converter(List<Receita> receitas) {
		return receitas.stream().map(ReceitaDetalhesDto::new).collect(Collectors.toList());
	}
}
