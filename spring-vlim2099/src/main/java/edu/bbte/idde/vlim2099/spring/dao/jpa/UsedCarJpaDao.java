package edu.bbte.idde.vlim2099.spring.dao.jpa;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface UsedCarJpaDao extends UsedCarDao, JpaRepository<UsedCar, Long> {

}
