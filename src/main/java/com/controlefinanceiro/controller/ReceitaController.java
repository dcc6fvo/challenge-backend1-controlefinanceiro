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

import com.controlefinanceiro.controller.dto.ReceitaDetalhesDto;
import com.controlefinanceiro.controller.dto.ReceitaDto;
import com.controlefinanceiro.controller.form.AtualizacaoReceitaForm;
import com.controlefinanceiro.controller.form.ReceitaForm;
import com.controlefinanceiro.modelo.Receita;
import com.controlefinanceiro.repository.ReceitaRepository;

@RestController
@RequestMapping("/receitas")
@CrossOrigin("http://127.0.0.1")
public class ReceitaController {

	@Autowired
	private ReceitaRepository receitaRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder) {
		Receita receita = form.converter();
		receitaRepository.save(receita);

		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReceitaDto(receita));
	}

	@GetMapping
	public Page<ReceitaDto> listaTodos(Integer page, Integer pageSize) {

		if(page !=null && pageSize != null) {
			Pageable paging = PageRequest.of(page,pageSize);
			Page<Receita> despesas = receitaRepository.findAll(paging);
			Page<ReceitaDto> despesasDto = despesas.map(ReceitaDto::new);
			return despesasDto;	
		}
		else {
			List<Receita> receitas = receitaRepository.findAll();
			List<ReceitaDto> receitasDto = ReceitaDto.converter(receitas);
			return new PageImpl<ReceitaDto>(receitasDto);	
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDetalhesDto> listaDetalhado(@PathVariable Long id) {

		Optional<Receita> receita = receitaRepository.findById(id);
		if (receita.isPresent()) {
			return ResponseEntity.ok(new ReceitaDetalhesDto(receita.get()));
		}else {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoReceitaForm form) {
		Optional<Receita> optional = receitaRepository.findById(id);
		if (optional.isPresent()) {
			Receita receita = form.atualizar(id, receitaRepository);
			return ResponseEntity.ok(new ReceitaDto(receita));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> deletar(@PathVariable Long id) {
		Optional<Receita> optional = receitaRepository.findById(id);
		if (optional.isPresent()) {
			receitaRepository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
		
}