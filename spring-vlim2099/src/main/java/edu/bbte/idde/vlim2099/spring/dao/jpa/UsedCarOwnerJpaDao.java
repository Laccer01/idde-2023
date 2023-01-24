package edu.bbte.idde.vlim2099.spring.dao.jpa;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")

public interface UsedCarOwnerJpaDao extends UsedCarOwnerDao, JpaRepository<UsedCarOwner, Long> {

}
