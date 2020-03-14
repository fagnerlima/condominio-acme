package br.com.acme.presentation.dto.aviso;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoReducedResponseTO extends RepresentationModel<AvisoReducedResponseTO> implements Serializable {

    private static final long serialVersionUID = -6459512780882864124L;

    private Long id;

    private String descricaoAviso;

    @JsonProperty("condominioNome")
    private String condominioAvisosNome;

    @JsonProperty("unidadeNumero")
    private String unidadeNumeroUnidade;

    @JsonProperty("unidadeBloco")
    private String unidadeBlocoUnidade;

}
