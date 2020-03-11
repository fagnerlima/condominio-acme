package br.com.acme.presentation.dto.condominio;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondominioMultaResponseTO implements Serializable {

    private static final long serialVersionUID = 2176261315222751580L;

    private Long id;

    private String nome;

    private String email;

    private String telefone;

//    private Set<Aviso> avisos; TODO

}
