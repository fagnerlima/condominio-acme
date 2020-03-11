package br.com.acme.presentation.dto.aviso;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoReducedResponseTO implements Serializable {

    private static final long serialVersionUID = -6459512780882864124L;

    private Long id;

    @JsonProperty("descricao")
    private String descricaoAviso;

    @JsonProperty("condominio")
    private String condominioNome;

}
