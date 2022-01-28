package com.controlefinanceiro.repository;

import java.time.YearMonth;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.controlefinanceiro.BaseTest;
import com.controlefinanceiro.controller.dto.DespesaCategoriaAnoMesDto;
import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;

public class DespesaRepositoryTest extends BaseTest{

	@Autowired
	private DespesaRepository despesaRepository;

	@Test
	void deveriaRetornarListaDeDespesasAoBuscarPelaDescricao() {
		
		String descricao = "Agua";
	 	List<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);
		
		Assert.assertNotNull(despesas);		
		Assert.assertFalse(despesas.isEmpty());
	}
	
	@Test
	void deveriaRetornarListaDeTodasAsDespesas() {
		
		List<Despesa> despesas = despesaRepository.findAll();
		
		Assert.assertNotNull(despesas);		
		Assert.assertFalse(despesas.isEmpty());
	}

	@Test
	void deveriaRetornarPaginaComDespesasAoBuscarPelaDescricao() {
		
		String descricao = "Agua";
		Pageable paging = PageRequest.of(0,10);
		
		Page<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(paging, descricao);
		
		Assert.assertNotNull(despesas);		
		Assert.assertTrue(despesas.getTotalElements() > 0);
	}
		
	@Test
	void deveriaRetornarPaginaComDespesasAoBuscarPelaData() {
		
		YearMonth anoMes = YearMonth.of(2022, 1);
		Pageable paging = PageRequest.of(0,10);
		
		Page<Despesa> despesas = despesaRepository.findByData(anoMes,paging);
		
		Assert.assertNotNull(despesas);		
		Assert.assertTrue(despesas.getTotalElements() > 0);
	}
	
	@Test
	void deveriaRetornarListaDeDespesasAoBuscarPelaData() {
		
		YearMonth anoMes = YearMonth.of(2022, 1);
		
		List<Despesa> despesas = despesaRepository.findByData(anoMes);
		
		Assert.assertNotNull(despesas);		
		Assert.assertTrue(despesas.size() > 0);
	}
	
	@Test
	void deveriaRetornarListaDeDespesasAoBuscarPelaDataECategoria() {
		
		YearMonth anoMes = YearMonth.of(2022, 1);
		Categoria categoria = Categoria.Lazer;
		
		List<Despesa> despesas = despesaRepository.findByDataAndCategoria(anoMes, categoria);
		
		Assert.assertNotNull(despesas);		
		Assert.assertTrue(despesas.size() > 0);
	}
	
	@Test
	void deveriaRetornarValorTotalDeDespesasAoBuscarPelaData() {
		
		YearMonth anoMes = YearMonth.of(2022, 1);
		
		Double valorTotal = despesaRepository.findSumDespesa(anoMes);
				
		Assert.assertNotNull(valorTotal);		
		Assert.assertTrue(valorTotal > 0D);
	}
	
	@Test
	void deveriaRetornarListaDeDespesasCategorizadasAoBuscarPelaData() {
		
		YearMonth anoMes = YearMonth.of(2022, 1);
		
		List<DespesaCategoriaAnoMesDto> despesas = despesaRepository.findSumDespesaCategoria(anoMes);
				
		Assert.assertNotNull(despesas);		
		Assert.assertTrue(despesas.size() > 0);
	}
	 
	@Test
	void naoDeveriaRetornarListaDeDespesasAoBuscarPelaDescricao() {
		
		String descricao = "Vacina";
		
		List<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);
				
		Assert.assertTrue(despesas.isEmpty());
	}
	
}
