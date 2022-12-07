package edu.bbte.idde.vlim2099.backend.dao;

import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import java.util.Collection;

public interface UsedCarDao extends Dao<UsedCar> {

    Collection<UsedCar> findByBrand(String brand);
}


