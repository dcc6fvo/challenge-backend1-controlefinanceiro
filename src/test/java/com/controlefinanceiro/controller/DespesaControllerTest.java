package com.controlefinanceiro.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.controlefinanceiro.dto.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para nao usar banco de memoria nos testes
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//para usar @beforeall em ambiente nao estatico
class DespesaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private TokenDto token = new TokenDto();

	@BeforeAll
	public void setUp(){
		try {
			this.token = autenticaDevolveEToken();
		} catch (Exception e) {
			this.token = null;
			System.out.println("erro nao foi possivel recuperar token autenticado 1");
		}
	}

	public TokenDto autenticaDevolveEToken() {

		try {

			URI uri = new URI("/auth");

			String json = "{" 
					+ "       \"email\": \"fvolpato@gmail.com\"," 
					+ "       \"senha\": \"123456\""    
					+ " }";

			MvcResult result = 
					mockMvc.perform(MockMvcRequestBuilders
							.post(uri)
							.content(json)
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers
							.status()
							.is(200))
					.andReturn();

			TokenDto tokenDto = new ObjectMapper().readValue(result.getResponse().getContentAsString(), TokenDto.class); 			
			return tokenDto;

		}catch(Exception e) {
			System.out.println("erro nao foi possivel recuperar token autenticado 2");
			token = null;
			return null;
		}
	}

	
	@Test
	public void deveriaDevolver401AoTentarRealizarProcedimentoSemAutenticacao() throws Exception {
		URI uri = new URI("/despesas/9999");

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(401))
		.andReturn();
	}
	
	
	@Test
	public void deveriaDevolver200AoTentarRealizarProcedimentoComAutenticacao() throws Exception {
		URI uri = new URI("/despesas/1");

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.header("authorization", "Bearer " + this.token.getToken())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200))
		.andReturn();
	}

	
	@Test
	public void deveriaDevolver404AoDeletarDespesaInexistente() throws Exception {
		URI uri = new URI("/despesas/9999");

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri)
				.header("authorization", "Bearer " + this.token.getToken())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404))
		.andReturn();
	}

	@Test
	public void deveriaDevolver201CasoDespesaSemCategoriaDefinidaFoiCadastradaComSucesso() throws Exception {
		URI uri = new URI("/despesas");
		String json =     "        {" 
				+ "                \"descricao\": \"Presente joaquim\"," 
				+ "                \"valor\": \"80.00\","                  
				+ "                \"data\" : \"2022-01\""           
				+ "                }";

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.header("authorization", "Bearer " + this.token.getToken())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201))
		.andReturn();

	}

	
	@Test
	public void deveriaDevolver201CasoDespesaComCategoriaFoiCadastradaComSucesso() throws Exception {
		URI uri = new URI("/despesas");
		String json =     "        {" 
				+ "                \"descricao\": \"Presente joaquim 2\"," 
				+ "                \"valor\": \"90.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"LAZER\""
				+ "                }";

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.header("authorization", "Bearer " + this.token.getToken())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201))
		.andReturn();

	}

	@Test
	public void deveriaDevolver500CasoForEnviadoDespesaComCategoriaInexistente() throws Exception {
		URI uri = new URI("/despesas");
		String json =     "        {" 
				+ "                \"descricao\": \"Presente joaquim 3\"," 
				+ "                \"valor\": \"90.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"LASER\""
				+ "                }";

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.header("authorization", "Bearer " + this.token.getToken())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(500))
		.andReturn();

	}


	@Test
	public void deveriaDevolver200AoBuscarPorDescricaoComPaginacao() throws Exception {
		URI uri = new URI("/despesas");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("page", Collections.singletonList("0"));
		parameters.put("size", Collections.singletonList("10"));
		parameters.put("descricao", Collections.singletonList("joaquim"));

		//MvcResult result =
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.params(parameters)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200))
		.andReturn();	
		
	}

	@Test
	public void deveriaDevolver200AoBuscarPorDescricaoSemPaginacao() throws Exception {
		URI uri = new URI("/despesas");

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.put("descricao", Collections.singletonList("futsal"));

		//MvcResult result =
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.params(parameters)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200))
		.andReturn();
		
	}
	
	
	@Test
	public void deveriaDevolver200AoBuscarPorId() throws Exception {
		URI uri = new URI("/despesas/3");

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200))
		.andReturn();

	}


	@Test
	public void deveriaDevolver404AoBuscarPorIdInexistente() throws Exception {
		URI uri = new URI("/despesas/9999");

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404))
		.andReturn();

	}


	@Test
	public void deveriaDevolver500AoBuscarPorIdInvalido() throws Exception {
		URI uri = new URI("/despesas/cacaca");

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(500))
		.andReturn();

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

		assertThat(result.getResponse().getContentAsString().contains("\"totalElements\":0")).isTrue();

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
		assertThat(result.getResponse().getContentAsString().contains("\"totalElements\":4")).isTrue();

	}

	@Test
	public void deveriaDevolver200AoAtualizarDespesa() throws Exception {
		URI uri = new URI("/despesas/4");

		String json =     "        {" 
				+ "                \"descricao\": \"Gas aquecimento\"," 
				+ "                \"valor\": \"181.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"OUTRAS\""
				+ "                }";

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.header("authorization", "Bearer " + this.token.getToken())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200))
		.andReturn();
	}

	@Test
	public void deveriaDevolver404AoAtualizarDespesaInexistente() throws Exception {
		URI uri = new URI("/despesas/9999");

		String json =     "        {" 
				+ "                \"descricao\": \"Gas aquecimento\"," 
				+ "                \"valor\": \"181.00\","                  
				+ "                \"data\" : \"2022-01\","
				+ "                \"categoria\" : \"OUTRAS\""
				+ "                }";

		//MvcResult result = mockMvc
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.header("authorization", "Bearer " + this.token.getToken())
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404))
		.andReturn();

	}
	/*
	*/
}
