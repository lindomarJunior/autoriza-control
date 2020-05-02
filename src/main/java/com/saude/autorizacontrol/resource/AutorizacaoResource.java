package com.saude.autorizacontrol.resource;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saude.autorizacontrol.service.AutorizacaoService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/autorizador")
public class AutorizacaoResource {
	
	@Autowired
	AutorizacaoService autorizacaoService;
	
	@GetMapping(
			"/cadastro/procedimento/{procedimento}"
			+ "/idade/{idade}"
			+ "/sexo/{sexo}"
			+ "/autoriza/{autoriza}")
	public ResponseEntity<String> cadastrar(
			@PathVariable Integer procedimento, 
			@PathVariable Integer idade,
			@PathVariable String sexo,
			@PathVariable String autoriza){
		
		try {
			autorizacaoService.cadastrar(procedimento, idade, sexo, autoriza);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}		
		
	}
	
	@GetMapping("/procedimento/{procedimento}/idade/{idade}/sexo/{sexo}")
	public ResponseEntity<String> consultar(
			@PathVariable Integer procedimento, 
			@PathVariable Integer idade,
			@PathVariable String sexo){
		
		try {
			String autoriza = autorizacaoService.consultarAutoriza(procedimento, idade, sexo);		
			return new ResponseEntity<>(autoriza, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
