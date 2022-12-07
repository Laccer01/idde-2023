package edu.bbte.idde.vlim2099.backend.dao;

import edu.bbte.idde.vlim2099.backend.model.UsedCarOwner;
import java.util.Collection;

public interface UsedCarOwnerDao extends Dao<UsedCarOwner> {

    Collection<UsedCarOwner> findByLastName(String lastName);
}


