package edu.bbte.idde.vlim2099.spring.dao;

import edu.bbte.idde.vlim2099.spring.dao.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    T findById(Long id);

    void create(T entity);

    Collection<T> findAll();

    void update(T entity, Long id);

    void delete(Long id);
}
