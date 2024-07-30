package br.com.cupacke.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaPesquisarDTO implements Serializable  {

	private static final long serialVersionUID = -4833385366118755970L;
	
//	private Long idVenda;
//	private Long idCliente;
//	private BigDecimal valorTotalVenda;
//	private Boolean statusAtivo;
//	private Date dataInclusao;
//	private Date dataUltimaAlteracao
	
	private String descricao;
	
}