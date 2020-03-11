package br.com.acme.presentation.dto.unidade;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acme.presentation.dto.multa.MultaUnidadeResponseTO;
import br.com.acme.presentation.dto.responsavel.ResponsavelResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeResponseTO implements Serializable {

    private static final long serialVersionUID = -5952090825908278382L;

    private Long id;

    @JsonProperty("responsavel")
    private ResponsavelResponseTO responsavelUnidade;

    @JsonProperty("numero")
    private String numeroUnidade;

    @JsonProperty("bloco")
    private String blocoUnidade;

    @JsonProperty("multas")
    private List<MultaUnidadeResponseTO> multasUnidade;

}
