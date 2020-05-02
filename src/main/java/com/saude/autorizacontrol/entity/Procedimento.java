package com.saude.autorizacontrol.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "procedimento")
public class Procedimento {
	@Id
	private Integer codigo;
	
	public Procedimento() {}
	
	public Procedimento(Integer codigo) {
		super();
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
}
