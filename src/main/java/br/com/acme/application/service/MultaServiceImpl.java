package br.com.acme.application.service;

import org.springframework.stereotype.Service;

import br.com.acme.domain.model.multas.Multa;
import br.com.acme.domain.service.MultaService;
import br.com.acme.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.com.acme.infrastructure.persistence.hibernate.repository.MultaRepository;

@Service
public class MultaServiceImpl extends BaseServiceImpl<Multa> implements MultaService {

    private MultaRepository multaRepository;

    public MultaServiceImpl(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    @Override
    protected BaseRepository<Multa> getRepository() {
        return multaRepository;
    }

}
