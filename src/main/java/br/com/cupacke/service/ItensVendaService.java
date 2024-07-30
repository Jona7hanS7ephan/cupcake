package br.com.cupacke.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cupacke.dao.ItensVendaDao;
import br.com.cupacke.dao.ItensVendaDaoImpl;
import br.com.cupacke.dto.ItensVendaAlterarDTO;
import br.com.cupacke.dto.ItensVendaDTO;
import br.com.cupacke.dto.ItensVendaInserirDTO;
import br.com.cupacke.entidade.ItensVenda;
import br.com.cupacke.exception.CupackeException;

@Service
public class ItensVendaService {
	
	
	@Autowired
	private ItensVendaDao itensVendaDao;
	
	@Autowired
	private ItensVendaDaoImpl itensVendaDaoImpl;
	
	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	public List<ItensVendaDTO> recuperarTodos() {
				
		return this.itensVendaDaoImpl.recuperarTodos();
		
	}
	
	
	/**
	 * Buscar por idItensVenda
	 * @param idItensVenda
	 */
	public ItensVendaDTO buscarPorIdCliente(final Long idItensVenda) throws CupackeException {
		
		if (idItensVenda == null) {
			throw new CupackeException("idItensVenda é obrigatório!");
		}
		
		ItensVendaDTO dto = this.itensVendaDaoImpl.buscarPorId(idItensVenda);
		if (dto == null) {
			throw new CupackeException("O idItensVenda não foi encontrado.");
		}
		
		return dto;
	}

	/**
	 * Inserir
	 * @param ItensVendaInserirDTO
	 */
	@Transactional
	public void inserir(ItensVendaInserirDTO dto) throws CupackeException {
		
		if (dto == null) {
			throw new CupackeException("ItensVendaInserirDTO não pode ser nulo.");
		}
		
		if (dto.getIdVenda() == null) {
			throw new CupackeException("IdVenda é obrigatória.");
		}
		
		if (dto.getIdCupcake() == null) {
			throw new CupackeException("IdCupcake é obrigatória.");
		}
		
		if (dto.getQuantidade() == null) {
			throw new CupackeException("Quantidade é obrigatória.");
		}
		
		if (dto.getValor() == null) {
			throw new CupackeException("Valor é obrigatório.");
		}
		
		if (dto.getValorTotal() == null) {
			throw new CupackeException("Valor Total é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		ItensVenda entidade = modelMapper.map(dto, ItensVenda.class);
		
		// Gato - porque o modelMapper.map está jogando o mesmo valor de idSabor no campo idCupcake
		entidade.setIdItensVenda(null);
		entidade.setDataInclusao(new Date());
		entidade.setStatusAtivo(true);
		
		this.itensVendaDao.save(entidade);
		
	}
	
	
	/**
	 * Alterar
	 * @param ItensVendaAlterarDTO
	 */
	@Transactional
	public void alterar(final ItensVendaAlterarDTO dto) throws CupackeException {
				
		if (dto.getIdItensVenda() == null) {
			throw new CupackeException("idItensVenda é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		ItensVenda itensVendaAntes = this.itensVendaDao.findById(dto.getIdItensVenda()).get();
		if (itensVendaAntes == null || itensVendaAntes.getIdItensVenda() == null) {
			throw new CupackeException("idItensVenda é inválido.");
		}
			
		if (dto.getQuantidade() == null) {
			throw new CupackeException("Quantidade é obrigatório.");
		}
		
		if (dto.getValor() == null) {
			throw new CupackeException("Valor é obrigatório.");
		}
		
		if (dto.getValorTotal() == null) {
			throw new CupackeException("Valor Total é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		ItensVenda entidade = modelMapper.map(dto, ItensVenda.class);
		
		entidade.setDataInclusao(itensVendaAntes.getDataInclusao());
		entidade.setDataUltimaAlteracao(new Date() );
		entidade.setStatusAtivo(true);
		
		this.itensVendaDao.save(entidade);
		
	}

	
	/**
	 * Excluir por idItensVenda.
	 * @param idItensVenda
	 */
	@Transactional
    public void excluir(final Long idItensVenda) throws CupackeException {
		
		if (idItensVenda == null) {
			throw new CupackeException("idItensVenda é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Optional<ItensVenda> entidade = this.itensVendaDao.findById(idItensVenda);
		if (entidade == null) {
			throw new CupackeException("idItensVenda é inválido.");
		}
		
   	  	this.itensVendaDao.deleteById(idItensVenda);
    }
	
	/**
	 * Desativar por idSabor.
	 * @param idSabor
	 */
	@Transactional
    public void desativar(final Long idItensVenda) throws CupackeException {
		
		if (idItensVenda == null) {
			throw new CupackeException("idItensVenda é obrigatório.");
		}
				
		//Faz a Consulta no Banco de Dados
		ItensVenda itensVendaAntes = this.itensVendaDao.findById(idItensVenda).get();
		if (itensVendaAntes == null || itensVendaAntes.getIdItensVenda() == null) {
			throw new CupackeException("idItensVenda é inválido.");
		}
		
		itensVendaAntes.setDataUltimaAlteracao(new Date() );
		itensVendaAntes.setStatusAtivo(false);
		
		this.itensVendaDao.save(itensVendaAntes);
		
    }
	
	
//	/**
//	 * Retorna todos
//	 *
//	 * @return
//	 */
//	public List<ClienteDTO> pesquisar(final ClientePesquisarDTO clientePesquisarDTO) {
//				
//		return this.clienteDaoImpl.pesquisar(clientePesquisarDTO);
//		
//	}
//

}
