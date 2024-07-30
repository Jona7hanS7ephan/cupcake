package br.com.cupacke.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CupcakeMaisVendidosDTO implements Serializable  {
	
	
	private static final long serialVersionUID = -8756001267293380076L;
	
	
	private Integer quantidadeVendidos;
	private Long idCupcake;
	private Long idSabor;
	private String sabor;
	private String descricao;
	private Integer quantidadeEstoque;
	private BigDecimal preco;

}