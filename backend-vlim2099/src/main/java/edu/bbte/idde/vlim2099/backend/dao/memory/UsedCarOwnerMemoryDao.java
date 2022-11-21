package edu.bbte.idde.vlim2099.backend.dao.memory;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import edu.bbte.idde.vlim2099.backend.model.UsedCarOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UsedCarOwnerMemoryDao implements UsedCarOwnerDao {

    private static final Map<Long, UsedCarOwner> USED_CAR_OWNER_ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private static final Logger LOG = LoggerFactory.getLogger(UsedCarOwnerMemoryDao.class);


    @Override
    public UsedCarOwner findById(Long ownerId) {
        UsedCarOwner searchedOwner = USED_CAR_OWNER_ENTITIES.get(ownerId);
        if (searchedOwner != null) {
            LOG.info("The owner with id {} has been found",searchedOwner);
        }
        return searchedOwner;
    }

    @Override
    public void createNewUsedCarOwner(UsedCarOwner usedCarOwner) {
        Long currentUsedCarOwnerId = ID_GENERATOR.getAndIncrement();
        usedCarOwner.setId(currentUsedCarOwnerId);
        USED_CAR_OWNER_ENTITIES.put(currentUsedCarOwnerId, usedCarOwner);
        LOG.info("New owner with id {} has been created",currentUsedCarOwnerId);
    }

    @Override
    public Collection<UsedCarOwner> findAllUsedCarOwner() {
        LOG.info("All used car owner has been found");
        return USED_CAR_OWNER_ENTITIES.values();
    }

    @Override
    public void updateUsedCarOwner(UsedCarOwner usedCarOwner, Long id) {
        LOG.info("The used car owner with id {} has been updated", id);
        USED_CAR_OWNER_ENTITIES.get(id).setOwner(usedCarOwner);
    }

    @Override
    public void deleteUsedCarOwner(Long id) {
        LOG.info("The used car owner with id {} has been deleted", id);
        USED_CAR_OWNER_ENTITIES.remove(id);
    }
}
