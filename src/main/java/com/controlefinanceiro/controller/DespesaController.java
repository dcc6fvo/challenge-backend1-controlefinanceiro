package com.controlefinanceiro.controller;

import java.net.URI;
import java.time.YearMonth;
import java.util.ArrayList;
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

import com.controlefinanceiro.dto.DespesaDetalhesDto;
import com.controlefinanceiro.dto.DespesaDto;
import com.controlefinanceiro.form.AtualizacaoDespesaForm;
import com.controlefinanceiro.form.DespesaForm;
import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.repository.DespesaRepository;
import com.controlefinanceiro.service.DespesaService;

@RestController
@RequestMapping("/despesas")
@CrossOrigin("http://127.0.0.1")
public class DespesaController {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private DespesaService despesaService;

	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaForm form, UriComponentsBuilder uriBuilder) {
		
		/*Despesa despesa = form.converter();
		despesaRepository.save(despesa);

		URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DespesaDto(despesa));
		*/
		
		return despesaService.cadastrar(form, uriBuilder);
	}

	@GetMapping
	public Page<DespesaDto> listaTodos(Integer page, Integer pageSize, String descricao) {
		return despesaService.listar(page, pageSize, descricao);
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
	
	@GetMapping("/{ano}/{mes}")
	public Page<DespesaDto> listaAnoMes(@PathVariable int ano, @PathVariable int mes) {

		if(ano > 0 && mes > 0 && mes <= 12) {
			YearMonth anoMes = YearMonth.of(ano, mes);
	
			List<Despesa> despesas = despesaRepository.findByData(anoMes);
			List<DespesaDto> despesasDto = DespesaDto.converter(despesas);
			return new PageImpl<DespesaDto>(despesasDto);
		}else {
			List<DespesaDto> despesasDto = new ArrayList<DespesaDto>();
			return new PageImpl<DespesaDto>(despesasDto);
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
