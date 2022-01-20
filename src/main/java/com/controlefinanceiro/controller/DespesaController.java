package com.controlefinanceiro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.controlefinanceiro.controller.dto.DespesaDetalhesDto;
import com.controlefinanceiro.controller.dto.DespesaDto;

import com.controlefinanceiro.controller.form.AtualizacaoDespesaForm;
import com.controlefinanceiro.controller.form.DespesaForm;

import com.controlefinanceiro.modelo.Despesa;

import com.controlefinanceiro.repository.DespesaRepository;

@RestController
@RequestMapping("/despesas")
@CrossOrigin("http://127.0.0.1")
public class DespesaController {

	@Autowired
	private DespesaRepository despesaRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaForm form, UriComponentsBuilder uriBuilder) {
		Despesa despesa = form.converter();
		despesaRepository.save(despesa);

		URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DespesaDto(despesa));
	}

	@GetMapping
	public Page<DespesaDto> listaTodos(Integer page, Integer pageSize) {

		if(page !=null && pageSize != null) {
			Pageable paging = PageRequest.of(page,pageSize);
			Page<Despesa> despesas = despesaRepository.findAll(paging);
			Page<DespesaDto> despesasDto = despesas.map(DespesaDto::new);
			return despesasDto;	
		}
		else {
			List<Despesa> despesas = despesaRepository.findAll();
			List<DespesaDto> despesasDto = DespesaDto.converter(despesas);
			return new PageImpl<DespesaDto>(despesasDto);	
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesaDetalhesDto> listaDetalhado(@PathVariable Long id) {

		Optional<Despesa> despesa = despesaRepository.findById(id);
		if (despesa.isPresent()) {
			return ResponseEntity.ok(new DespesaDetalhesDto(despesa.get()));
		}else {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoDespesaForm form) {
		Optional<Despesa> optional = despesaRepository.findById(id);
		if (optional.isPresent()) {
			Despesa despesa = form.atualizar(id, despesaRepository);
			return ResponseEntity.ok(new DespesaDto(despesa));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> deletar(@PathVariable Long id) {
		Optional<Despesa> optional = despesaRepository.findById(id);
		if (optional.isPresent()) {
			despesaRepository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
		
	
}
