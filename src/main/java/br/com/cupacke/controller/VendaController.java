package br.com.cupacke.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cupacke.dao.VendaDao;
import br.com.cupacke.dto.ListaVendaDTO;
import br.com.cupacke.dto.MensagemValidacaoDTO;
import br.com.cupacke.dto.VendaAlterarDTO;
import br.com.cupacke.dto.VendaDTO;
import br.com.cupacke.dto.VendaInserirDTO;
import br.com.cupacke.dto.VendaInserirV2DTO;
import br.com.cupacke.dto.VendaPesquisarDTO;
import br.com.cupacke.entidade.Venda;
import br.com.cupacke.exception.CupackeException;
import br.com.cupacke.service.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController{
	
	
	@Autowired
	public VendaService vendaService;
	
	@Autowired
	public VendaDao vendaDao;
	

	@GetMapping(value = "/recuperarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recuperarTodos() {
				
		List<VendaDTO> lista = this.vendaService.recuperarTodos();
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<VendaDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<VendaDTO>>(lista, HttpStatus.OK);
		
    }
	
	
	@GetMapping(value = "/buscarPorIdVenda/{idVenda}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdVenda(@PathVariable("idVenda") Long idVenda) {
		
		try {
			VendaDTO dto = this.vendaService.buscarPorIdVenda(idVenda);	
			if (dto == null) {
	            return new ResponseEntity<VendaDTO>(HttpStatus.NO_CONTENT);
	        }			
			return ResponseEntity.ok().body(dto);
		}catch(CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

		}
		
	}
	
	@PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inserir(@Valid @RequestBody final VendaInserirDTO vendaInserirDTO) {
				
		try {
			this.vendaService.inserir(vendaInserirDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Inserido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	@PostMapping(value = "/inserirV2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inserirV2(@Valid @RequestBody final VendaInserirV2DTO vendaInserirV2DTO) {
				
		try {
			Venda venda = this.vendaService.inserirV2(vendaInserirV2DTO);
			//return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Inserido com sucesso!"), HttpStatus.OK);
			return ResponseEntity.ok().body(venda);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@PostMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterar(@Valid @RequestBody final VendaAlterarDTO vendaAlterarDTO) {
				
		try {
			this.vendaService.alterar(vendaAlterarDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Alterado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@DeleteMapping(value = "/excluir/{idVenda}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluir(@PathVariable("idVenda") final Long idVenda) {		
		
		try {
			this.vendaService.excluir(idVenda);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Excluido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }

	@PostMapping(value = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pesquisar(@Valid @RequestBody final VendaPesquisarDTO vendaPesquisarDTO) {
				
		List<VendaDTO> lista = this.vendaService.pesquisar(vendaPesquisarDTO);		
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<VendaDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<VendaDTO>>(lista, HttpStatus.OK);
		
    }
	
	@GetMapping(value = "/buscarItensVendaPorIdVenda/{idVenda}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarItensVendaPorIdVenda(@PathVariable("idVenda") Long idVenda) {
		
		try {
			List<ListaVendaDTO> dto = this.vendaService.buscarItensVendaPorIdVenda(idVenda);	
			if (dto == null) {
	            return new ResponseEntity<VendaDTO>(HttpStatus.NO_CONTENT);
	        }			
			return ResponseEntity.ok().body(dto);
		}catch(CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

		}
		
	}
	
	@DeleteMapping(value = "/desativar/{idVenda}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> desativar(@PathVariable("idVenda") final Long idVenda) {		
		
		try {
			this.vendaService.desativar(idVenda);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Desativado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }
}
