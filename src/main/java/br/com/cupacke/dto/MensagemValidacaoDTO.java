package br.com.cupacke.dto;

import lombok.Getter;


/**
 * DTO para retornar mensagens de erro para o cliente. 
 * Esta classe gerencia uma lista de mensagens a serem devolvidas para o cliente.
 */
public class MensagemValidacaoDTO{
	
	@Getter
	private String  mensagem = new String();

	public MensagemValidacaoDTO(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public void addMensagem(String mensagem){
		this.mensagem = mensagem;
	}
	
}
