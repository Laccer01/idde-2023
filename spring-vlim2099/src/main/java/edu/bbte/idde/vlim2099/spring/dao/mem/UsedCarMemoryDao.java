package edu.bbte.idde.vlim2099.spring.dao.mem;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("mem")
public class UsedCarMemoryDao implements UsedCarDao {

    private static final Map<Long, UsedCar> USED_CAR_ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private static final Logger LOG = LoggerFactory.getLogger(UsedCarMemoryDao.class);

    @Override
    public UsedCar getById(Long usedCarId) {
        UsedCar searchedCar = USED_CAR_ENTITIES.get(usedCarId);
        if (searchedCar != null) {
            LOG.info("The car with id {} has been found",usedCarId);
        }
        return searchedCar;
    }

    @Override
    public UsedCar saveAndFlush(UsedCar usedCar) {
        if (usedCar.getId() == null) {
            Long currentUsedCarId = ID_GENERATOR.getAndIncrement();
            usedCar.setId(currentUsedCarId);
            USED_CAR_ENTITIES.put(currentUsedCarId, usedCar);
            LOG.info("New car with id {} has been created",currentUsedCarId);
        } else {
            LOG.info("The car with id {} has been updated", usedCar.getId());
            USED_CAR_ENTITIES.get(usedCar.getId()).setCar(usedCar);
        }
        return usedCar;
    }

    @Override
    public Collection<UsedCar> findAll() {
        LOG.info("All cars has been found");
        return USED_CAR_ENTITIES.values();
    }

    @Override
    public void delete(UsedCar usedCar) {
        LOG.info("The car with id {} has been deleted", usedCar.getId());
        USED_CAR_ENTITIES.remove(usedCar.getId());
    }

    @Override
    public Collection<UsedCar> findByBrand(String brand) {
        Collection<UsedCar> currentUsedCardBrand = new ArrayList();
        Long currentUsedCarId = ID_GENERATOR.getAndIncrement();
        for (Map.Entry<Long, UsedCar> entry : USED_CAR_ENTITIES.entrySet()) {
            if (Objects.equals(entry.getValue().getBrand(), brand)) {
                UsedCar usedCar = entry.getValue();
                usedCar.setId(currentUsedCarId);
                currentUsedCardBrand.add(usedCar);
            }
        }

        if (currentUsedCardBrand.isEmpty()) {
            LOG.info("The car with brand {} has not been found",brand);
        } else {
            LOG.info("The car(s) with brand {} has been found",brand);
            for (UsedCar usedCar : currentUsedCardBrand) {
                LOG.info(usedCar.toString());
            }
        }
        return currentUsedCardBrand;
    }
}
