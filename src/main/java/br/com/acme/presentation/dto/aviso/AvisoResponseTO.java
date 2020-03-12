package br.com.acme.presentation.dto.aviso;

import java.io.Serializable;

import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoResponseTO implements Serializable {

    private static final long serialVersionUID = -6459512780882864124L;

    private Long id;

    private String descricaoAviso;

    private CondominioResponseTO condominioAvisos;

}
