package br.com.acme.presentation.dto.unidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeMultaReducedResponseTO implements Serializable {

    private static final long serialVersionUID = -6910265428666453093L;

    private Long id;

    @JsonProperty("responsavel")
    private String responsavelUnidadeNome;

    @JsonProperty("numero")
    private String numeroUnidade;

    @JsonProperty("bloco")
    private String blocoUnidade;

}
