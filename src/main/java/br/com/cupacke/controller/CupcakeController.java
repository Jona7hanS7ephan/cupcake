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

import br.com.cupacke.dao.CupcakeDao;
import br.com.cupacke.dto.CupcakeAlterarDTO;
import br.com.cupacke.dto.CupcakeDTO;
import br.com.cupacke.dto.CupcakeInserirDTO;
import br.com.cupacke.dto.CupcakeMaisVendidosDTO;
import br.com.cupacke.dto.CupcakePesquisarDTO;
import br.com.cupacke.dto.MensagemValidacaoDTO;
import br.com.cupacke.exception.CupackeException;
import br.com.cupacke.service.CupcakeService;

@RestController
@RequestMapping("/cupcake")
public class CupcakeController{
	
	
	@Autowired
	public CupcakeService cupcakeService;
	
	@Autowired
	public CupcakeDao cupcakeDao;
	

	@GetMapping(value = "/recuperarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recuperarTodos() {
				
		List<CupcakeDTO> lista = this.cupcakeService.recuperarTodos();
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<CupcakeDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<CupcakeDTO>>(lista, HttpStatus.OK);
		
    }
	
	
	@GetMapping(value = "/buscarPorIdCupcake/{idCupcake}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdCupcake(@PathVariable("idCupcake") Long idCupcake) {
		
		try {
			CupcakeDTO dto = this.cupcakeService.buscarPorIdCupcake(idCupcake);	
			if (dto == null) {
	            return new ResponseEntity<CupcakeDTO>(HttpStatus.NO_CONTENT);
	        }			
			return ResponseEntity.ok().body(dto);
		}catch(CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

		}
		
	}
	
	@GetMapping(value = "/buscarPorIdSabor/{idSabor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdSabor(@PathVariable("idSabor") Long idSabor) {
		
		try {
			CupcakeDTO dto = this.cupcakeService.buscarPorIdSabor(idSabor);	
			if (dto == null) {
	            return new ResponseEntity<CupcakeDTO>(HttpStatus.NO_CONTENT);
	        }			
			return ResponseEntity.ok().body(dto);
		}catch(CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

		}
		
	}
	
	@PostMapping(value = "/inserir", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inserir(@Valid @RequestBody final CupcakeInserirDTO cupcakeInserirDTO) {
				
		try {
			this.cupcakeService.inserir(cupcakeInserirDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Inserido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@PostMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterar(@Valid @RequestBody final CupcakeAlterarDTO cupcakeAlterarDTO) {
				
		try {
			this.cupcakeService.alterar(cupcakeAlterarDTO);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Alterado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
	
	
	@DeleteMapping(value = "/excluir/{idCupcake}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> excluir(@PathVariable("idCupcake") final Long idCupcake) {		
		
		try {
			this.cupcakeService.excluir(idCupcake);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Excluido com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }

	@PostMapping(value = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pesquisar(@Valid @RequestBody final CupcakePesquisarDTO cupcakePesquisarDTO) {
				
		List<CupcakeDTO> lista = this.cupcakeService.pesquisar(cupcakePesquisarDTO);		
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<CupcakeDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<CupcakeDTO>>(lista, HttpStatus.OK);
		
    }
	
	@PostMapping(value = "/pesquisarPorSabor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pesquisarPorSabor(@Valid @RequestBody final CupcakePesquisarDTO cupcakePesquisarDTO) {
				
		List<CupcakeDTO> lista = this.cupcakeService.pesquisarPorSabor(cupcakePesquisarDTO);		
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<CupcakeDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<CupcakeDTO>>(lista, HttpStatus.OK);
		
    }
	
	@PostMapping(value = "/pesquisaAvancada", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pesquisaAvancada(@Valid @RequestBody final CupcakePesquisarDTO cupcakePesquisarDTO) {
				
		List<CupcakeDTO> lista = this.cupcakeService.pesquisaAvancada(cupcakePesquisarDTO);		
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<CupcakeDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<CupcakeDTO>>(lista, HttpStatus.OK);
		
    }
	
	@GetMapping(value = "/maisVendidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> maisVendidos() {
				
		List<CupcakeMaisVendidosDTO> lista = this.cupcakeService.maisVendidos();
		if (lista == null || lista.isEmpty()) {
	         return new ResponseEntity<List<CupcakeMaisVendidosDTO>>(HttpStatus.NO_CONTENT);
	    }
		return  new ResponseEntity<List<CupcakeMaisVendidosDTO>>(lista, HttpStatus.OK);
		
    }
	
	@DeleteMapping(value = "/desativar/{idCupcake}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> desativar(@PathVariable("idCupcake") final Long idCupcake) {		
		
		try {
			this.cupcakeService.desativar(idCupcake);
			return new ResponseEntity<MensagemValidacaoDTO>( new MensagemValidacaoDTO("Desativado com sucesso!"), HttpStatus.OK);
		} catch (final CupackeException e) {
			return new ResponseEntity<MensagemValidacaoDTO>(new MensagemValidacaoDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
    }
	
}
