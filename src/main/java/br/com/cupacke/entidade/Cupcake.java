package br.com.cupacke.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(schema = "cupcake", name = "tb_cupcake")
@EqualsAndHashCode(of={"idCupcake"})
@Getter
@Setter
public class Cupcake implements Serializable {


	private static final long serialVersionUID = 8729074270404889408L;

	
	@Id
	@SequenceGenerator(name = "TB_CUPCAKE_ID_GENERATOR", sequenceName = "cupcake.seq_cupcake_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_CUPCAKE_ID_GENERATOR")
	@Column(name = "id_cupcake")
	private Long idCupcake;
	
	@Column(name = "id_sabor")
	private Long idSabor;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;
	
	@Column(name = "preco")
	private BigDecimal preco;
	
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
