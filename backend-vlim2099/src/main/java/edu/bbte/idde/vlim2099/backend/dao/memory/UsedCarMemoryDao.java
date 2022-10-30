package edu.bbte.idde.vlim2099.backend.dao.memory;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
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
    public void createNewUsedCar(UsedCar usedCar) {
        Long currentUsedCarId = ID_GENERATOR.getAndIncrement();
        usedCar.setId(currentUsedCarId);
        USED_CAR_ENTITIES.put(currentUsedCarId, usedCar);
        LOG.info("New car with id {} has been created",currentUsedCarId);
    }

    @Override
    public Collection<UsedCar> findAllUsedCar() {
        LOG.info("All cars has been found");
        return USED_CAR_ENTITIES.values();
    }

    @Override
    public void updateUsedCar(UsedCar usedCar, Long id) {
        LOG.info("The car with id {} has been updated", id);
        USED_CAR_ENTITIES.get(id).setCar(usedCar);
    }

    @Override
    public void deleteUsedCar(Long id) {
        LOG.info("The car with id {} has been deleted", id);
        USED_CAR_ENTITIES.remove(id);
    }
}
