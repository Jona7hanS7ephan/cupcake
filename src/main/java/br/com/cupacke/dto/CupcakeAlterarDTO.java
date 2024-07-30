package br.com.cupacke.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupcakeAlterarDTO implements Serializable  {
	
	private static final long serialVersionUID = 4757884891519267420L;
	
	private Long idCupcake;
	private Long idSabor;
	private String descricao;
	private Integer quantidadeEstoque;
	private BigDecimal preco;
//	private Boolean statusAtivo;
//	private Date dataInclusao;
//	private Date dataUltimaAlteracao;

	
	

}