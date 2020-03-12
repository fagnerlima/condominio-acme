package br.com.acme.application.service;

import org.springframework.stereotype.Service;

import br.com.acme.domain.model.condominio.Condominio;
import br.com.acme.domain.service.CondominioService;
import br.com.acme.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.com.acme.infrastructure.persistence.hibernate.repository.CondominioRepository;

@Service
public class CondominioServiceImpl extends BaseServiceImpl<Condominio> implements CondominioService {

    private CondominioRepository condominioRepository;

    public CondominioServiceImpl(CondominioRepository condominioRepository) {
        this.condominioRepository = condominioRepository;
    }

    @Override
    protected BaseRepository<Condominio> getRepository() {
        return condominioRepository;
    }

}
