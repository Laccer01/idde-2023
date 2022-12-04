package edu.bbte.idde.vlim2099.backend.dao.memory;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UsedCarMemoryDao implements UsedCarDao {

    private static final Map<Long, UsedCar> USED_CAR_ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private static final Logger LOG = LoggerFactory.getLogger(UsedCarMemoryDao.class);

    @Override
    public UsedCar findById(Long usedCarId) {
        UsedCar searchedCar = USED_CAR_ENTITIES.get(usedCarId);
        if (searchedCar != null) {
            LOG.info("The car with id {} has been found",usedCarId);
        }
        return searchedCar;
    }

    @Override
    public void create(UsedCar usedCar) {
        Long currentUsedCarId = ID_GENERATOR.getAndIncrement();
        usedCar.setId(currentUsedCarId);
        USED_CAR_ENTITIES.put(currentUsedCarId, usedCar);
        LOG.info("New car with id {} has been created",currentUsedCarId);
    }

    @Override
    public Collection<UsedCar> findAll() {
        LOG.info("All cars has been found");
        return USED_CAR_ENTITIES.values();
    }

    @Override
    public void update(UsedCar usedCar, Long id) {
        LOG.info("The car with id {} has been updated", id);
        USED_CAR_ENTITIES.get(id).setCar(usedCar);
    }

    @Override
    public void delete(Long id) {
        LOG.info("The car with id {} has been deleted", id);
        USED_CAR_ENTITIES.remove(id);
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
