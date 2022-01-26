package com.controlefinanceiro.controller;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlefinanceiro.controller.dto.DespesaCategoriaAnoMesDto;
import com.controlefinanceiro.controller.dto.ResumoAnoMesDto;
import com.controlefinanceiro.repository.DespesaRepository;
import com.controlefinanceiro.repository.ReceitaRepository;

@RestController
@RequestMapping("/resumo")
@CrossOrigin("http://127.0.0.1")
public class ResumoController {

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
	public ResponseEntity<ResumoAnoMesDto> resumoAnoMes(@PathVariable int ano, @PathVariable int mes) {

		Double totalDespesas=0D, totalReceitas=0D;
		
		if(ano > 0 && mes > 0 && mes <= 12) {
			YearMonth anoMes = YearMonth.of(ano, mes);
	
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