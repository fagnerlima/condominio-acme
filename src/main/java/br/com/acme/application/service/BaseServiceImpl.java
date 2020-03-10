package br.com.acme.application.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.acme.application.service.exception.InformationNotFoundException;
import br.com.acme.domain.service.BaseService;
import br.com.acme.infrastructure.persistence.hibernate.repository.BaseRepository;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Transactional(readOnly = true)
    @Override
    public T findById(Long id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new InformationNotFoundException());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Transactional
    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Transactional
    @Override
    public T update(Long id, T entity) {
        T savedEntity = findById(id);
        BeanUtils.copyProperties(entity, savedEntity, "id");

        return getRepository().save(savedEntity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        T entity = findById(id);
        getRepository().delete(entity);
    }

    protected abstract BaseRepository<T> getRepository();

}
