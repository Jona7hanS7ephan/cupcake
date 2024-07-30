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
@Table(schema = "cupcake", name = "tb_venda")
@EqualsAndHashCode(of={"idVenda"})
@Getter
@Setter
public class Venda implements Serializable {
	
	private static final long serialVersionUID = -6251371165077093872L;

	@Id
	@SequenceGenerator(name = "TB_VENDA_ID_GENERATOR", sequenceName = "cupcake.seq_venda_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_VENDA_ID_GENERATOR")
	@Column(name = "id_venda")
	private Long idVenda;
	
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name = "valor_total_venda")
	private BigDecimal valorTotalVenda;
	
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
