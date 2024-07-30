package br.com.cupacke.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupcakeDTO implements Serializable  {
	
	private static final long serialVersionUID = 6227481148349996763L;
	
	private Long idCupcake;
	private Long idSabor;
	private String descricao;
	private Integer quantidadeEstoque;
	private BigDecimal preco;
	private Boolean statusAtivo;
	private Date dataInclusao;
	private Date dataUltimaAlteracao;
	private String sabor;
	

}