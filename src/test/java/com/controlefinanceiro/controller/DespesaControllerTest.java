package com.controlefinanceiro.controller;

import java.net.URI;
import java.util.Collections;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para nao usar banco de memoria nos testes
@ActiveProfiles("test")
class DespesaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private boolean habilitaLogSyso = false;

	@Test
	public void deveriaDevolver404AoDeletarDespesaInexistente() throws Exception {
		URI uri = new URI("/despesas/9999");
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.delete(uri)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(404))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDespesasDeAnoEMes");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}
	
	@Test
	public void deveriaDevolver200AoDeletarDespesa() throws Exception {
		URI uri = new URI("/despesas/1");
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.delete(uri)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(200))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDespesasDeAnoEMes");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}
	
	
	@Test
	public void deveriaDevolver201CasoDespesaSemCategoriaDefinidaFoiCadastradaComSucesso() throws Exception {
		URI uri = new URI("/despesas");
		String json =     "        {" 
				+ "                \"descricao\": \"Presente joaquim\"," 
				+ "                \"valor\": \"80.00\","                  
				+ "                \"data\" : \"2022-01\""           
				+ "                }";

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.post(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(201))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver201CasoDespesaSemCategoriaDefinidaFoiCadastradaComSucesso");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}	

	}

	@Test
	public void deveriaDevolver201CasoDespesaComCategoriaFoiCadastradaComSucesso() throws Exception {
		URI uri = new URI("/despesas");
		String json =     "        {" 
				+ "                \"descricao\": \"Presente joaquim 2\"," 
				+ "                \"valor\": \"90.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"Lazer\""
				+ "                }";

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.post(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(201))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver201CasoDespesaComCategoriaFoiCadastradaComSucesso");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	@Test
	public void deveriaDevolver500CasoForEnviadoDespesaComCategoriaInexistente() throws Exception {
		URI uri = new URI("/despesas");
		String json =     "        {" 
				+ "                \"descricao\": \"Presente joaquim 3\"," 
				+ "                \"valor\": \"90.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"Laser\""
				+ "                }";

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.post(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(500))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver500CasoForEnviadoDespesaComCategoriaInexistente");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	@Test
	public void deveriaDevolver200AoBuscarPorDescricaoComPaginacao() throws Exception {
		URI uri = new URI("/despesas");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("page", Collections.singletonList("0"));
		parameters.put("pageSize", Collections.singletonList("10"));
		parameters.put("descricao", Collections.singletonList("joaquim"));

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(uri)
						.params(parameters)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(200))
				.andReturn();	

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDescricaoComPaginacao");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	@Test
	public void deveriaDevolver200AoBuscarPorDescricaoSemPaginacao() throws Exception {
		URI uri = new URI("/despesas");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("descricao", Collections.singletonList("futsal"));

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(uri)
						.params(parameters)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(200))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDescricaoSemPaginacao");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	@Test
	public void deveriaDevolver200AoBuscarPorId() throws Exception {
		URI uri = new URI("/despesas/3");

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(uri)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(200))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorId");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	@Test
	public void deveriaDevolver404AoBuscarPorIdInexistente() throws Exception {
		URI uri = new URI("/despesas/9999");

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(uri)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(404))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver404AoBuscarPorIdInexistente");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	@Test
	public void deveriaDevolver500AoBuscarPorIdInvalido() throws Exception {
		URI uri = new URI("/despesas/cacaca");

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(uri)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(500))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver500AoBuscarPorIdInexistente");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

		@Test
	public void deveriaDevolver200AoBuscarPorDespesasDeAnoEMesInexistente() throws Exception {
		URI uri = new URI("/despesas/1990/01");

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(uri)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(200))
				.andReturn();

		Assert.assertTrue(result.getResponse().getContentAsString().contains("\"totalElements\":0"));

		if(habilitaLogSyso){	
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDespesasDeAnoEMes");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	

	@Test
	public void deveriaDevolver200AoBuscarPorDespesasDeAnoEMes() throws Exception {
		URI uri = new URI("/despesas/2022/01");


		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.get(uri)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(200))
				.andReturn();

		Assert.assertTrue(result.getResponse().getContentAsString().contains("\"totalElements\":3"));

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDespesasDeAnoEMes");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

	@Test
	public void deveriaDevolver200AoAtualizarDespesa() throws Exception {
		URI uri = new URI("/despesas/4");

		String json =     "        {" 
				+ "                \"descricao\": \"Gas aquecimento\"," 
				+ "                \"valor\": \"181.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"Outras\""
				+ "                }";
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.put(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(200))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDespesasDeAnoEMes");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}
	
	@Test
	public void deveriaDevolver404AoAtualizarDespesaInexistente() throws Exception {
		URI uri = new URI("/despesas/9999");

		String json =     "        {" 
				+ "                \"descricao\": \"Gas aquecimento\"," 
				+ "                \"valor\": \"181.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"Outras\""
				+ "                }";
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.put(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers
						.status()
						.is(404))
				.andReturn();

		if(habilitaLogSyso){
			System.out.println("%%%%%%%%%%% deveriaDevolver200AoBuscarPorDespesasDeAnoEMes");
			System.out.println(result.getResponse().getContentAsString());
			System.out.println("%%%%%%%%%%%");
		}
	}

}
