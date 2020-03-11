package br.com.acme.presentation.dto.multa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultaResponseTO implements Serializable {

    private static final long serialVersionUID = 9092186921347962543L;

    private Long id;

    @JsonProperty("descricao")
    private String descricaoMulta;

    @JsonProperty("data")
    private LocalDate dataMulta;

    @JsonIgnoreProperties("multas")
    @JsonProperty("condominio")
    private CondominioResponseTO condominoMulta;

    @JsonIgnoreProperties("multas")
    @JsonProperty("unidade")
    private UnidadeResponseTO unidadeMulta;

    @JsonProperty("valor")
    private BigDecimal valorMulta;

}
