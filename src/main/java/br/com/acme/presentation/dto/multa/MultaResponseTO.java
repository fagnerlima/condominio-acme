package br.com.acme.presentation.dto.multa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultaResponseTO extends RepresentationModel<MultaResponseTO> implements Serializable {

    private static final long serialVersionUID = 9092186921347962543L;

    private Long id;

    private String descricaoMulta;

    private LocalDate dataMulta;

    @JsonIgnoreProperties("multasAplicadas")
    private CondominioResponseTO condominioMulta;

    @JsonIgnoreProperties("multasUnidade")
    private UnidadeResponseTO unidadeMulta;

    private BigDecimal valorMulta;

}
