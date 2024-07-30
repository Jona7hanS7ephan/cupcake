package br.com.cupacke.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDTO implements Serializable {
	
	private static final long serialVersionUID = 3347909132920939719L;
	
	private Long idVenda;
	private Long idCliente;
	private BigDecimal valorTotalVenda;
	private Boolean statusAtivo;
	private Date dataInclusao;
	private Date dataUltimaAlteracao;
	private String nomeCliente;
	private String numeroPedido;
	
}
