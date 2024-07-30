package br.com.cupacke.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItensVendaDTO implements Serializable{

	private static final long serialVersionUID = -8214912447740757619L;
	
	private Long idItensVenda;
	private Long idVenda;
	private Long idCupcake;
	private Integer quantidade;
	private Double valor;
	private Double valorTotal;
	private Boolean statusAtivo;
	private Date dataInclusao;
	private Date dataUltimaAlteracao;
}
