package br.com.cupacke.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaVendaDTO implements Serializable {
	
	private static final long serialVersionUID = -3401305240545037584L;
	
	
	private Long idVenda;
	private Integer quantidade;
	private BigDecimal valor;
	private BigDecimal valorTotal;
	private String cupcake;
	private Integer quantidadeEstoque;
	private BigDecimal preco;
	private String sabor;
	private BigDecimal valorTotalVenda;
	private String nomeCliente;
	
}
