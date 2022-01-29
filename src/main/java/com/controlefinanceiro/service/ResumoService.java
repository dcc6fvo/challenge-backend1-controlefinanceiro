package com.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.controlefinanceiro.dto.DespesaCategoriaAnoMesDto;
import com.controlefinanceiro.dto.ResumoAnoMesDto;
import com.controlefinanceiro.repository.DespesaRepository;
import com.controlefinanceiro.repository.ReceitaRepository;

@Service
public class ResumoService {

	/*
	O resumo do mês deve conter as seguintes informações:

	    Valor total das receitas no mês
	    Valor total das despesas no mês
	    Saldo final no mês
	    Valor total gasto no mês em cada uma das categorias
	 */

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private DespesaRepository despesaRepository;

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<ResumoAnoMesDto> resumoAnoMes(int ano, int mes) {

		Double totalDespesas=0D, totalReceitas=0D;

		LocalDate anoMes = LocalDate.of(ano, mes, 1);

		if(anoMes != null) {

			try {
				totalDespesas =  despesaRepository.findSumDespesa(anoMes);
				totalReceitas =  receitaRepository.findSumReceita(anoMes);
			}
			catch(org.springframework.aop.AopInvocationException e) {

				if (totalDespesas == null )
					totalDespesas = 0D;
				else {
					totalReceitas = 0D;
				}
			}

			List<DespesaCategoriaAnoMesDto> listaDespesasPorCategoria = despesaRepository.findSumDespesaCategoria(anoMes);

			if(listaDespesasPorCategoria != null)
				return ResponseEntity.ok(new ResumoAnoMesDto(anoMes,totalDespesas, totalReceitas, listaDespesasPorCategoria));
			else
				return ResponseEntity.ok(new ResumoAnoMesDto(anoMes,totalDespesas, totalReceitas, new ArrayList<DespesaCategoriaAnoMesDto>()));

		}else {
			return ResponseEntity.notFound().build();
		}
	}

}