package com.saude.autorizacontrol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutorizacaoResourceIntegrationTest {
	
	@LocalServerPort
    int port;
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void quandoCadastrar_entaoRetornaStausOk() throws Exception {
		
		String url = "http://localhost:" + port + "autorizador/cadastro/procedimento/4567/idade/10/sexo/M/autoriza/NAO";

		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void quandoCadastrarProcedimentoNaoExistente_entaoRetornaStausNotAccetable() throws Exception {
		String url = "http://localhost:" + port + "autorizador/cadastro/procedimento/5555/idade/10/sexo/M/autoriza/NAO";

		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
	}
	
	@Test
	public void quandoConsultar_entaoRetornaStatusDeAutorizacao() throws Exception {
		String url = "http://localhost:" + port + "autorizador/cadastro/procedimento/4567/idade/10/sexo/M/autoriza/NAO";
		testRestTemplate.getForEntity(url, String.class);
		
		url = "http://localhost:" + port + "autorizador/procedimento/4567/idade/10/sexo/M";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		assertEquals("NAO", response.getBody());
	}
	
	@Test
	public void quandoConsultarRegistroNaoExistente_entaoRetornaStatusNotFound() throws Exception {
		String url = "http://localhost:" + port + "autorizador/procedimento/5555/idade/10/sexo/M";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
