package br.com.acme.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acme.domain.model.unidade.Unidade;
import br.com.acme.domain.service.UnidadeService;
import br.com.acme.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.com.acme.infrastructure.persistence.hibernate.repository.UnidadeRepository;

@Service
public class UnidadeServiceImpl extends BaseServiceImpl<Unidade> implements UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Override
    protected BaseRepository<Unidade> getRepository() {
        return unidadeRepository;
    }

}
