package br.com.cupacke.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cupacke.dao.SaborDao;
import br.com.cupacke.dao.SaborDaoImpl;
import br.com.cupacke.dto.SaborAlterarDTO;
import br.com.cupacke.dto.SaborDTO;
import br.com.cupacke.dto.SaborInserirDTO;
import br.com.cupacke.dto.SaborPesquisarDTO;
import br.com.cupacke.entidade.Sabor;
import br.com.cupacke.exception.CupackeException;

@Service
public class SaborService {
	
	
	@Autowired
	private SaborDao saborDao;
	
	@Autowired
	private SaborDaoImpl saborDaoImpl;
	
	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	public List<SaborDTO> recuperarTodos() {
				
		return this.saborDaoImpl.recuperarTodos();
		
	}
	
	
	/**
	 * Buscar por idSabor
	 * @param idSabor
	 */
	public SaborDTO buscarPorIdSabor(final Long idSabor) throws CupackeException {
		
		if (idSabor == null) {
			throw new CupackeException("idSabor é obrigatório!");
		}
		
		SaborDTO sabor = this.saborDaoImpl.buscarPorIdSabor(idSabor);
		if (sabor == null) {
			throw new CupackeException("O idSabor não foi encontrado.");
		}
		
		return sabor;
	}

	
	/**
	 * Inserir
	 * @param SaborInserirDTO
	 */
	@Transactional
	public void inserir(SaborInserirDTO dto) throws CupackeException {
		
		if (dto == null) {
			throw new CupackeException("SaborInserirDTO não pode ser nulo.");
		}
		
		if (dto.getDescricao() == null) {
			throw new CupackeException("Descricao é obrigatória.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Sabor entidade = modelMapper.map(dto, Sabor.class);
		
		entidade.setDataInclusao(new Date());
		entidade.setStatusAtivo(true);
		
		this.saborDao.save(entidade);
		
	}
	
	
	/**
	 * Alterar
	 * @param SaborAlterarDTO
	 */
	@Transactional
	public void alterar(final SaborAlterarDTO dto) throws CupackeException {
				
		if (dto.getIdSabor() == null) {
			throw new CupackeException("idSabor é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Sabor saborAntes = this.saborDao.findById(dto.getIdSabor()).get();
		if (saborAntes == null || saborAntes.getIdSabor() == null) {
			throw new CupackeException("idSabor é inválido.");
		}
		
		if (dto.getDescricao() == null) {
			throw new CupackeException("Descrição é obrigatória.");
		}
		
		final ModelMapper modelMapper = new ModelMapper();
		Sabor entidade = modelMapper.map(dto, Sabor.class);
		
		entidade.setDataInclusao(saborAntes.getDataInclusao());
		entidade.setDataUltimaAlteracao(new Date() );
		entidade.setStatusAtivo(true);
		
		this.saborDao.save(entidade);
		
	}	
	
	
	/**
	 * Excluir por idSabor.
	 * @param idSabor
	 */
	@Transactional
    public void excluir(final Long idSabor) throws CupackeException {
		
		if (idSabor == null) {
			throw new CupackeException("idSabor é obrigatório.");
		}
		
		//Faz a Consulta no Banco de Dados
		Optional<Sabor> entidade = this.saborDao.findById(idSabor);
		if (entidade == null) {
			throw new CupackeException("idSabor é inválido.");
		}
		
   	  	this.saborDao.deleteById(idSabor);
    }
	
	
	/**
	 * Desativar por idSabor.
	 * @param idSabor
	 */
	@Transactional
    public void desativar(final Long idSabor) throws CupackeException {
		
		if (idSabor == null) {
			throw new CupackeException("idSabor é obrigatório.");
		}
				
		//Faz a Consulta no Banco de Dados
		Sabor saborAntes = this.saborDao.findById(idSabor).get();
		if (saborAntes == null || saborAntes.getIdSabor() == null) {
			throw new CupackeException("idSabor é inválido.");
		}
		
		saborAntes.setDataUltimaAlteracao(new Date() );
		saborAntes.setStatusAtivo(false);
		
		this.saborDao.save(saborAntes);
		
    }
	
	
	/**
	 * Retorna todos
	 *
	 * @return
	 */
	public List<SaborDTO> pesquisar(final SaborPesquisarDTO saborPesquisarDTO) {
				
		return this.saborDaoImpl.pesquisar(saborPesquisarDTO);
		
	}

	
}
