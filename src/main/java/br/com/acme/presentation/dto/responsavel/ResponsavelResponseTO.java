package br.com.acme.presentation.dto.responsavel;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsavelResponseTO implements Serializable {

    private static final long serialVersionUID = 7831127546565389619L;

    private Long id;

    private String nome;

    private String email;

    private String telefone;

//    private Set<Reserva> reservas; TODO

//    private Set<Reclamacao> reclamacoes; TODO

}
