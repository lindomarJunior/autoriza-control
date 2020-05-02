package com.saude.autorizacontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saude.autorizacontrol.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {

}
