package br.com.cupacke.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaborAlterarDTO implements Serializable  {
	

	private static final long serialVersionUID = 8209781925253740833L;
	
	
	private Long idSabor;
	private String descricao;
//	private boolean statusAtivo;
//	private Date dataInclusao;
//	private Date dataUltimaAlteracao;
	

}