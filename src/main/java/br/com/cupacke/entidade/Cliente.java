package br.com.cupacke.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "cupcake", name = "tb_cliente")
@EqualsAndHashCode(of={"idCliente"})
@Getter
@Setter
public class Cliente implements Serializable {

	private static final long serialVersionUID = 6582854642639059066L;
	
	@Id
	@SequenceGenerator(name = "TB_CLIENTE_ID_GENERATOR", sequenceName = "cupcake.seq_cliente_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_CLIENTE_ID_GENERATOR")
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "data_nascimento" )
	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "status_ativo")
	private Boolean statusAtivo;

	@Column(name = "data_inclusao" )
	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(pattern = "dd/MM/yyyy")
//	@JsonDeserialize(using = LocalDateDeserializer.class)
//	@JsonSerialize(using = LocalDateSerializer.class)
	private Date dataInclusao;
	
	@Column(name = "data_ultima_alteracao" )
	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(pattern = "dd/MM/yyyy")
//	@JsonDeserialize(using = LocalDateDeserializer.class)
//	@JsonSerialize(using = LocalDateSerializer.class)
	private Date dataUltimaAlteracao;
}
