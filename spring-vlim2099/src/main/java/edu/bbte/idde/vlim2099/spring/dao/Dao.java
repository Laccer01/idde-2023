package edu.bbte.idde.vlim2099.spring.dao;

import edu.bbte.idde.vlim2099.spring.dao.model.BaseEntity;

import java.sql.SQLException;
import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    T saveAndFlush(T entity) throws SQLException;

    Collection<T> findAll();

    T getById(Long id);

    void delete(T entity);
}
