package br.com.cupacke.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaborDTO implements Serializable  {
	
	private static final long serialVersionUID = 180918260232410092L;
	
	private Long idSabor;
	private String descricao;
	private boolean statusAtivo;
	private Date dataInclusao;
	private Date dataUltimaAlteracao;
	

}