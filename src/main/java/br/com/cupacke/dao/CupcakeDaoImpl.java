package br.com.cupacke.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.cupacke.dto.CupcakeDTO;
import br.com.cupacke.dto.CupcakeMaisVendidosDTO;
import br.com.cupacke.dto.CupcakePesquisarDTO;

@Repository
public class CupcakeDaoImpl  extends AbstractDao {

	
	private static final long serialVersionUID = 2469026542105081492L;

	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CupcakeDTO> recuperarTodos() {
		String sql =
				" select                                                              "+
				" c.id_cupcake, c.id_sabor, c.descricao, c.quantidade_estoque,        "+
				" c.preco, c.status_ativo, c.data_inclusao, c.data_ultima_alteracao,  "+
				" s.descricao as sabor                                                "+
				" from cupcake.tb_cupcake c     									  "+
				" inner join cupcake.tb_sabor s 									  "+
				" on c.id_sabor = s.id_sabor    									  "+
				" where c.status_ativo = true 										  "+	
				" order by s.descricao          ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<CupcakeDTO> lista = listaResultado.stream().map(meuObjeto -> createCupcakeDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	
	/**
	 * Cria o dto: CupcakeDTO.
	 *
	 * @param row
	 * @return
	 */
	private CupcakeDTO createCupcakeDTO(final Object[] row) {
				
		final CupcakeDTO dto = new CupcakeDTO();
		
		if (row[0] != null)
			dto.setIdCupcake(((BigInteger) row[0]).longValue());
		if (row[1] != null)
			dto.setIdSabor(((BigInteger) row[1]).longValue());
		if (row[2] != null)
			dto.setDescricao((String) row[2]);
		if (row[3] != null)
			dto.setQuantidadeEstoque((Integer) row[3]);
		if (row[4] != null)
			dto.setPreco((BigDecimal) row[4]);
		if (row[5] != null)
			dto.setStatusAtivo((Boolean) row[5]);
		if (row[6] != null)
			dto.setDataInclusao((Date) row[6]);		
		if (row[7] != null)
			dto.setDataUltimaAlteracao((Date) row[7]);
		if (row[8] != null)
			dto.setSabor((String) row[8]);
		
		return dto;
	}
	
	/**
	 * Retorna por idCupcake
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CupcakeDTO buscarPorId(final Long idCupcake) {
		String sql =				
				" select                                                              "+
				" c.id_cupcake, c.id_sabor, c.descricao, c.quantidade_estoque,        "+
				" c.preco, c.status_ativo, c.data_inclusao, c.data_ultima_alteracao,  "+
				" s.descricao as sabor                                                "+
				" from cupcake.tb_cupcake c     "+
				" inner join cupcake.tb_sabor s "+
				" on c.id_sabor = s.id_sabor    "+  
				" where c.id_cupcake = :id ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("id", idCupcake);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<CupcakeDTO> lista = listaResultado.stream().map(meuObjeto -> createCupcakeDTO(meuObjeto)).collect(Collectors.toList());
		
		if (lista == null || lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
		
		
	}
	
	/**
	 * Retorna por idSabor
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CupcakeDTO buscarPorIdSabor(final Long idSabor) {
		String sql =				
				" select                                                              "+
				" c.id_cupcake, c.id_sabor, c.descricao, c.quantidade_estoque,        "+
				" c.preco, c.status_ativo, c.data_inclusao, c.data_ultima_alteracao,  "+
				" s.descricao as sabor                                                "+
				" from cupcake.tb_cupcake c     "+
				" inner join cupcake.tb_sabor s "+
				" on c.id_sabor = s.id_sabor    "+  
				" where c.id_sabor = :idSabor ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("idSabor", idSabor);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<CupcakeDTO> lista = listaResultado.stream().map(meuObjeto -> createCupcakeDTO(meuObjeto)).collect(Collectors.toList());
		
		if (lista == null || lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CupcakeDTO> pesquisar(final CupcakePesquisarDTO cupcakePesquisarDTO) {
		
		String sql =
				" select                                                              "+
				" c.id_cupcake, c.id_sabor, c.descricao, c.quantidade_estoque,        "+
				" c.preco, c.status_ativo, c.data_inclusao, c.data_ultima_alteracao,  "+
				" s.descricao as sabor                                                "+
				" from cupcake.tb_cupcake c     "+
				" inner join cupcake.tb_sabor s "+
				" on c.id_sabor = s.id_sabor    "+  
				" where c.status_ativo = true   ";
		
		if (cupcakePesquisarDTO != null && cupcakePesquisarDTO.getDescricao() != null && !cupcakePesquisarDTO.getDescricao().isEmpty()) {
			sql = sql + " and s.descricao||c.descricao ilike :descricao ";
		}
		
		sql = sql + " order by c.descricao   ";		
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		if (cupcakePesquisarDTO != null && cupcakePesquisarDTO.getDescricao() != null && !cupcakePesquisarDTO.getDescricao().isEmpty()) {
			query.setParameter("descricao", "%"+cupcakePesquisarDTO.getDescricao()+"%");			
		}
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<CupcakeDTO> lista = listaResultado.stream().map(meuObjeto -> createCupcakeDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CupcakeDTO> pesquisarPorSabor(final CupcakePesquisarDTO cupcakePesquisarDTO) {
		
		String sql =
				" select                                                              "+
				" c.id_cupcake, c.id_sabor, c.descricao, c.quantidade_estoque,        "+
				" c.preco, c.status_ativo, c.data_inclusao, c.data_ultima_alteracao,  "+
				" s.descricao as sabor                                                "+
				" from cupcake.tb_cupcake c     "+
				" inner join cupcake.tb_sabor s "+
				" on c.id_sabor = s.id_sabor    "+  
				" where c.status_ativo = true   ";
		
		if (cupcakePesquisarDTO != null && cupcakePesquisarDTO.getDescricao() != null && !cupcakePesquisarDTO.getDescricao().isEmpty()) {
			sql = sql + " and s.descricao ilike :descricao ";  // Pesquisa pela descrição do Sabor
		}
		
		sql = sql + " order by s.descricao   ";		
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		if (cupcakePesquisarDTO != null && cupcakePesquisarDTO.getDescricao() != null && !cupcakePesquisarDTO.getDescricao().isEmpty()) {
			query.setParameter("descricao", "%"+cupcakePesquisarDTO.getDescricao()+"%");			
		}
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<CupcakeDTO> lista = listaResultado.stream().map(meuObjeto -> createCupcakeDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CupcakeMaisVendidosDTO
	> maisVendidos() {
		
		String sql =
				" select                                            "+
				" sum(iv.quantidade) as qtd_vendidos,               "+
				" iv.id_cupcake, s.id_sabor, s.descricao as sabor,  "+
				" c.descricao, c.quantidade_estoque, c.preco        "+
				" from cupcake.tb_itens_venda iv                    "+
				" inner join cupcake.tb_cupcake c                   "+
				" on iv.id_cupcake = c.id_cupcake                   "+
				" and c.status_ativo = true                         "+
				" inner join cupcake.tb_sabor s                     "+
				" on c.id_sabor = s.id_sabor                        "+
				" and s.status_ativo = true                         "+
				" group by iv.id_cupcake, s.id_sabor, s.descricao,  "+
				" c.descricao, c.quantidade_estoque, c.preco        "+
				" order by 1 desc                                   "+
				" limit 6	                                        ";
		
		final Query query = getEntityManager().createNativeQuery(sql);

		final List<Object[]> listaResultado = query.getResultList();
		
		final List<CupcakeMaisVendidosDTO> lista = listaResultado.stream().map(meuObjeto -> createCupcakeMaisVendidosDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	/**
	 * Cria o dto: CupcakeMaisVendidosDTO.
	 *
	 * @param row
	 * @return
	 */
	private CupcakeMaisVendidosDTO createCupcakeMaisVendidosDTO(final Object[] row) {
				
		final CupcakeMaisVendidosDTO dto = new CupcakeMaisVendidosDTO();
		
		if (row[0] != null)
			dto.setQuantidadeVendidos( ((BigInteger) row[0]).intValue() );
		if (row[1] != null)
			dto.setIdCupcake(((BigInteger) row[1]).longValue());
		if (row[2] != null)
			dto.setIdSabor(((BigInteger) row[2]).longValue());
		if (row[3] != null)
			dto.setSabor((String) row[3]);
		if (row[4] != null)
			dto.setDescricao((String) row[4]);
		if (row[5] != null)
			dto.setQuantidadeEstoque((Integer) row[5]);
		if (row[6] != null)
			dto.setPreco((BigDecimal) row[6]);	
		
		return dto;
	}
	
	
	
	
	
}
