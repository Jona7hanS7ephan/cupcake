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
@Table(schema = "cupcake", name = "tb_sabor")
@EqualsAndHashCode(of={"idSabor"})
@Getter
@Setter
public class Sabor implements Serializable {
	
	
	private static final long serialVersionUID = -6895923808099284522L;
	

	@Id
	@SequenceGenerator(name = "TB_SABOR_ID_GENERATOR", sequenceName = "cupcake.seq_sabor_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_SABOR_ID_GENERATOR")
	@Column(name = "id_sabor")
	private Long idSabor;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "status_ativo")
	private boolean statusAtivo;

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
