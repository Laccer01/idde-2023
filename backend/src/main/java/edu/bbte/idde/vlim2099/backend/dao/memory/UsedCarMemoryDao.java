package edu.bbte.idde.vlim2099.backend.dao.memory;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UsedCarMemoryDao implements UsedCarDao {

    private static final Map<Long, UsedCar> USED_CAR_ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    @Override
    public UsedCar findById(Long usedCarId) {
        return USED_CAR_ENTITIES.get(usedCarId);
    }

    @Override
    public void createNewUsedCar(UsedCar usedCar) {
        Long currentUsedCarId = ID_GENERATOR.getAndIncrement();
        usedCar.setId(currentUsedCarId);
        USED_CAR_ENTITIES.put(currentUsedCarId, usedCar);

    }

    @Override
    public Collection<UsedCar> findAllUsedCar() {
        return USED_CAR_ENTITIES.values();
    }

    @Override
    public void updateUsedCar(UsedCar usedCar, Long id) {
        USED_CAR_ENTITIES.get(id).setCar(usedCar);
    }

    @Override
    public void deleteUsedCar(Long id) {
        USED_CAR_ENTITIES.remove(id);
    }
}
