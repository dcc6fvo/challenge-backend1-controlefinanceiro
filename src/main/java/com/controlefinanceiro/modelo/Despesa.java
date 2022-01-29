package com.controlefinanceiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.controlefinanceiro.modelo.enums.Categoria;

@Entity
@Table(uniqueConstraints = 
@UniqueConstraint(name = "UniqueDescAndDate", columnNames = { "descricao", "data" }))
public class Despesa extends Conta {

	@Enumerated(EnumType.STRING)
	@NotNull
	private Categoria categoria = Categoria.OUTRAS;
	
	public Despesa() { }
	
	public Despesa(@NotBlank(message = "A descrição é obrigatória") String descricao, @NotNull BigDecimal valor, LocalDate data) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}
	
	public Despesa(@NotBlank(message = "A descrição é obrigatória") String descricao, @NotNull BigDecimal valor, LocalDate data, Categoria cat) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = cat;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
