package br.com.acme.presentation.dto.multa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultaRequestTO implements Serializable {

    private static final long serialVersionUID = 9092186921347962543L;

    private Long id;

    private String descricaoMulta;

    private LocalDate dataMulta;

    private Long unidadeMultaId;

    private Long condominoMultaId;

    private BigDecimal valorMulta;

}
