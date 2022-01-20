package com.controlefinanceiro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.controlefinanceiro.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>, PagingAndSortingRepository<Despesa, Long> {

	List<Despesa> findByDescricao(String descricao);
	
	List<Despesa> findAll();
	
	Page<Despesa> findByDescricaoContainingIgnoreCase(String descricao, Pageable paging);
	
}
