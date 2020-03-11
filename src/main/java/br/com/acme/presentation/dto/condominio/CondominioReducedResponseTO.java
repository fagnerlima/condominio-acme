package br.com.acme.presentation.dto.condominio;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acme.presentation.dto.aviso.AvisoResponseTO;
import br.com.acme.presentation.dto.multa.MultaResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondominioReducedResponseTO implements Serializable {

    private static final long serialVersionUID = 2176261315222751580L;

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    @JsonIgnoreProperties("condominio")
    @JsonProperty("multas")
    private List<MultaResponseTO> multasAplicadas;

    @JsonIgnoreProperties("condominio")
    @JsonProperty("avisos")
    private List<AvisoResponseTO> avisosDescricaoAviso;

}
