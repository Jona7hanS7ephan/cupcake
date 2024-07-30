package br.com.cupacke.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaInserirDTO implements Serializable {
	
	private static final long serialVersionUID = -3096249947654948009L;
	
//	private Long idVenda;
	private Long idCliente;
	private BigDecimal valorTotalVenda;
//	private Boolean statusAtivo;
//	private Date dataInclusao;
//	private Date dataUltimaAlteracao
}
