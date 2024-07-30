package br.com.cupacke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import br.com.cupacke.entidade.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {


}
