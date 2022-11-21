package edu.bbte.idde.vlim2099.backend.dao;

import edu.bbte.idde.vlim2099.backend.model.UsedCarOwner;
import java.util.Collection;

public interface UsedCarOwnerDao {
    UsedCarOwner findById(Long ownerId);

    void createNewUsedCarOwner(UsedCarOwner usedCarOwner);

    Collection<UsedCarOwner> findAllUsedCarOwner();

    void updateUsedCarOwner(UsedCarOwner usedCarOwner, Long id);

    void deleteUsedCarOwner(Long id);

    Collection<UsedCarOwner> findByLastName(String lastName);
}


