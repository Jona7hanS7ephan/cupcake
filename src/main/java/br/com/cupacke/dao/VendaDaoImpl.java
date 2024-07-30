package br.com.cupacke.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.cupacke.dto.ListaVendaDTO;
import br.com.cupacke.dto.VendaDTO;
import br.com.cupacke.dto.VendaPesquisarDTO;

@Repository
public class VendaDaoImpl  extends AbstractDao {

	private static final long serialVersionUID = -5117621853983560116L;

	/**
	 * Retorna todos
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VendaDTO> recuperarTodos() {
		String sql =
				" select                                                      		"+
				" v.id_venda, v.id_cliente, v.valor_total_venda,              		"+
				" v.status_ativo, v.data_inclusao, v.data_ultima_alteracao,   		"+
				" c.nome, lpad(cast(v.id_venda as varchar),6,'0') as numero_pedido  "+
				" from cupcake.tb_venda v                                     		"+
				" inner join cupcake.tb_cliente c                            		"+
				" on v.id_cliente = c.id_cliente                              		"+
				"where v.status_ativo = true 										"+
				" order by v.data_inclusao desc   ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<VendaDTO> lista = listaResultado.stream().map(meuObjeto -> createVendaDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	
	/**
	 * Cria o dto: VendaDTO.
	 *
	 * @param row
	 * @return
	 */
	private VendaDTO createVendaDTO(final Object[] row) {
				
		final VendaDTO dto = new VendaDTO();
		
		if (row[0] != null)
			dto.setIdVenda(((BigInteger) row[0]).longValue());
		if (row[1] != null)
			dto.setIdCliente(((BigInteger) row[1]).longValue());
		if (row[2] != null)
			dto.setValorTotalVenda((BigDecimal) row[2]);
		if (row[3] != null)
			dto.setStatusAtivo((Boolean) row[3]);
		if (row[4] != null)
			dto.setDataInclusao((Date) row[4]);		
		if (row[5] != null)
			dto.setDataUltimaAlteracao((Date) row[5]);
		if (row[6] != null)
			dto.setNomeCliente((String) row[6]);
		if (row[7] != null)
			dto.setNumeroPedido((String) row[7]);
		
		return dto;
	}
	
	/**
	 * Retorna por idVenda
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public VendaDTO buscarPorId(final Long idVenda) {
		String sql =
				" select                                                      "+
				" v.id_venda, v.id_cliente, v.valor_total_venda,              "+
				" v.status_ativo, v.data_inclusao, v.data_ultima_alteracao,   "+
				" c.nome, lpad(cast(v.id_venda as varchar),6,'0') as numero_pedido  "+
				" from cupcake.tb_venda v                                     "+
				" inner join cupcake.tb_cliente c                             "+
				" on v.id_cliente = c.id_cliente                              "+
				" where v.id_venda = :id ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("id", idVenda);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<VendaDTO> lista = listaResultado.stream().map(meuObjeto -> createVendaDTO(meuObjeto)).collect(Collectors.toList());
		
		if (lista == null || lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<VendaDTO> pesquisar(final VendaPesquisarDTO vendaPesquisarDTO) {
		
		String sql =		
				  " select                                                      "+
				  " v.id_venda, v.id_cliente, v.valor_total_venda,              "+
				  " v.status_ativo, v.data_inclusao, v.data_ultima_alteracao,   "+
				  " c.nome, lpad(cast(v.id_venda as varchar),6,'0') as numero_pedido  "+
				  " from cupcake.tb_venda v                                     "+
				  " inner join cupcake.tb_cliente c                             "+
				  " on v.id_cliente = c.id_cliente                              "+
				  " where v.status_ativo = true                                 ";
		  
		
		if (vendaPesquisarDTO != null && vendaPesquisarDTO.getDescricao() != null) {
			sql = sql + " and v.id_venda||c.nome ilike :descricao ";
		}
		
		sql = sql + " order by v.data_inclusao desc  ";		
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		if (vendaPesquisarDTO != null && vendaPesquisarDTO.getDescricao() != null) {
			query.setParameter("descricao", "%"+vendaPesquisarDTO.getDescricao()+"%");			
		}
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<VendaDTO> lista = listaResultado.stream().map(meuObjeto -> createVendaDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	/**
	 * Retorna lista por idVenda
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ListaVendaDTO> buscarPorIdVenda(final Long idVenda) {
		String sql =
				" select										"+
				" v.id_venda, iv.quantidade, iv.valor, iv.valor_total,		"+
				" c.descricao as cupcake, c.quantidade_estoque, c.preco,	"+
				" s.descricao as sabor, v.valor_total_venda, cl.nome		"+
				" from cupcake.tb_itens_venda iv     			"+ 
				" inner join cupcake.tb_cupcake c				"+
				" on c.id_cupcake = iv.id_cupcake				"+
				" inner join cupcake.tb_sabor s					"+
				" on s.id_sabor = c.id_sabor					"+
				" inner join cupcake.tb_venda v					"+
				" on v.id_venda = iv.id_venda					"+
				" inner join cupcake.tb_cliente cl				"+
				" on cl.id_cliente = v.id_cliente				"+
				" where v.id_venda = :id						";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("id", idVenda);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<ListaVendaDTO> lista = listaResultado.stream().map(meuObjeto -> createListaVendaDTO(meuObjeto)).collect(Collectors.toList());
		
		if (lista == null || lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
		
		
	}
	
	/**
	 * Cria o dto: ListaVendaDTO.
	 *
	 * @param row
	 * @return
	 */
	private ListaVendaDTO createListaVendaDTO(final Object[] row) {
				
		final ListaVendaDTO dto = new ListaVendaDTO();
		
		if (row[0] != null)
			dto.setIdVenda(((BigInteger) row[0]).longValue());
		if (row[1] != null)
			dto.setQuantidade((Integer) row[1]);
		if (row[2] != null)
			dto.setValor(((BigDecimal) row[2]));
		if (row[3] != null)
			dto.setValorTotal(((BigDecimal) row[3]));
		if (row[4] != null)
			dto.setCupcake((String) row[4]);
		if (row[5] != null)
			dto.setQuantidadeEstoque(((Integer) row[5]));		
		if (row[6] != null)
			dto.setPreco(((BigDecimal) row[6]));
		if (row[7] != null)
			dto.setSabor((String) row[7]);
		if (row[8] != null)
			dto.setValorTotalVenda((BigDecimal) row[8]);
		if (row[9] != null)
			dto.setNomeCliente((String) row[9]);
		
		
		return dto;
	}
	
	
}
