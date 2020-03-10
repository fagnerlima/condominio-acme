/**
 * 
 */
package br.com.acme.domain.model.multas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.domain.model.condominio.Condominio;
import br.com.acme.domain.model.unidade.Unidade;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosfilho
 *
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tb_multas")
public class Multa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricaoMulta;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataMulta;
	
	@JsonIgnoreProperties("multasUnidade")
	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidadeMulta;
	
	@ManyToOne
	@JoinColumn(name = "id_condominio")
	private Condominio condominoMulta;
	
	private BigDecimal valorMulta;
}
