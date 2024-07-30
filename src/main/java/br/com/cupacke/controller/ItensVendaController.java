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

import br.com.cupacke.dao.ItensVendaDao;
import br.com.cupacke.dto.ItensVendaAlterarDTO;
import br.com.cupacke.dto.ItensVendaDTO;
import br.com.cupacke.dto.ItensVendaInserirDTO;
import br.com.cupacke.dto.MensagemValidacaoDTO;
import br.com.cupacke.exception.CupackeException;
import br.com.cupacke.service.ItensVendaService;

@RestController
@RequestMapping("/itensVenda")
public class ItensVendaController{
	
	
	@Autowired
	public ItensVendaService itensVendaService;
	
	@Autowired
	public ItensVendaDao itensVendaDao;
	

	@GetMapping(value = "/recuperarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recuperarTodos() {
				
		List<ItensVendaDTO> lista = this.itensVendaService.recuperarTodos();
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<ItensVendaDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<ItensVendaDTO>>(lista, HttpStatus.OK);
		
    }
	
	
	@GetMapping(value = "/buscarPorIdItensVenda/{idItensVenda}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdItensVenda(@PathVariable("idItensVenda") Long idItensVenda) {
		
		try {
			ItensVendaDTO dto = this.itensVendaService.buscarPorIdCliente(idItensVenda);	
			if (dto == null) {
	            return new ResponseEntity<ItensVendaDTO>(HttpStatus.NO_CONTENT);
	        }			
			return ResponseEntity.ok().body(dto);
		}catch(CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

		}
		
	}
	
	@PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inserir(@Valid @RequestBody final ItensVendaInserirDTO itensVendaInserirDTO) {
				
		try {
			this.itensVendaService.inserir(itensVendaInserirDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Inserido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@PostMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterar(@Valid @RequestBody final ItensVendaAlterarDTO itensVendaAlterarDTO) {
				
		try {
			this.itensVendaService.alterar(itensVendaAlterarDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Alterado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@DeleteMapping(value = "/excluir/{idItensVenda}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluir(@PathVariable("idItensVenda") final Long idItensVenda) {		
		
		try {
			this.itensVendaService.excluir(idItensVenda);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Excluido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }
	
	@DeleteMapping(value = "/desativar/{idItensVenda}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> desativar(@PathVariable("idItensVenda") final Long idItensVenda) {		
		
		try {
			this.itensVendaService.desativar(idItensVenda);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Desativado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }

//	@PostMapping(value = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> pesquisar(@Valid @RequestBody final ClientePesquisarDTO clientePesquisarDTO) {
//				
//		List<ClienteDTO> lista = this.clienteService.pesquisar(clientePesquisarDTO);		
//		if (lista == null || lista.isEmpty()) {
//	         return new ResponseEntity<List<CupcakeDTO>>(HttpStatus.NO_CONTENT);
//	    }
//		return  new ResponseEntity<List<ClienteDTO>>(lista, HttpStatus.OK);
//		
//    }
	
}
