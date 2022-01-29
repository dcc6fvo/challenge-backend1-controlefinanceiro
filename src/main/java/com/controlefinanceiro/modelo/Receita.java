package com.controlefinanceiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Receita extends Conta{
	
	public Receita() {}
	
	public Receita(@NotBlank(message = "A descrição é obrigatória") String descricao, @NotNull BigDecimal valor,
			LocalDate data) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}

}
