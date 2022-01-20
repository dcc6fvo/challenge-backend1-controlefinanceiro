package com.controlefinanceiro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.controlefinanceiro.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>, PagingAndSortingRepository<Receita, Long> {

	List<Receita> findByDescricao(String descricao);
	
	List<Receita> findAll();
	
	Page<Receita> findByDescricaoContainingIgnoreCase(String descricao, Pageable paging);
	
}
