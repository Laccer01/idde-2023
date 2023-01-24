package edu.bbte.idde.vlim2099.spring.dao;

import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;

import java.util.Collection;

public interface UsedCarDao extends Dao<UsedCar> {
    Collection<UsedCar> findByBrand(String brand);
}


