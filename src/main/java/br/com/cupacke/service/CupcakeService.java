package br.com.cupacke.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cupacke.dao.CupcakeDao;
import br.com.cupacke.dao.CupcakeDaoImpl;
import br.com.cupacke.dto.CupcakeAlterarDTO;
import br.com.cupacke.dto.CupcakeDTO;
import br.com.cupacke.dto.CupcakeInserirDTO;
import br.com.cupacke.dto.CupcakeMaisVendidosDTO;
import br.com.cupacke.dto.CupcakePesquisarDTO;
import br.com.cupacke.entidade.Cliente;
import br.com.cupacke.entidade.Cupcake;
import br.com.cupacke.exception.CupackeException;

@Service
public class CupcakeService {
	
	
	@Autowired
	private CupcakeDao cupcakeDao;
	
	@Autowired
	private CupcakeDaoImpl cupcakeDaoImpl;
	
	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	public List<CupcakeDTO> recuperarTodos() {
				
		return this.cupcakeDaoImpl.recuperarTodos();
		
	}
	
	
	/**
	 * Buscar por idCupcake
	 * @param idCupcake
	 */
	public CupcakeDTO buscarPorIdCupcake(final Long idCupcake) throws CupackeException {
		
		if (idCupcake == null) {
			throw new CupackeException("idCupcake é obrigatório!");
		}
		
		CupcakeDTO dto = this.cupcakeDaoImpl.buscarPorId(idCupcake);
		if (dto == null) {
			throw new CupackeException("O idCupcake não foi encontrado.");
		}
		
		return dto;
	}
	
	/**
	 * Buscar por idSabor
	 * @param idSabor
	 */
	public CupcakeDTO buscarPorIdSabor(final Long idSabor) throws CupackeException {
		
		if (idSabor == null) {
			throw new CupackeException("idSabor é obrigatório!");
		}
		
		CupcakeDTO dto = this.cupcakeDaoImpl.buscarPorIdSabor(idSabor);
		if (dto == null) {
			throw new CupackeException("O cupcake com o idSabor "+idSabor+" não foi encontrado.");
		}
		
		return dto;
	}

