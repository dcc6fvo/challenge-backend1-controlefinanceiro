package com.controlefinanceiro.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.controlefinanceiro.BaseTest;
import com.controlefinanceiro.dto.DespesaCategoriaAnoMesDto;
import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;

public class DespesaRepositoryTest extends BaseTest{

	@Autowired
	private DespesaRepository despesaRepository;

	@Test
	void deveriaRetornarListaDeDespesasAoBuscarPelaDescricao() {
		
		String descricao = "Agua";
	 	List<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);
		
	 	assertThat(despesas).isNotNull();
	 	assertThat(despesas).isNotEmpty();
	}
	
	@Test
	void deveriaRetornarListaDeTodasAsDespesas() {
		
		List<Despesa> despesas = despesaRepository.findAll();
		
	 	assertThat(despesas).isNotNull();
	 	assertThat(despesas).isNotEmpty();
	}

	@Test
	void deveriaRetornarPaginaComDespesasAoBuscarPelaDescricao() {
		
		String descricao = "Agua";
		Pageable paging = PageRequest.of(0,10);
		
		Page<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(paging, descricao);
		
		assertThat(despesas).isNotNull();	
		assertThat(despesas.getTotalElements() > 0).isTrue();
	}
		
	@Test
	void deveriaRetornarPaginaComDespesasAoBuscarPelaData() {
		
		LocalDate anoMes = LocalDate.of(2022, 1, 1);
		Pageable paging = PageRequest.of(0,10);
		
		Page<Despesa> despesas = despesaRepository.findByData(anoMes,paging);
		
		assertThat(despesas).isNotNull();	
		assertThat(despesas.getTotalElements() > 0).isTrue();
	}
	
	@Test
	void deveriaRetornarListaDeDespesasAoBuscarPelaData() {
		
		LocalDate anoMes = LocalDate.of(2022, 1, 1);
		
		List<Despesa> despesas = despesaRepository.findByData(anoMes);
		
		assertThat(despesas).isNotNull();	
		assertThat(despesas.size() > 0).isTrue();
	}
	
	@Test
	void deveriaRetornarListaDeDespesasAoBuscarPelaDataECategoria() {
		
		LocalDate anoMes = LocalDate.of(2022, 1, 1);
		Categoria categoria = Categoria.LAZER;
		
		List<Despesa> despesas = despesaRepository.findByDataAndCategoria(anoMes, categoria);
		
		assertThat(despesas).isNotNull();	
		assertThat(despesas.size() > 0).isTrue();
	}
	
	@Test
	void deveriaRetornarValorTotalDeDespesasAoBuscarPelaData() {
		
		LocalDate anoMes = LocalDate.of(2022, 1, 1);
		
		Double valorTotal = despesaRepository.findSumDespesa(anoMes);
				
		assertThat(valorTotal).isNotNull();		
		assertThat(valorTotal > 0D).isTrue();
	}
	
	@Test
	void deveriaRetornarListaDeDespesasCategorizadasAoBuscarPelaData() {
		
		LocalDate anoMes = LocalDate.of(2022, 1, 1);
		
		List<DespesaCategoriaAnoMesDto> despesas = despesaRepository.findSumDespesaCategoria(anoMes);
				
		assertThat(despesas).isNotNull();		
		assertThat(despesas.size() > 0).isTrue();
	}
	 
	@Test
	void naoDeveriaRetornarListaDeDespesasAoBuscarPelaDescricao() {
		
		String descricao = "Vacina";
		
		List<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);
				
		assertThat(despesas.isEmpty()).isTrue();
		
	}
	
}
