package br.com.cupacke.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -1963573236639240482L;
	
	private Long idCliente;
	private String nome;
	private String email;
	private String cpf;
	private Date dataNascimento;
	private String celular;
	private Boolean statusAtivo;
	private Date dataInclusao;
	private Date dataUltimaAlteracao;
}
