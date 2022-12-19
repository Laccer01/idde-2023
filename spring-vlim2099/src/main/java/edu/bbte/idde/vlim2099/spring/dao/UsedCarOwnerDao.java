package edu.bbte.idde.vlim2099.spring.dao;

import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;

import java.util.Collection;

public interface UsedCarOwnerDao extends Dao<UsedCarOwner> {

    Collection<UsedCarOwner> findByLastName(String lastName);
}


