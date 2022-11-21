package edu.bbte.idde.vlim2099.backend.dao;

import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import java.util.Collection;

public interface UsedCarDao {
    UsedCar findById(Long id);

    void createNewUsedCar(UsedCar usedCar);

    Collection<UsedCar> findAllUsedCar();

    void updateUsedCar(UsedCar usedCar, Long id);

    void deleteUsedCar(Long id);

    Collection<UsedCar> findByBrand(String brand);
}


