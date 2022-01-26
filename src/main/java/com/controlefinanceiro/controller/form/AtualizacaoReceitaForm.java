package com.controlefinanceiro.controller.form;

import java.time.YearMonth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.controlefinanceiro.modelo.Receita;
import com.controlefinanceiro.modelo.enums.Categoria;
import com.controlefinanceiro.repository.ReceitaRepository;

public class AtualizacaoReceitaForm {

	@NotNull @NotEmpty
	private String descricao;

	@NotNull
	private double valor;

	@NotNull
	private YearMonth data;
	
	@NotNull
	private Categoria categoria;
		
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

	public Receita atualizar(Long id, ReceitaRepository receitaRepository) {
		Receita receita = receitaRepository.getById(id);
		receita.setDescricao(this.getDescricao());
		receita.setCategoria(this.getCategoria());
		receita.setValor(this.getValor());
		receita.setData(this.getData());
		return receita;
	}

	
}