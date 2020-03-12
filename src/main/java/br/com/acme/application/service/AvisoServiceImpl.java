package br.com.acme.application.service;

import org.springframework.stereotype.Service;

import br.com.acme.domain.model.aviso.Aviso;
import br.com.acme.domain.service.AvisoService;
import br.com.acme.infrastructure.persistence.hibernate.repository.AvisoRepository;
import br.com.acme.infrastructure.persistence.hibernate.repository.BaseRepository;

@Service
public class AvisoServiceImpl extends BaseServiceImpl<Aviso> implements AvisoService {

    private AvisoRepository avisoRepository;

    public AvisoServiceImpl(AvisoRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    @Override
    protected BaseRepository<Aviso> getRepository() {
        return avisoRepository;
    }

}
