package com.controlefinanceiro.controller.dto;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import com.controlefinanceiro.modelo.Receita;
import com.controlefinanceiro.modelo.enums.TipoReceita;


public class ReceitaDetalhesDto {

	private String descricao;
	private double valor;
	private YearMonth data;
	private TipoReceita tipoReceita;
	
	public ReceitaDetalhesDto(Receita receita) {
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
		this.tipoReceita = receita.getTiporeceita();
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
	
	public TipoReceita getTipoDespesa() {
		return tipoReceita;
	}
	
	public void setTipoDespesa(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}	
	
	public static List<ReceitaDetalhesDto> converter(List<Receita> receitas) {
		return receitas.stream().map(ReceitaDetalhesDto::new).collect(Collectors.toList());
	}
}
