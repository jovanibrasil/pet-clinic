package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.BaseEntity;
import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractJpaService<T extends BaseEntity, ID extends Long,
        R extends CrudRepository<T, Long>>
    implements CrudService<T, Long> {

    protected R repository;

    public AbstractJpaService(R repository) {
        this.repository = repository;
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<T>((Collection<? extends T>) repository.findAll());
    }

    @Override
    public T findById(Long aLong) {
        return  repository.findById(aLong).orElse(null);
    }

    @Override
    public T save(T object) {
        return repository.save(object);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
