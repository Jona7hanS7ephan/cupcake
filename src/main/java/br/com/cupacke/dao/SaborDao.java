package br.com.cupacke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.cupacke.entidade.Sabor;

public interface SaborDao extends JpaRepository<Sabor, Long>, JpaSpecificationExecutor<Sabor> {

	// Foi Utilizado "Query methods"
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	// Exemplos:
	// List<Produto> findByIdSolicitacaoLeilaoTerceiro(Long idSolicitacaoLeilaoTerceiro);	
	// List<Produto> findByIdSolicitacaoLeilaoTerceiroAndIdNotIn(Long idSolicitacaoLeilaoTerceiro, List<Long> listaIds);	
	// List<Produto> findByIdSolicitacaoLeilaoTerceiroOrderByDataInclusaoDesc(Long idSolicitacaoLeilaoTerceiro);
	
	//List<Sabor> findByIdSabor(Long idSabor);

}
