package com.saude.autorizacontrol.service;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.saude.autorizacontrol.entity.Autorizacao;
import com.saude.autorizacontrol.entity.Procedimento;
import com.saude.autorizacontrol.enums.Sexo;
import com.saude.autorizacontrol.repository.AutorizacaoRepository;
import com.saude.autorizacontrol.repository.ProcedimentoRepository;

import javassist.NotFoundException;

@Service
public class AutorizacaoService {
	
	@Autowired
	private AutorizacaoRepository autorizacaoRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	public void cadastrar(Integer procedimento, Integer idade, String sexo, String autoriza) throws NotFoundException {	
		Autorizacao autorizacao = criarObjetoAutorizacao(procedimento, idade, sexo, autoriza);
		autorizacaoRepository.save(autorizacao);
	}
	
	public String consultarAutoriza(Integer procedimento, Integer idade, String sexo) {
		return consultar(procedimento, idade, sexo).getAutorizaString();
	}
	
	public Autorizacao consultar(Integer procedimento, Integer idade, String sexo) {
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setProcedimento(new Procedimento(procedimento));
		autorizacao.setIdade(idade);
		autorizacao.setSexo(Sexo.valueOf(sexo));
		
		autorizacao = autorizacaoRepository.findOne(Example.of(autorizacao)).get();
		
		return autorizacao;
	}
	
	private Autorizacao criarObjetoAutorizacao(Integer procedimento, Integer idade, String sexo, String autoriza) throws NotFoundException {		
		Procedimento procedimentoRetorno = procedimentoRepository.findById(procedimento)
				.orElseThrow(() -> new NotFoundException(procedimento.toString()));
		Autorizacao autorizacao;
		try {
			autorizacao = consultar(procedimento, idade, sexo);
			autorizacao.setAutorizaString(autoriza);
		} catch (NoSuchElementException e) {
			autorizacao = new Autorizacao(procedimentoRetorno, idade, Sexo.valueOf(sexo), autoriza);
		}
		return autorizacao;
	}
}
