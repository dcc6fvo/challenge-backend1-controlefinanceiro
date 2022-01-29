package com.controlefinanceiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@NotBlank(message = "A descrição é obrigatória")
	protected String descricao;
	
	@NotNull
	protected BigDecimal valor;
	
	@NotNull
	protected LocalDate data;

	public Conta() {}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}
		
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}