package com.controlefinanceiro.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.controlefinanceiro.modelo.Receita;
import com.controlefinanceiro.repository.ReceitaRepository;

public class AtualizacaoReceitaForm {

	@NotNull @NotEmpty
	private String descricao;

	@NotNull
	private BigDecimal valor;

	@NotNull
	private LocalDate data;
			
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

	public Receita atualizar(Long id, ReceitaRepository receitaRepository) {
		Receita receita = receitaRepository.getById(id);
		receita.setDescricao(this.getDescricao());
		receita.setValor(this.getValor());
		receita.setData(this.getData());
		return receita;
	}
	
}