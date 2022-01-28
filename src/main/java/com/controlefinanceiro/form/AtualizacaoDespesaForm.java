package com.controlefinanceiro.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;
import com.controlefinanceiro.repository.DespesaRepository;


public class AtualizacaoDespesaForm {
	
	@NotNull @NotEmpty
	private String descricao;

	@NotNull
	private BigDecimal valor;

	@NotNull
	private LocalDate data;
	
	private Categoria categoria;
		
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
