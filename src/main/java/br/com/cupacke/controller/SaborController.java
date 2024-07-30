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

import br.com.cupacke.dto.MensagemValidacaoDTO;
import br.com.cupacke.dto.SaborAlterarDTO;
import br.com.cupacke.dto.SaborDTO;
import br.com.cupacke.dto.SaborInserirDTO;
import br.com.cupacke.dto.SaborPesquisarDTO;
import br.com.cupacke.exception.CupackeException;
import br.com.cupacke.service.SaborService;

@RestController
@RequestMapping("/sabor")
public class SaborController{
	
	
	@Autowired
	public SaborService saborService;	
	

	@GetMapping(value = "/recuperarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recuperarTodos() {
				
		List<SaborDTO> lista = this.saborService.recuperarTodos();		
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<SaborDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<SaborDTO>>(lista, HttpStatus.OK);
		
    }
	
	
	@GetMapping(value = "/buscarPorIdSabor/{idSabor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdSabor(@PathVariable("idSabor") Long idSabor) {
		
		try {
			SaborDTO dto = this.saborService.buscarPorIdSabor(idSabor);
			if (dto == null) {
	            return new ResponseEntity<SaborDTO>(HttpStatus.NO_CONTENT);
	        }	
			return ResponseEntity.ok().body(dto);
		}catch(CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

		}
		
	}
	
	
	@PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inserir(@Valid @RequestBody final SaborInserirDTO saborInserirDTO) {
				
		try {
			this.saborService.inserir(saborInserirDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Inserido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@PostMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterar(@Valid @RequestBody final SaborAlterarDTO saborAlterarDTO) {
				
		try {
			this.saborService.alterar(saborAlterarDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Alterado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@DeleteMapping(value = "/excluir/{idSabor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluir(@PathVariable("idSabor") final Long idSabor) {		
		
		try {
			this.saborService.excluir(idSabor);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Excluido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }
	
	@DeleteMapping(value = "/desativar/{idSabor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> desativar(@PathVariable("idSabor") final Long idSabor) {		
		
		try {
			this.saborService.desativar(idSabor);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Desativado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }
	
	@PostMapping(value = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pesquisar(@Valid @RequestBody final SaborPesquisarDTO saborPesquisarDTO) {
				
		List<SaborDTO> lista = this.saborService.pesquisar(saborPesquisarDTO);		
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<SaborDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<SaborDTO>>(lista, HttpStatus.OK);
		
    }

	
}
