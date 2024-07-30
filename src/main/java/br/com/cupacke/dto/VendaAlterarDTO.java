package br.com.cupacke.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaAlterarDTO implements Serializable  {

	private static final long serialVersionUID = -6608043263586138856L;
	
	private Long idVenda;
	private Long idCliente;
	private Double valorTotalVenda;
//	private Boolean statusAtivo;
//	private Date dataInclusao;
//	private Date dataUltimaAlteracao

}