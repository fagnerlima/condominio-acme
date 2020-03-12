package br.com.acme.presentation.dto.condominio;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondominioRequestTO implements Serializable {

    private static final long serialVersionUID = -4026160112254908411L;

    private String nome;

    private String email;

    private String telefone;

}
