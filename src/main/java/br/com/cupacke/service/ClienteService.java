package br.com.cupacke.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cupacke.dao.ClienteDao;
import br.com.cupacke.dao.ClienteDaoImpl;
import br.com.cupacke.dto.ClienteAlterarDTO;
import br.com.cupacke.dto.ClienteDTO;
import br.com.cupacke.dto.ClienteInserirDTO;
import br.com.cupacke.dto.ClientePesquisarDTO;
import br.com.cupacke.entidade.Cliente;
import br.com.cupacke.entidade.Venda;
import br.com.cupacke.exception.CupackeException;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private ClienteDaoImpl clienteDaoImpl;
	
	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	public List<ClienteDTO> recuperarTodos() {
				
		return this.clienteDaoImpl.recuperarTodos();
		
	}
	
	
	/**
	 * Buscar por idCupcake
	 * @param idCupcake
	 */
	public ClienteDTO buscarPorIdCliente(final Long idCliente) throws CupackeException {
		
		if (idCliente == null) {
			throw new CupackeException("idCliente é obrigatório!");
		}
		
		ClienteDTO dto = this.clienteDaoImpl.buscarPorId(idCliente);
		if (dto == null) {
			throw new CupackeException("O idCliente não foi encontrado.");
		}
		
		return dto;
	}

	/**
	 * Inserir
	 * @param CupcakeInserirDTO
	 */
	@Transactional
	public void inserir(ClienteInserirDTO dto) throws CupackeException {
		
		if (dto == null) {
			throw new CupackeException("ClienteInserirDTO não pode ser nulo.");
		}
		
		if (dto.getNome() == null) {
			throw new CupackeException("Nome é obrigatória.");
		}
		
		if (dto.getEmail() == null) {
			throw new CupackeException("Email é obrigatória.");
		}
		
		if (dto.getCpf() == null) {
			throw new CupackeException("Cpf é obrigatório.");
		}
		
		if (dto.getDataNascimento() == null) {
			throw new CupackeException("Data Nascimento é obrigatório.");
		}
		
		if (dto.getCelular() == null) {
			throw new CupackeException("Celular é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Cliente entidade = modelMapper.map(dto, Cliente.class);
		
		// Gato - porque o modelMapper.map está jogando o mesmo valor de idSabor no campo idCupcake
		entidade.setIdCliente(null);
//		entidade.setDataNascimento(dto.getDataNascimento());
		entidade.setDataInclusao(new Date());
		entidade.setStatusAtivo(true);
		
		this.clienteDao.save(entidade);
		
	}
	
	
	/**
	 * Alterar
	 * @param CupcakeAlterarDTO
	 */
	@Transactional
	public void alterar(final ClienteAlterarDTO dto) throws CupackeException {
				
		if (dto.getIdCliente() == null) {
			throw new CupackeException("IdCupcake é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Cliente clienteAntes = this.clienteDao.findById(dto.getIdCliente()).get();
		if (clienteAntes == null || clienteAntes.getIdCliente() == null) {
			throw new CupackeException("IdCliente é inválido.");
		}
		
//		if (dto.getIdSabor() == null || (clienteAntes.getIdSabor() == null)) {
//			throw new CupackeException("IdSabor é obrigatório.");
//		}
//		
		if (dto.getNome() == null) {
			throw new CupackeException("Nome é obrigatório.");
		}
		
		if (dto.getEmail() == null) {
			throw new CupackeException("Email é obrigatório.");
		}
		
		if (dto.getCpf() == null) {
			throw new CupackeException("CPF é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Cliente entidade = modelMapper.map(dto, Cliente.class);
		
		entidade.setDataInclusao(clienteAntes.getDataInclusao());
		entidade.setDataUltimaAlteracao(new Date() );
		entidade.setStatusAtivo(true);
		
		this.clienteDao.save(entidade);
		
	}

	
	/**
	 * Excluir por IdCupcake.
	 * @param IdCupcake
	 */
	@Transactional
    public void excluir(final Long idCliente) throws CupackeException {
		
		if (idCliente == null) {
			throw new CupackeException("idCupcake é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Optional<Cliente> entidade = this.clienteDao.findById(idCliente);
		if (entidade == null) {
			throw new CupackeException("idCupcake é inválido.");
		}
		
   	  	this.clienteDao.deleteById(idCliente);
    }
	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	public List<ClienteDTO> pesquisar(final ClientePesquisarDTO clientePesquisarDTO) {
				
		return this.clienteDaoImpl.pesquisar(clientePesquisarDTO);
		
	}
	
	/**
	 * Desativar por idCliente.
	 * @param idVenda
	 */
	@Transactional
    public void desativar(final Long idCliente) throws CupackeException {
		
		if (idCliente == null) {
			throw new CupackeException("idCliente é obrigatório.");
		}
				
		//Faz a Consulta no Banco de Dados
		Cliente clienteAntes = this.clienteDao.findById(idCliente).get();
		if (clienteAntes == null || clienteAntes.getIdCliente() == null) {
			throw new CupackeException("idCliente é inválido.");
		}
		
		clienteAntes.setDataUltimaAlteracao(new Date() );
		clienteAntes.setStatusAtivo(false);
		
		this.clienteDao.save(clienteAntes);
		
    }


}
