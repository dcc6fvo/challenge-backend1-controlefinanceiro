package com.controlefinanceiro.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.controlefinanceiro.dto.ReceitaDetalhesDto;
import com.controlefinanceiro.dto.ReceitaDto;
import com.controlefinanceiro.form.AtualizacaoReceitaForm;
import com.controlefinanceiro.form.ReceitaForm;
import com.controlefinanceiro.modelo.Receita;
import com.controlefinanceiro.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository repository;

	@Autowired
	private ModelMapper modelMapper;


	public ResponseEntity<ReceitaDto> cadastrar(ReceitaForm form, UriComponentsBuilder uriBuilder) {

		Receita receita = form.converter();
		repository.save(receita);

		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReceitaDto(receita));

	}

	public Page<ReceitaDto> listaTodos(Integer page, Integer pageSize, String descricao) {

		if( (page !=null && pageSize != null) && descricao != null) {
			Pageable paging = PageRequest.of(page,pageSize);
			Page<Receita> receitas = repository.findByDescricaoContainingIgnoreCase(descricao, paging);
			Page<ReceitaDto> receitasDto = receitas.map(ReceitaDto::new);
			return receitasDto;	
		}
		else if ( (page !=null && pageSize != null) && descricao == null) {
			Pageable paging = PageRequest.of(page,pageSize);
			Page<Receita> receitas = repository.findAll(paging);
			Page<ReceitaDto> receitasDto = receitas.map(ReceitaDto::new);
			return receitasDto;	
		}
		else if ( (page == null && pageSize == null) && descricao != null) {
			List<Receita> receitas = repository.findByDescricaoContainingIgnoreCase(descricao);
			List<ReceitaDto> receitasDto = ReceitaDto.converter(receitas);
			return new PageImpl<ReceitaDto>(receitasDto);

		}else {
			List<Receita> receitas = repository.findAll();
			//List<DespesaDto> despesasDto = DespesaDto.converter(despesas);	
			List<ReceitaDto> receitasDto = receitas.stream()
					.map(c -> modelMapper.map(c, ReceitaDto.class))
					.collect(Collectors.toList());
			
			return new PageImpl<ReceitaDto>(receitasDto);
		}	
	}

	public ResponseEntity<ReceitaDetalhesDto> listaDetalhado(Long id) {

		Optional<Receita> receita = repository.findById(id);
		if (receita.isPresent()) {
			return ResponseEntity.ok(new ReceitaDetalhesDto(receita.get()));
		}else {
			return ResponseEntity.notFound().build();
		}	
	}

	public Page<ReceitaDto> listaAnoMes(int ano, int mes) {

		LocalDate anoMes = LocalDate.of(ano, mes, 1);

		if(anoMes != null) {	
			List<Receita> receitas = repository.findByData(anoMes);
			List<ReceitaDto> receitasDto = ReceitaDto.converter(receitas);
			return new PageImpl<ReceitaDto>(receitasDto);
		}else {
			List<ReceitaDto> receitasDto = new ArrayList<ReceitaDto>();
			return new PageImpl<ReceitaDto>(receitasDto);
		}
	}

	public ResponseEntity<ReceitaDto> atualizar(Long id, AtualizacaoReceitaForm form) {
		
		Optional<Receita> optional = repository.findById(id);
		if (optional.isPresent()) {
			Receita receita = form.atualizar(id, repository);
			return ResponseEntity.ok(new ReceitaDto(receita));
		}
		return ResponseEntity.notFound().build();
	}		

	public ResponseEntity<ReceitaDto> deletar(Long id) {
		
		Optional<Receita> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}			

}
