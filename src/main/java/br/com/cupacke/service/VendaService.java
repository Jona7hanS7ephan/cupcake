package br.com.cupacke.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cupacke.dao.VendaDao;
import br.com.cupacke.dao.VendaDaoImpl;
import br.com.cupacke.dto.ItensVendaInserirDTO;
import br.com.cupacke.dto.ItensVendaInserirV2DTO;
import br.com.cupacke.dto.ListaVendaDTO;
import br.com.cupacke.dto.VendaAlterarDTO;
import br.com.cupacke.dto.VendaDTO;
import br.com.cupacke.dto.VendaInserirDTO;
import br.com.cupacke.dto.VendaInserirV2DTO;
import br.com.cupacke.dto.VendaPesquisarDTO;
import br.com.cupacke.entidade.Sabor;
import br.com.cupacke.entidade.Venda;
import br.com.cupacke.exception.CupackeException;

@Service
public class VendaService {
	
	
	@Autowired
	private VendaDao vendaDao;
	
	@Autowired
	private VendaDaoImpl vendaDaoImpl;
	
	@Autowired
	private ItensVendaService itensVendaService;
	
	
	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	public List<VendaDTO> recuperarTodos() {
				
		return this.vendaDaoImpl.recuperarTodos();
		
	}
	
	
	/**
	 * Buscar por idVenda
	 * @param idVenda
	 */
	public VendaDTO buscarPorIdVenda(final Long idVenda) throws CupackeException {
		
		if (idVenda == null) {
			throw new CupackeException("IdVenda é obrigatório!");
		}
		
		VendaDTO dto = this.vendaDaoImpl.buscarPorId(idVenda);
		if (dto == null) {
			throw new CupackeException("O IdVenda não foi encontrado.");
		}
		
		return dto;
	}

	/**
	 * Inserir
	 * @param VendaInserirDTO
	 */
	@Transactional
	public void inserir(VendaInserirDTO dto) throws CupackeException {
		
		if (dto == null) {
			throw new CupackeException("VendaInserirDTO não pode ser nulo.");
		}
		
		if (dto.getIdCliente() == null) {
			throw new CupackeException("IdCliente é obrigatório.");
		}
		
		if (dto.getValorTotalVenda() == null) {
			throw new CupackeException("Valor Total Venda é obrigatória.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Venda entidade = modelMapper.map(dto, Venda.class);
		
		// Gato - porque o modelMapper.map está jogando o mesmo valor de idSabor no campo idCupcake
		entidade.setIdVenda(null);
		entidade.setDataInclusao(new Date());
		entidade.setStatusAtivo(true);
		
		this.vendaDao.save(entidade);
		
	}
	
	public Venda inserirV2(VendaInserirV2DTO dto) throws CupackeException {
		
		if (dto == null) {
			throw new CupackeException("VendaInserirDTO não pode ser nulo.");
		}
		
		if (dto.getIdCliente() == null) {
			throw new CupackeException("IdCliente é obrigatório.");
		}
		
		if (dto.getValorTotalVenda() == null) {
			throw new CupackeException("Valor Total Venda é obrigatória.");
		}
		
		if (dto.getItensVenda() == null) {
			throw new CupackeException("Itens da Venda é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Venda entidade = modelMapper.map(dto, Venda.class);
		
		// Gato - porque o modelMapper.map está jogando o mesmo valor de idSabor no campo idCupcake
		entidade.setIdVenda(null);
		
		entidade.setDataInclusao(new Date());
		entidade.setStatusAtivo(true);
		
		entidade = this.vendaDao.save(entidade);
		
		// Inserir os itens da venda
		
		for (ItensVendaInserirV2DTO itensDTO : dto.getItensVenda()) {
			
			ItensVendaInserirDTO itensVendaInserirDTO  = modelMapper.map(itensDTO, ItensVendaInserirDTO.class);
			
			itensVendaInserirDTO.setIdVenda(entidade.getIdVenda()); // id da venda, inserido mais em cima
			
			this.itensVendaService.inserir(itensVendaInserirDTO);			
		}
		
		return entidade;
		
	}
	
	
	/**
	 * Alterar
	 * @param VendaAlterarDTO
	 */
	@Transactional
	public void alterar(final VendaAlterarDTO dto) throws CupackeException {
				
		if (dto.getIdVenda() == null) {
			throw new CupackeException("IdVenda é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Venda vendaAntes = this.vendaDao.findById(dto.getIdVenda()).get();
		if (vendaAntes == null || vendaAntes.getIdVenda() == null) {
			throw new CupackeException("IdVenda é inválido.");
		}
			
//		if (dto.getIdCliente() == null) {
//			throw new CupackeException("Nome é obrigatório.");
//		}
		
		if (dto.getValorTotalVenda() == null) {
			throw new CupackeException("Valor Total Venda é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Venda entidade = modelMapper.map(dto, Venda.class);
		
		entidade.setDataInclusao(vendaAntes.getDataInclusao());
		entidade.setDataUltimaAlteracao(new Date() );
		entidade.setStatusAtivo(true);
		
		this.vendaDao.save(entidade);
		
	}

	
	/**
	 * Excluir por idVenda.
	 * @param idVenda
	 */
	@Transactional
    public void excluir(final Long idVenda) throws CupackeException {
		
		if (idVenda == null) {
			throw new CupackeException("idVenda é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Optional<Venda> entidade = this.vendaDao.findById(idVenda);
		if (entidade == null) {
			throw new CupackeException("idCupcake é inválido.");
		}
		
   	  	this.vendaDao.deleteById(idVenda);
    }
	
	/**
	 * Retorna todas
	 *
	 * @return
	 */
	public List<VendaDTO> pesquisar(final VendaPesquisarDTO vendaPesquisarDTO) {
				
		return this.vendaDaoImpl.pesquisar(vendaPesquisarDTO);
		
	}

	/**
	 * Buscar por idVenda
	 * @param idVenda
	 */
	public List<ListaVendaDTO> buscarItensVendaPorIdVenda(final Long idVenda) throws CupackeException {
		
		if (idVenda == null) {
			throw new CupackeException("IdVenda é obrigatório!");
		}
		
		List<ListaVendaDTO> dto = this.vendaDaoImpl.buscarPorIdVenda(idVenda);
		if (dto == null) {
			throw new CupackeException("O IdVenda não foi encontrado.");
		}
		
		return dto;
	}
	
	/**
	 * Desativar por idVenda.
	 * @param idVenda
	 */
	@Transactional
    public void desativar(final Long idVenda) throws CupackeException {
		
		if (idVenda == null) {
			throw new CupackeException("idSabor é obrigatório.");
		}
				
		//Faz a Consulta no Banco de Dados
		Venda vendaAntes = this.vendaDao.findById(idVenda).get();
		if (vendaAntes == null || vendaAntes.getIdVenda() == null) {
			throw new CupackeException("idVenda é inválido.");
		}
		
		vendaAntes.setDataUltimaAlteracao(new Date() );
		vendaAntes.setStatusAtivo(false);
		
		this.vendaDao.save(vendaAntes);
		
    }

}
