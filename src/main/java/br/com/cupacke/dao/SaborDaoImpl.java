package br.com.cupacke.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import br.com.cupacke.dto.SaborDTO;
import br.com.cupacke.dto.SaborPesquisarDTO;

@Repository
public class SaborDaoImpl  extends AbstractDao {
	
	
	private static final long serialVersionUID = -1323218316044390108L;

	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SaborDTO> recuperarTodos() {
		String sql =
				" select                                "+
				" id_sabor, descricao, status_ativo,    "+
				" data_inclusao, data_ultima_alteracao  "+
				" from cupcake.tb_sabor     "+
				" where status_ativo = true "+
				" order by descricao   ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<SaborDTO> lista = listaResultado.stream().map(meuObjeto -> createSaborDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	
	/**
	 * Cria o dto: SaborDTO.
	 *
	 * @param row
	 * @return
	 */
	private SaborDTO createSaborDTO(final Object[] row) {
				
		final SaborDTO dto = new SaborDTO();
		
		if (row[0] != null)
			dto.setIdSabor(((BigInteger) row[0]).longValue());
		if (row[1] != null)
			dto.setDescricao((String) row[1]);
		if (row[2] != null)
			dto.setStatusAtivo((Boolean) row[2]);	
		if (row[3] != null)
			dto.setDataInclusao((Date) row[3]);		
		if (row[4] != null)
			dto.setDataUltimaAlteracao((Date) row[4]);		
		
		return dto;
	}
	
	/**
	 * Retorna por idNewsletter
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SaborDTO buscarPorIdSabor(final Long idSabor) {
		String sql =
				" select                               "+
				" id_sabor, descricao, status_ativo,   "+
				" data_inclusao, data_ultima_alteracao "+
				" from cupcake.tb_sabor   "+
				" where id_sabor = :id    ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("id", idSabor);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<SaborDTO> lista = listaResultado.stream().map(meuObjeto -> createSaborDTO(meuObjeto)).collect(Collectors.toList());
		
		if (lista == null || lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
		
		
	}
	
	/**
	 * Retorna conforme a pesquisa
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SaborDTO> pesquisar(final SaborPesquisarDTO saborPesquisarDTO) {
		
		String sql =
				" select                                "+
				" id_sabor, descricao, status_ativo,    "+
				" data_inclusao, data_ultima_alteracao  "+
				" from cupcake.tb_sabor     "+
				" where status_ativo = true ";
		
		if (saborPesquisarDTO != null && saborPesquisarDTO.getDescricao() != null && !saborPesquisarDTO.getDescricao().isEmpty()) {
			sql = sql + " and descricao ilike :descricao ";
		}
		
		sql = sql + " order by descricao   ";		
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		if (saborPesquisarDTO != null && saborPesquisarDTO.getDescricao() != null && !saborPesquisarDTO.getDescricao().isEmpty()) {
			query.setParameter("descricao", "%"+saborPesquisarDTO.getDescricao()+"%");			
		}
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<SaborDTO> lista = listaResultado.stream().map(meuObjeto -> createSaborDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
}
