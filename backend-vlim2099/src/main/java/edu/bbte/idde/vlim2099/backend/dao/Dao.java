package edu.bbte.idde.vlim2099.backend.dao;

import edu.bbte.idde.vlim2099.backend.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    T findById(Long id);

    void create(T entity);

    Collection<T> findAll();

    void update(T entity, Long id);

    void delete(Long id);
}
