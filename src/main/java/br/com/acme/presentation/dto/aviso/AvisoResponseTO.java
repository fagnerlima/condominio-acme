package br.com.acme.presentation.dto.aviso;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoResponseTO implements Serializable {

    private static final long serialVersionUID = -6459512780882864124L;

    private Long id;

    @JsonProperty("descricao")
    private String descricaoAviso;

    @JsonIgnoreProperties("avisos")
    @JsonProperty("condominio")
    private CondominioResponseTO condominio;

}
