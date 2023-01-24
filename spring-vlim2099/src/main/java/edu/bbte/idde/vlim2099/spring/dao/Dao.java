package edu.bbte.idde.vlim2099.spring.dao;

import edu.bbte.idde.vlim2099.spring.dao.model.BaseEntity;
import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    T saveAndFlush(T entity);

    Collection<T> findAll();

    T getById(Long id);

    void delete(T entity);

}
