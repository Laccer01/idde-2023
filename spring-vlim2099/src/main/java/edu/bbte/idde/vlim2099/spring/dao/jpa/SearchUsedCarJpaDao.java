package edu.bbte.idde.vlim2099.spring.dao.jpa;

import edu.bbte.idde.vlim2099.spring.dao.model.SearchUsedCar;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.bbte.idde.vlim2099.spring.dao.SearchUsedCarDao;

@Repository
@Profile("jpa")
public interface SearchUsedCarJpaDao extends SearchUsedCarDao, JpaRepository<SearchUsedCar, Long> {
}
