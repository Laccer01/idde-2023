package edu.bbte.idde.vlim2099.backend.dao;

import edu.bbte.idde.vlim2099.backend.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    void create(T entity);

    T findById(Long id);

    Collection<T> findAll();
}
