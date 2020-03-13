package br.com.acme.presentation.dto.unidade;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.presentation.dto.multa.MultaResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeResponseTO extends RepresentationModel<UnidadeResponseTO> implements Serializable {

    private static final long serialVersionUID = -5952090825908278382L;

    private Long id;

    private String numeroUnidade;

    private String blocoUnidade;

    @JsonIgnoreProperties("unidadeMulta")
    private List<MultaResponseTO> multasUnidade;

}
