package br.com.acme.presentation.dto.condominio;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.presentation.dto.aviso.AvisoReducedResponseTO;
import br.com.acme.presentation.dto.multa.MultaReducedResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondominioReducedResponseTO extends RepresentationModel<CondominioReducedResponseTO> implements Serializable {

    private static final long serialVersionUID = 2176261315222751580L;

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    @JsonIgnoreProperties("condominioMultaNome")
    private List<MultaReducedResponseTO> multasAplicadas;

    @JsonIgnoreProperties("condominioAvisosNome")
    private List<AvisoReducedResponseTO> avisos;

}
