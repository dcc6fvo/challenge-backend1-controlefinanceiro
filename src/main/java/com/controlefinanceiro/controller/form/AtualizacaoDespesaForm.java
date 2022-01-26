package com.controlefinanceiro.controller.form;

import java.time.YearMonth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;
import com.controlefinanceiro.repository.DespesaRepository;


public class AtualizacaoDespesaForm {
	
	@NotNull @NotEmpty
	private String descricao;

	@NotNull
	private double valor;

	@NotNull
	private YearMonth data;
	
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

	public Despesa atualizar(Long id, DespesaRepository despesaRepository) {
		Despesa despesa = despesaRepository.getById(id);
		
		despesa.setDescricao(this.getDescricao());
		despesa.setValor(this.getValor());
		despesa.setData(this.getData());
		
		if (this.getCategoria() != null)
			despesa.setCategoria(this.getCategoria());
			
		return despesa;
	}

}
