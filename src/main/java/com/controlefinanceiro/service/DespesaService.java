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
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.controlefinanceiro.dto.DespesaDetalhesDto;
import com.controlefinanceiro.dto.DespesaDto;
import com.controlefinanceiro.form.AtualizacaoDespesaForm;
import com.controlefinanceiro.form.DespesaForm;
import com.controlefinanceiro.modelo.Despesa;
import com.controlefinanceiro.modelo.enums.Categoria;
import com.controlefinanceiro.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<DespesaDto> listar(String descricao, Pageable paginacao){
		
		if( (paginacao != null) && descricao != null) {

			Page<Despesa> despesas = repository.findByDescricaoContainingIgnoreCase(paginacao, descricao);
			Page<DespesaDto> despesasDto = despesas.map(DespesaDto::new);
			return despesasDto;	
		}
		else if ( (paginacao != null) && descricao == null) {
			Page<Despesa> despesas = repository.findAll(paginacao);
			Page<DespesaDto> despesasDto = despesas.map(DespesaDto::new);
			return despesasDto;	
		}
		else if ( (paginacao == null) && descricao != null) {
			List<Despesa> despesas = repository.findByDescricaoContainingIgnoreCase(descricao);
			List<DespesaDto> despesasDto = DespesaDto.converter(despesas);
			return new PageImpl<DespesaDto>(despesasDto);
		}else {
			List<Despesa> despesas = repository.findAll();
			//List<DespesaDto> despesasDto = DespesaDto.converter(despesas);	
			List<DespesaDto> despesasDto = despesas.stream()
					.map(c -> modelMapper.map(c, DespesaDto.class))
					.collect(Collectors.toList());
			
			return new PageImpl<DespesaDto>(despesasDto);
		}	
	}
	

	public ResponseEntity<DespesaDto> cadastrar(DespesaForm form, UriComponentsBuilder uriBuilder){
		
		Despesa despesa = modelMapper.map(form, Despesa.class);
		
		if(despesa.getCategoria() == null) {
			despesa.setCategoria(Categoria.OUTRAS);
		}
		
		//Forçar para toda data ficar com dia 1 e assim (descricao-data) não se repetir no mesmo mes
		despesa.setData(despesa.getData().withDayOfMonth(1));
		
		repository.save(despesa);
		
		URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DespesaDto(despesa));
	}	
	

	public Page<DespesaDto> listaAnoMes(int ano, int mes) {
	
		LocalDate anoMes = LocalDate.of(ano, mes, 1);
	
		if(anoMes != null) {
			List<Despesa> despesas = repository.findByData(anoMes);
			List<DespesaDto> despesasDto = DespesaDto.converter(despesas);
			return new PageImpl<DespesaDto>(despesasDto);
		}else {
			List<DespesaDto> despesasDto = new ArrayList<DespesaDto>();
			return new PageImpl<DespesaDto>(despesasDto);
		}
	}
		
	public ResponseEntity<DespesaDetalhesDto> listaDetalhado(Long id) {
		
		Optional<Despesa> despesa = repository.findById(id);
		if (despesa.isPresent()) {
			return ResponseEntity.ok(new DespesaDetalhesDto(despesa.get()));
		}else {
			return ResponseEntity.notFound().build();
		}	
	}
	
	public ResponseEntity<DespesaDto> atualizar(Long id, AtualizacaoDespesaForm form) {
	
		Optional<Despesa> optional = repository.findById(id);
		if (optional.isPresent()) {
			Despesa despesa = form.atualizar(id, repository);
			return ResponseEntity.ok(new DespesaDto(despesa));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<DespesaDto> deletar(Long id) {
		
		Optional<Despesa> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
