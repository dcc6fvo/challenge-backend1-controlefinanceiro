package com.controlefinanceiro.controller.dto;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ResumoAnoMesDto {

	/*
	O resumo do mês deve conter as seguintes informações:

	    Valor total das receitas no mês
	    Valor total das despesas no mês
	    Saldo final no mês
	    Valor total gasto no mês em cada uma das categorias
	 */

	private YearMonth anoMes;
	private Double valorTotalDespesas = 0D;
	private Double valorTotalReceitas = 0D;
	private Double saldo;
	private List<DespesaCategoriaAnoMesDto> despesaPorCategoria = new ArrayList<DespesaCategoriaAnoMesDto>();

	public ResumoAnoMesDto() { }

	public ResumoAnoMesDto(YearMonth anoMes, Double valorTotalDespesas, Double valorTotalReceitas, List<DespesaCategoriaAnoMesDto> despesaPorCategoria) {
	
		this.anoMes = anoMes;
		if(valorTotalDespesas != null)
			this.valorTotalDespesas = valorTotalDespesas;
		if(valorTotalReceitas != null)
			this.valorTotalReceitas = valorTotalReceitas;
		this.saldo = this.valorTotalReceitas - this.valorTotalDespesas;
		if(despesaPorCategoria != null || despesaPorCategoria.size() > 0)
			this.despesaPorCategoria = despesaPorCategoria;
	}

	public YearMonth getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(YearMonth anoMes) {
		this.anoMes = anoMes;
	}

	public Double getValorTotalDespesas() {
		return valorTotalDespesas;
	}

	public void setValorTotalDespesas(Double valorTotalDespesas) {
		this.valorTotalDespesas = valorTotalDespesas;
	}

	public Double getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public void setValorTotalReceitas(Double valorTotalReceitas) {
		this.valorTotalReceitas = valorTotalReceitas;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<DespesaCategoriaAnoMesDto> getDespesaPorCategoria() {
		return despesaPorCategoria;
	}

	public void setDespesaPorCategoria(List<DespesaCategoriaAnoMesDto> despesaPorCategoria) {
		this.despesaPorCategoria = despesaPorCategoria;
	}		
}
