package com.controlefinanceiro.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.controlefinanceiro.service.DespesaService;

@RestController
@RequestMapping("/despesas")
@CrossOrigin("http://127.0.0.1")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;

	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaForm form, UriComponentsBuilder uriBuilder) {
		
		return despesaService.cadastrar(form, uriBuilder);
	}

	@GetMapping
	public Page<DespesaDto> listaTodos(Integer page, Integer pageSize, String descricao) {
		
		return despesaService.listar(page, pageSize, descricao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaDetalhesDto> listaDetalhado(@PathVariable Long id) {

		return despesaService.listaDetalhado(id);
	}

	@GetMapping("/{ano}/{mes}")
	public Page<DespesaDto> listaAnoMes(@PathVariable int ano, @PathVariable int mes) {

		return despesaService.listaAnoMes(ano, mes);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoDespesaForm form) {
		
		return despesaService.atualizar(id, form);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> deletar(@PathVariable Long id) {
		
		return despesaService.deletar(id);
	}


}
