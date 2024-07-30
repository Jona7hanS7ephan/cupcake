package br.com.cupacke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.cupacke.entidade.Cupcake;

public interface CupcakeDao extends JpaRepository<Cupcake, Long>, JpaSpecificationExecutor<Cupcake> {

	// Foi Utilizado "Query methods"
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	// Exemplos:
	// List<Produto> findByIdSolicitacaoLeilaoTerceiro(Long idSolicitacaoLeilaoTerceiro);	
	// List<Produto> findByIdSolicitacaoLeilaoTerceiroAndIdNotIn(Long idSolicitacaoLeilaoTerceiro, List<Long> listaIds);	
	// List<Produto> findByIdSolicitacaoLeilaoTerceiroOrderByDataInclusaoDesc(Long idSolicitacaoLeilaoTerceiro);

}
