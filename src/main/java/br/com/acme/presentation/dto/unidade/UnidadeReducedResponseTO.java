package br.com.acme.presentation.dto.unidade;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acme.presentation.dto.multa.MultaUnidadeReducedResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeReducedResponseTO implements Serializable {

    private static final long serialVersionUID = -5952090825908278382L;

    private Long id;

    @JsonProperty("responsavel")
    private String responsavelUnidadeNome;

    @JsonProperty("numero")
    private String numeroUnidade;

    @JsonProperty("bloco")
    private String blocoUnidade;

    @JsonProperty("multas")
    private List<MultaUnidadeReducedResponseTO> multasUnidade;

}
