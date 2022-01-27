package com.controlefinanceiro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.controlefinanceiro.controller.DespesaController;
import com.controlefinanceiro.controller.ReceitaController;

@SpringBootTest
class ControlefinanceiroApplicationTests {

	@Autowired
	private DespesaController despesaController;
	
	@Autowired
	private ReceitaController receitaController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(despesaController).isNotNull();
		assertThat(receitaController).isNotNull();
	}
	
	
	
	

}
