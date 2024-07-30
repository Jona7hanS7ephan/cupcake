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

import br.com.cupacke.dao.ClienteDao;
import br.com.cupacke.dto.ClienteAlterarDTO;
import br.com.cupacke.dto.ClienteDTO;
import br.com.cupacke.dto.ClienteInserirDTO;
import br.com.cupacke.dto.ClientePesquisarDTO;
import br.com.cupacke.dto.CupcakeDTO;
import br.com.cupacke.dto.MensagemValidacaoDTO;
import br.com.cupacke.exception.CupackeException;
import br.com.cupacke.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController{
	
	
	@Autowired
	public ClienteService clienteService;
	
	@Autowired
	public ClienteDao clienteDao;
	

	@GetMapping(value = "/recuperarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recuperarTodos() {
				
		List<ClienteDTO> lista = this.clienteService.recuperarTodos();
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<ClienteDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<ClienteDTO>>(lista, HttpStatus.OK);
		
    }
	
	
	@GetMapping(value = "/buscarPorIdCliente/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdCliente(@PathVariable("idCliente") Long idCliente) {
		
		try {
			ClienteDTO dto = this.clienteService.buscarPorIdCliente(idCliente);	
			if (dto == null) {
	            return new ResponseEntity<ClienteDTO>(HttpStatus.NO_CONTENT);
	        }			
			return ResponseEntity.ok().body(dto);
		}catch(CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

		}
		
	}
	
	@PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inserir(@Valid @RequestBody final ClienteInserirDTO clienteInserirDTO) {
				
		try {
			this.clienteService.inserir(clienteInserirDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Inserido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@PostMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterar(@Valid @RequestBody final ClienteAlterarDTO clienteAlterarDTO) {
				
		try {
			this.clienteService.alterar(clienteAlterarDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Alterado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@DeleteMapping(value = "/excluir/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluir(@PathVariable("idCliente") final Long idCliente) {		
		
		try {
			this.clienteService.excluir(idCliente);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Excluido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }

	@PostMapping(value = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pesquisar(@Valid @RequestBody final ClientePesquisarDTO clientePesquisarDTO) {
				
		List<ClienteDTO> lista = this.clienteService.pesquisar(clientePesquisarDTO);		
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<CupcakeDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<ClienteDTO>>(lista, HttpStatus.OK);
		
    }
	
	@DeleteMapping(value = "/desativar/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> desativar(@PathVariable("idCliente") final Long idCliente) {		
		
		try {
			this.clienteService.desativar(idCliente);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Desativado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }
}
