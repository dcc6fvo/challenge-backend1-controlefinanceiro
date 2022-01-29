package com.controlefinanceiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controlefinanceiro.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	List<Receita> findByDescricao(String descricao);
	
	List<Receita> findAll();
	
	Page<Receita> findByDescricaoContainingIgnoreCase(String descricao, Pageable paging);
	
	List<Receita> findByDescricaoContainingIgnoreCase(String descricao);
	
	Page<Receita> findByData(LocalDate data, Pageable paging);
	
	List<Receita> findByData(LocalDate data);
	
	@Query("SELECT SUM(r.valor) FROM Receita r where r.data = ?1 ")
	double findSumReceita(LocalDate data);
	
}
