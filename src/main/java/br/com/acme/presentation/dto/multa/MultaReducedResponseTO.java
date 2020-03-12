package br.com.acme.presentation.dto.multa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.acme.presentation.dto.unidade.UnidadeReducedResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultaReducedResponseTO implements Serializable {

    private static final long serialVersionUID = 9092186921347962543L;

    private Long id;

    private String descricaoMulta;

    private LocalDate dataMulta;

    private String condominioMultaNome;

    @JsonIgnoreProperties("multasUnidade")
    private UnidadeReducedResponseTO unidadeMulta;

    private BigDecimal valorMulta;

}
