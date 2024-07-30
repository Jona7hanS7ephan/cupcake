package br.com.cupacke.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItensVendaAlterarDTO implements Serializable{

	private static final long serialVersionUID = 6516101266151602844L;
	
	private Long idItensVenda;
	private Long idVenda;
	private Long idCupcake;
	private Double quantidade;
	private Double valor;
	private Double valorTotal;
//	private Boolean statusAtivo;
//	private Date dataInclusao;
//	private Date dataUltimaAlteracao;
}
