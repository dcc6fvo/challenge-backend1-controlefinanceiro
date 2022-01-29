package com.controlefinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlefinanceiro.dto.ResumoAnoMesDto;
import com.controlefinanceiro.service.ResumoService;

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
	private ResumoService resumoService;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<ResumoAnoMesDto> resumoAnoMes(@PathVariable int ano, @PathVariable int mes) {

		return resumoService.resumoAnoMes(ano, mes);
	}

}