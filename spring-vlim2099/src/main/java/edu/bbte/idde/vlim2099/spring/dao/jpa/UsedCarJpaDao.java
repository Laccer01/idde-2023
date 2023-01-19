package edu.bbte.idde.vlim2099.spring.dao.jpa;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("jpa")
public interface UsedCarJpaDao extends UsedCarDao, JpaRepository<UsedCar, Long> {

    @Modifying
    @Override
    @Query("select usedCar from UsedCar usedCar where usedCar.brand = ?1")
    Collection<UsedCar> findByBrand(String brand);

}
