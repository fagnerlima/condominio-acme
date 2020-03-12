package br.com.acme.presentation.dto.aviso;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoRequestTO implements Serializable {

    private static final long serialVersionUID = 5293256216179231683L;

    private String descricaoAviso;

    private Long condominioAvisosId;

}
