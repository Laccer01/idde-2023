package edu.bbte.idde.vlim2099.backend.dao.memory;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCarOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
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
    public void create(UsedCarOwner usedCarOwner) {
        Long currentUsedCarOwnerId = ID_GENERATOR.getAndIncrement();
        usedCarOwner.setId(currentUsedCarOwnerId);
        USED_CAR_OWNER_ENTITIES.put(currentUsedCarOwnerId, usedCarOwner);
        LOG.info("New owner with id {} has been created",currentUsedCarOwnerId);
    }

    @Override
    public Collection<UsedCarOwner> findAll() {
        LOG.info("All used car owner has been found");
        return USED_CAR_OWNER_ENTITIES.values();
    }

    @Override
    public void update(UsedCarOwner usedCarOwner, Long id) {
        LOG.info("The used car owner with id {} has been updated", id);
        USED_CAR_OWNER_ENTITIES.get(id).setOwner(usedCarOwner);
    }

    @Override
    public void delete(Long id) {
        LOG.info("The used car owner with id {} has been deleted", id);
        USED_CAR_OWNER_ENTITIES.remove(id);
    }

    @Override
    public Collection<UsedCarOwner> findByLastName(String lastName) {
        Collection<UsedCarOwner> currentUsedCarOwnerLastname = new ArrayList();
        Long currentUsedCarOwnerId = ID_GENERATOR.getAndIncrement();
        for (Map.Entry<Long, UsedCarOwner> entry : USED_CAR_OWNER_ENTITIES.entrySet()) {
            if (Objects.equals(entry.getValue().getLastName(), lastName)) {
                UsedCarOwner usedCarOwner = entry.getValue();
                usedCarOwner.setId(currentUsedCarOwnerId);
                currentUsedCarOwnerLastname.add(usedCarOwner);
            }
        }

        if (currentUsedCarOwnerLastname.isEmpty()) {
            LOG.info("The car owner with lastname {} has not been found",lastName);
        } else {
            LOG.info("The car(s) with brand {} has been found",lastName);
            for (UsedCarOwner usedCarOwner : currentUsedCarOwnerLastname) {
                LOG.info(usedCarOwner.toString());
            }
        }
        return currentUsedCarOwnerLastname;
    }
}