	/**
	 * Inserir
	 * @param CupcakeInserirDTO
	 */
	@Transactional
	public void inserir(CupcakeInserirDTO dto) throws CupackeException {
		
		if (dto == null) {
			throw new CupackeException("CupcakeInserirDTO não pode ser nulo.");
		}
		
		if (dto.getDescricao() == null) {
			throw new CupackeException("Descricao é obrigatória.");
		}
		
		if (dto.getQuantidadeEstoque() == null) {
			throw new CupackeException("Quantidade de estoque é obrigatória.");
		}
		
		if (dto.getPreco() == null) {
			throw new CupackeException("Preço é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Cupcake entidade = modelMapper.map(dto, Cupcake.class);
		
		// Gato - porque o modelMapper.map está jogando o mesmo valor de idSabor no campo idCupcake
		entidade.setIdCupcake(null);
		
		entidade.setDataInclusao(new Date());
		entidade.setStatusAtivo(true);
		
		this.cupcakeDao.save(entidade);
		
	}
	
	
	/**
	 * Alterar
	 * @param CupcakeAlterarDTO
	 */
	@Transactional
	public void alterar(final CupcakeAlterarDTO dto) throws CupackeException {
				
		if (dto.getIdCupcake() == null) {
			throw new CupackeException("IdCupcake é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Cupcake cupcakeAntes = this.cupcakeDao.findById(dto.getIdCupcake()).get();
		if (cupcakeAntes == null || cupcakeAntes.getIdCupcake() == null) {
			throw new CupackeException("IdCupcake é inválido.");
		}
		
		if (dto.getIdSabor() == null || (cupcakeAntes.getIdSabor() == null)) {
			throw new CupackeException("IdSabor é obrigatório.");
		}
		
		if (dto.getDescricao() == null) {
			throw new CupackeException("Descrição é obrigatória.");
		}
		
		if (dto.getQuantidadeEstoque() == null) {
			throw new CupackeException("Quantidade de estoque é obrigatória.");
		}
		
		if (dto.getPreco() == null) {
			throw new CupackeException("Preço é obrigatório.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Cupcake entidade = modelMapper.map(dto, Cupcake.class);
		
		entidade.setDataInclusao(cupcakeAntes.getDataInclusao());
		entidade.setDataUltimaAlteracao(new Date() );
		entidade.setStatusAtivo(true);
		
		this.cupcakeDao.save(entidade);
		
	}

	
	/**
	 * Excluir por IdCupcake.
	 * @param IdCupcake
	 */
	@Transactional
    public void excluir(final Long idCupcake) throws CupackeException {
		
		if (idCupcake == null) {
			throw new CupackeException("idCupcake é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Optional<Cupcake> entidade = this.cupcakeDao.findById(idCupcake);
		if (entidade == null) {
			throw new CupackeException("idCupcake é inválido.");
		}
		
   	  	this.cupcakeDao.deleteById(idCupcake);
    }
	
	/**
	 * Pesquisar
	 *
	 * @return
	 */
	public List<CupcakeDTO> pesquisar(final CupcakePesquisarDTO cupcakePesquisarDTO) {
				
		return this.cupcakeDaoImpl.pesquisar(cupcakePesquisarDTO);
		
	}
	
	/**
	 * Pesquisar por Sabor
	 *
	 * @return
	 */
	public List<CupcakeDTO> pesquisarPorSabor(final CupcakePesquisarDTO cupcakePesquisarDTO) {
				
		return this.cupcakeDaoImpl.pesquisarPorSabor(cupcakePesquisarDTO);
		
	}
	
	/**
	 * Pesquisar por Sabor e pela Descrição do Cupcake
	 *
	 * @return
	 */
	public List<CupcakeDTO> pesquisaAvancada(final CupcakePesquisarDTO cupcakePesquisarDTO) {
		
		
		List<CupcakeDTO> primeiraLista = this.cupcakeDaoImpl.pesquisarPorSabor(cupcakePesquisarDTO);
		List<CupcakeDTO> segundaLista = this.cupcakeDaoImpl.pesquisar(cupcakePesquisarDTO);
		
		if (segundaLista != null && !segundaLista.isEmpty()) {
			for (CupcakeDTO cupcakeDTOSegunda : segundaLista) {		
				
				boolean existeNaPrimeiraLista = false;
				if (primeiraLista != null && !primeiraLista.isEmpty()) {
					for (CupcakeDTO cupcakeDTOPrimeira : primeiraLista) {
						
						if (cupcakeDTOPrimeira.getIdCupcake() == cupcakeDTOSegunda.getIdCupcake()) {
							existeNaPrimeiraLista = true;
							break;
						}
						
					}
				}
				if (existeNaPrimeiraLista == false) {
					primeiraLista.add(cupcakeDTOSegunda);
				}
				
			}
		}
		
		return primeiraLista;
		
		
	}
	
	/**
	 * Retorna os mais vendidos
	 *
	 * @return
	 */
	public List<CupcakeMaisVendidosDTO> maisVendidos() {
				
		return this.cupcakeDaoImpl.maisVendidos();
		
	}

	/**
	 * Desativar por idCupcake.
	 * @param idVenda
	 */
	@Transactional
    public void desativar(final Long idCupcake) throws CupackeException {
		
		if (idCupcake == null) {
			throw new CupackeException("idCupcake é obrigatório.");
		}
				
		//Faz a Consulta no Banco de Dados
		Cupcake cupcakeAntes = this.cupcakeDao.findById(idCupcake).get();
		if (cupcakeAntes == null || cupcakeAntes.getIdCupcake() == null) {
			throw new CupackeException("idCupcake é inválido.");
		}
		
		cupcakeAntes.setDataUltimaAlteracao(new Date() );
		cupcakeAntes.setStatusAtivo(false);
		
		this.cupcakeDao.save(cupcakeAntes);
		
    }

}
