package br.com.cupacke.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.cupacke.dto.ClienteDTO;
import br.com.cupacke.dto.ClientePesquisarDTO;

@Repository
public class ClienteDaoImpl  extends AbstractDao {

	private static final long serialVersionUID = 111468962723441730L;


	/**
	 * Retorna todos
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClienteDTO> recuperarTodos() {
		String sql =
				" select                         								"+
				" id_cliente, nome, email, cpf, data_nascimento, 				"+
				" celular, status_ativo, data_inclusao, data_ultima_alteracao 	"+
				" from cupcake.tb_cliente   									"+
				" where status_ativo = true										"+
				" order by id_cliente   ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<ClienteDTO> lista = listaResultado.stream().map(meuObjeto -> createClienteDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
	
	/**
	 * Cria o dto: ClienteDTO.
	 *
	 * @param row
	 * @return
	 */
	private ClienteDTO createClienteDTO(final Object[] row) {
				
		final ClienteDTO dto = new ClienteDTO();
		
		if (row[0] != null)
			dto.setIdCliente(((BigInteger) row[0]).longValue());
		if (row[1] != null)
			dto.setNome(((String) row[1]));
		if (row[2] != null)
			dto.setEmail((String) row[2]);
		if (row[3] != null)
			dto.setCpf((String) row[3]);
		if (row[4] != null)
			dto.setDataNascimento((Date) row[4]);
		if (row[5] != null)
			dto.setCelular((String) row[5]);
		if (row[6] != null)
			dto.setStatusAtivo((Boolean) row[6]);
		if (row[7] != null)
			dto.setDataInclusao((Date) row[7]);		
		if (row[8] != null)
			dto.setDataUltimaAlteracao((Date) row[8]);		
		
		return dto;
	}
	
	/**
	 * Retorna por idCupcake
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ClienteDTO buscarPorId(final Long id_cliente) {
		String sql =
				" select                         "+
				" id_cliente, nome, email, cpf, data_nascimento, "+
				" celular, status_ativo, data_inclusao, data_ultima_alteracao "+
				" from cupcake.tb_cliente   "+
				" where id_cliente = :id ";
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("id", id_cliente);
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<ClienteDTO> lista = listaResultado.stream().map(meuObjeto -> createClienteDTO(meuObjeto)).collect(Collectors.toList());
		
		if (lista == null || lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteDTO> pesquisar(final ClientePesquisarDTO clientePesquisarDTO) {
		
		String sql =
				" select                                "+
				" id_cliente, nome, email, cpf, data_nascimento, "+
				" celular, status_ativo, data_inclusao, data_ultima_alteracao "+
				" from cupcake.tb_cliente   "+
				" where status_ativo = true ";
		
		if (clientePesquisarDTO != null && clientePesquisarDTO.getNome() != null && !clientePesquisarDTO.getNome().isEmpty()) {
			sql = sql + " and nome ilike :nome ";
		}
		
		sql = sql + " order by nome   ";		
		
		final Query query = getEntityManager().createNativeQuery(sql);
		
		if (clientePesquisarDTO != null && clientePesquisarDTO.getNome() != null && !clientePesquisarDTO.getNome().isEmpty()) {
			query.setParameter("nome", "%"+clientePesquisarDTO.getNome()+"%");			
		}
		
		final List<Object[]> listaResultado = query.getResultList();
		
		final List<ClienteDTO> lista = listaResultado.stream().map(meuObjeto -> createClienteDTO(meuObjeto)).collect(Collectors.toList());
		
		return lista;
		
	}
	
}
