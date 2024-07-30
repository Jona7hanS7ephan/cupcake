package br.com.cupacke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.cupacke.entidade.ItensVenda;

public interface ItensVendaDao extends JpaRepository<ItensVenda, Long>, JpaSpecificationExecutor<ItensVenda> {


}
