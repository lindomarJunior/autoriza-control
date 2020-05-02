package com.saude.autorizacontrol.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.saude.autorizacontrol.enums.Sexo;

@Entity
@Table(name = "autorizacao")
public class Autorizacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Procedimento procedimento;
	private Integer idade;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private Boolean autoriza;
	
	public Autorizacao() {
		super();
	}
	
	public Autorizacao(Procedimento procedimento, Integer idade, Sexo sexo, String autoriza) {
		super();
		this.procedimento = procedimento;
		this.idade = idade;
		this.sexo = sexo;
		setAutorizaString(autoriza);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Boolean getAutoriza() {
		return autoriza;
	}
	public void setAutoriza(Boolean autoriza) {
		this.autoriza = autoriza;
	}
	public String getAutorizaString() {
		return autoriza == true ? "SIM" : "NAO";
	}
	public void setAutorizaString(String autoriza) {
		this.autoriza = autoriza.equals("SIM") ? true : false;
	}	
}
