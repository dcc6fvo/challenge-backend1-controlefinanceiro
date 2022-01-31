package com.controlefinanceiro.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.controlefinanceiro.dto.ReceitaDetalhesDto;
import com.controlefinanceiro.dto.ReceitaDto;
import com.controlefinanceiro.form.AtualizacaoReceitaForm;
import com.controlefinanceiro.form.ReceitaForm;
import com.controlefinanceiro.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
@CrossOrigin("http://127.0.0.1")
public class ReceitaController {


	@Autowired
	private ReceitaService receitaService;


	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder) {

		return receitaService.cadastrar(form, uriBuilder);
	}

	@GetMapping
	public Page<ReceitaDto> listaTodos(@RequestParam(required = false)  String descricao,
			@PageableDefault(sort="descricao", direction = Direction.ASC,
			page = 0, size = 10) Pageable paginacao) {

		return receitaService.listaTodos(descricao, paginacao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDetalhesDto> listaDetalhado(@PathVariable Long id) {

		return receitaService.listaDetalhado(id);
	}

	@GetMapping("/{ano}/{mes}")
	public Page<ReceitaDto> listaAnoMes(@PathVariable int ano, @PathVariable int mes) {

		return receitaService.listaAnoMes(ano, mes);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoReceitaForm form) {

		return receitaService.atualizar(id, form);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> deletar(@PathVariable Long id) {

		return receitaService.deletar(id);
	}

}