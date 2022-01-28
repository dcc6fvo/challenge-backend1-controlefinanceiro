package com.controlefinanceiro.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.controlefinanceiro.dto.DespesaDto;
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
	
	public Page<DespesaDto> listar(Integer page, Integer pageSize, String descricao){
		
		if( (page !=null && pageSize != null) && descricao != null) {
			Pageable paging = PageRequest.of(page,pageSize);
			Page<Despesa> despesas = repository.findByDescricaoContainingIgnoreCase(paging, descricao);
			Page<DespesaDto> despesasDto = despesas.map(DespesaDto::new);
			return despesasDto;	
		}
		else if ( (page !=null && pageSize != null) && descricao == null) {
			Pageable paging = PageRequest.of(page,pageSize);
			Page<Despesa> despesas = repository.findAll(paging);
			Page<DespesaDto> despesasDto = despesas.map(DespesaDto::new);
			return despesasDto;	
		}
		else if ( (page == null && pageSize == null) && descricao != null) {
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

	
}
