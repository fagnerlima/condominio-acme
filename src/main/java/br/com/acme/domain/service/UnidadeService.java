package br.com.acme.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.acme.domain.model.unidade.Unidade;

public interface UnidadeService extends BaseService<Unidade> {

    public Page<Unidade> findAllComMultas(Pageable pageable);

    public Page<Unidade> findAllSemMultas(Pageable pageable);

}
