package br.com.cupacke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.cupacke.entidade.Venda;

public interface VendaDao extends JpaRepository<Venda, Long>, JpaSpecificationExecutor<Venda> {


}
