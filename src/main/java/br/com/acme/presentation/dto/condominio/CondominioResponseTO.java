package br.com.acme.presentation.dto.condominio;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.presentation.dto.aviso.AvisoResponseTO;
import br.com.acme.presentation.dto.multa.MultaResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondominioResponseTO extends RepresentationModel<CondominioResponseTO> implements Serializable {

    private static final long serialVersionUID = 2176261315222751580L;

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    @JsonIgnoreProperties("condominioMulta")
    private List<MultaResponseTO> multasAplicadas;

    @JsonIgnoreProperties("condominioAvisos")
    private List<AvisoResponseTO> avisos;

}
