/**
 * 
 */
package br.com.acme.domain.model.responsavel;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.acme.domain.model.reclamacao.Reclamacao;
import br.com.acme.domain.model.reserva.Reserva;
import lombok.Builder;
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
@Builder
@EqualsAndHashCode
@Table(name = "tb_responsavel")
public class Responsavel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String telefone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "responsavelReserva")
	private Set<Reserva> reservas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "responsavelReclamacao")
	private Set<Reclamacao> reclamacoes;
}
