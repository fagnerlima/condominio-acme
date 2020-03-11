package br.com.acme.presentation.dto.multa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acme.presentation.dto.unidade.UnidadeMultaReducedResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultaReducedResponseTO implements Serializable {

    private static final long serialVersionUID = 9092186921347962543L;

    private Long id;

    @JsonProperty("descricao")
    private String descricaoMulta;

    @JsonProperty("data")
    private LocalDate dataMulta;

    @JsonProperty("condominio")
    private String condominoMultaNome;

    @JsonProperty("unidade")
    private UnidadeMultaReducedResponseTO unidadeMulta;

    @JsonProperty("valor")
    private BigDecimal valorMulta;

}
