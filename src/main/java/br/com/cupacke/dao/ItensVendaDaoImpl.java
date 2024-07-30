package br.com.cupacke.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.cupacke.dto.ItensVendaDTO;

@Repository
public class ItensVendaDaoImpl  extends AbstractDao {

	private static final long serialVersionUID = -1641676768119578829L;

	/**
	 * Retorna todos
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ItensVendaDTO> recuperarTodos() {
		String sql =
				" select                         "+
				" id_itens_venda, id_venda, id_cupcake, quantidade, valor, "+
				" valor_total, status_ativo, data_inclusao, data_ultima_alteracao "+
				" from cupcake.tb_itens_venda "+
				" order by id_itens_venda   ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<ItensVendaDTO> lista = listaResultado.stream().map(meuObjeto -> createItensVendaDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	
	/**
	 * Cria o dto: ClienteDTO.
	 *
	 * @param row
	 * @return
	 */
	private ItensVendaDTO createItensVendaDTO(final Object[] row) {
				
		final ItensVendaDTO dto = new ItensVendaDTO();
		
		if (row[0] != null)
			dto.setIdItensVenda(((BigInteger) row[0]).longValue());
		if (row[1] != null)
			dto.setIdVenda(((BigInteger) row[1]).longValue());
		if (row[2] != null)
			dto.setIdCupcake(((BigInteger) row[2]).longValue());
		if (row[3] != null)
			dto.setQuantidade((Integer) row[3]);
		if (row[4] != null)
			dto.setValor(((BigDecimal) row[4]).doubleValue());
		if (row[5] != null)
			dto.setValorTotal(((BigDecimal) row[5]).doubleValue());
		if (row[6] != null)
			dto.setStatusAtivo((Boolean) row[6]);
		if (row[7] != null)
			dto.setDataInclusao((Date) row[7]);		
		if (row[8] != null)
			dto.setDataUltimaAlteracao((Date) row[8]);		
		
		return dto;
	}
	
	/**
	 * Retorna por idItensVenda
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ItensVendaDTO buscarPorId(final Long idItensVenda) {
		String sql =
				" select                         "+
				" id_itens_venda, id_venda, id_cupcake, quantidade, valor, "+
				" valor_total, status_ativo, data_inclusao, data_ultima_alteracao "+
				" from cupcake.tb_itens_venda "+
				" where id_itens_venda = :id  ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("id", idItensVenda);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<ItensVendaDTO> lista = listaResultado.stream().map(meuObjeto -> createItensVendaDTO(meuObjeto)).collect(Collectors.toList());
		
		if (lista == null || lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
		
		
	}
	
//	@SuppressWarnings("unchecked")
//	public List<ClienteDTO> pesquisar(final ClientePesquisarDTO clientePesquisarDTO) {
//		
//		String sql =
//				" select                                "+
//				" id_cliente, nome, email, cpf, data_nascimento, "+
//				" celular, status_ativo, data_inclusao, data_ultima_alteracao "+
//				" from cupcake.tb_cliente   "+
//				" where status_ativo = true ";
//		
//		if (clientePesquisarDTO != null && clientePesquisarDTO.getNome() != null && !clientePesquisarDTO.getNome().isEmpty()) {
//			sql = sql + " and nome ilike :nome ";
//		}
//		
//		sql = sql + " order by nome   ";		
//		
//		final Query query = getEntityManager().createNativeQuery(sql);
//		
//		if (clientePesquisarDTO != null && clientePesquisarDTO.getNome() != null && !clientePesquisarDTO.getNome().isEmpty()) {
//			query.setParameter("nome", "%"+clientePesquisarDTO.getNome()+"%");			
//		}
//		
//		final List<Object[]> listaResultado = query.getResultList();
//		
//		final List<ClienteDTO> lista = listaResultado.stream().map(meuObjeto -> createClienteDTO(meuObjeto)).collect(Collectors.toList());
//		
//		return lista;
//		
//	}
	
}
