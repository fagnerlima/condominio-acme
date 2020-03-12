package br.com.acme.presentation.dto.unidade;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeRequestTO implements Serializable {

    private static final long serialVersionUID = -5952090825908278382L;

    private String numeroUnidade;

    private String blocoUnidade;

}
