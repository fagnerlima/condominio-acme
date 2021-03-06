/**
 * 
 */
package br.com.acme.domain.model.aviso;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.acme.domain.model.condominio.Condominio;
import br.com.acme.domain.model.unidade.Unidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosfilho
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Table(name = "tb_avisos")
public class Aviso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricaoAviso;
	
	@ManyToOne
	@JoinColumn(name = "id_condominio")
	private Condominio condominioAvisos;
	
	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidadeAviso;
}
