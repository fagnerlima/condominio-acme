package br.com.acme.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.acme.domain.model.unidade.Unidade;
import br.com.acme.domain.service.UnidadeService;
import br.com.acme.infrastructure.persistence.hibernate.repository.BaseRepository;
import br.com.acme.infrastructure.persistence.hibernate.repository.UnidadeRepository;
import br.com.acme.infrastructure.persistence.hibernate.specification.UnidadeSpecification;

@Service
public class UnidadeServiceImpl extends BaseServiceImpl<Unidade> implements UnidadeService {

    private UnidadeRepository unidadeRepository;

    public UnidadeServiceImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public Page<Unidade> findAllComMultas(Pageable pageable) {
        return unidadeRepository.findAll(UnidadeSpecification.comMultas(), pageable);
    }

    @Override
    public Page<Unidade> findAllSemMultas(Pageable pageable) {
        return unidadeRepository.findAll(UnidadeSpecification.semMultas(), pageable);
    }

    @Override
    protected BaseRepository<Unidade> getRepository() {
        return unidadeRepository;
    }

}
