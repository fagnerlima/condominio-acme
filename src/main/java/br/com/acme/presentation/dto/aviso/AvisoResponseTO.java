package br.com.acme.presentation.dto.aviso;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoResponseTO extends RepresentationModel<AvisoResponseTO> implements Serializable {

    private static final long serialVersionUID = -6459512780882864124L;

    private Long id;

    private String descricaoAviso;

    @JsonIgnoreProperties("avisos")
    private CondominioResponseTO condominioAvisos;

    @JsonIgnoreProperties("avisosUnidade")
    private UnidadeResponseTO unidadeAviso;

}
