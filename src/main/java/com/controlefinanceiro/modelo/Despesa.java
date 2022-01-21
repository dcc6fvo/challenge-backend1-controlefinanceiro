package com.controlefinanceiro.modelo;

import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.controlefinanceiro.modelo.enums.TipoDespesa;
import com.controlefinanceiro.utilidades.YearMonthDateAttributeConverter;

@Entity
@Table(uniqueConstraints = 
@UniqueConstraint(name = "UniqueDescAndDate", columnNames = { "descricao", "data" }))
public class Despesa {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;

	@NotNull
	private double valor;

	@Column(name = "data",columnDefinition = "date")
	@Convert(converter = YearMonthDateAttributeConverter.class)
	@NotNull
	private YearMonth data;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoDespesa tipoDespesa;
	
	public Despesa() { }
	
	public Despesa(Long id, @NotBlank(message = "A descrição é obrigatória") String descricao, @NotNull double valor,
			YearMonth data, @NotBlank(message = "É preciso definir o tipo de despesa") TipoDespesa tipoDespesa) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.tipoDespesa = tipoDespesa;
	}
	
	public Despesa(@NotBlank(message = "A descrição é obrigatória") String descricao, @NotNull double valor,
			YearMonth data, @NotBlank(message = "É preciso definir o tipo de despesa") TipoDespesa tipoDespesa) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.tipoDespesa = tipoDespesa;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
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
