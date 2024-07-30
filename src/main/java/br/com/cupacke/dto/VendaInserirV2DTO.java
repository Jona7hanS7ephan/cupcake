package br.com.cupacke.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaInserirV2DTO implements Serializable {
	
	private static final long serialVersionUID = -8715735891870077241L;
	
//	private Long idVenda;
	private Long idCliente;
	private BigDecimal valorTotalVenda;
//	private Boolean statusAtivo;
//	private Date dataInclusao;
//	private Date dataUltimaAlteracao
	
	private ArrayList<ItensVendaInserirV2DTO> itensVenda;
	
}
