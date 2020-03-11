package br.com.acme.presentation.dto.unidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acme.presentation.dto.responsavel.ResponsavelResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeMultaResponseTO implements Serializable {

    private static final long serialVersionUID = -5421404482704851296L;

    private Long id;

    @JsonProperty("responsavel")
    private ResponsavelResponseTO responsavelUnidade;

    @JsonProperty("numero")
    private String numeroUnidade;

    @JsonProperty("bloco")
    private String blocoUnidade;

}
