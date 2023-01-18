package edu.bbte.idde.vlim2099.spring.dao.jpa;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("jpa")

public interface UsedCarOwnerJpaDao extends UsedCarOwnerDao, JpaRepository<UsedCarOwner, Long>{

    @Modifying
    @Query("select usedCarOwner from UsedCarOwner usedCarOwner where usedCarOwner.lastName = ?1")
    Collection<UsedCarOwner> findByLastName(String lastName);
}
