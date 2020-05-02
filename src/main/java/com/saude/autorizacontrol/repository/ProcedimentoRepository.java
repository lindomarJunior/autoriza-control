package com.saude.autorizacontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saude.autorizacontrol.entity.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer> {

}
