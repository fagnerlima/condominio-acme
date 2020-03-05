package br.com.acme.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {

    public T findById(Long id);

    public Page<T> findAll(Pageable pageable);

    public T save(T entity);

    public T update(Long id, T entity);

    public void delete(Long id);

}
