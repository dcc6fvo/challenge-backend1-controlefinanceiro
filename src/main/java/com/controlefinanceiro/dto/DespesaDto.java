package com.controlefinanceiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;

public class DespesaDto {

	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private Categoria categoria;
	
	public DespesaDto() {}
	
	public DespesaDto(Despesa despesa) {
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
	}

	public DespesaDto(String descricao, BigDecimal valor, LocalDate data, Categoria categoria) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public static List<DespesaDto> converter(List<Despesa> despesas) {
		return despesas.stream().map(DespesaDto::new).collect(Collectors.toList());
	}
}
