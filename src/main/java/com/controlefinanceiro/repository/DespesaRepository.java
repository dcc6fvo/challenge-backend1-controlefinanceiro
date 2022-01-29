package com.controlefinanceiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlefinanceiro.dto.DespesaCategoriaAnoMesDto;
import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	List<Despesa> findByDescricaoContainingIgnoreCase(String descricao);
	
	List<Despesa> findAll();
	
	Page<Despesa> findByDescricaoContainingIgnoreCase(Pageable paging, String descricao);
	
	Page<Despesa> findByData(LocalDate data, Pageable paging);
	
	List<Despesa> findByData(LocalDate data);
	
	List<Despesa> findByDataAndCategoria(LocalDate data, Categoria cat);
	
	@Query("SELECT SUM(d.valor) FROM Despesa d where d.data = ?1")
	Double findSumDespesa(LocalDate data);
	
	@Query("SELECT new com.controlefinanceiro.dto.DespesaCategoriaAnoMesDto (d.categoria, SUM(d.valor)) "
			+ "FROM Despesa d where d.data = ?1 GROUP BY d.categoria")
	List<DespesaCategoriaAnoMesDto> findSumDespesaCategoria(LocalDate data);
	
}
