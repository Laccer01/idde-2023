package edu.bbte.idde.vlim2099.spring.dao.mem;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
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
public class UsedCarOwnerMemoryDao implements UsedCarOwnerDao {

    private static final Map<Long, UsedCarOwner> USED_CAR_OWNER_ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    private static final Logger LOG = LoggerFactory.getLogger(UsedCarOwnerMemoryDao.class);

    @Override
    public UsedCarOwner getById(Long ownerId) {
        UsedCarOwner searchedOwner = USED_CAR_OWNER_ENTITIES.get(ownerId);
        if (searchedOwner != null) {
            LOG.info("The owner with id {} has been found",searchedOwner);
        }
        return searchedOwner;
    }

    @Override
    public UsedCarOwner saveAndFlush(UsedCarOwner usedCarOwner) {
        if (usedCarOwner.getId() == null) {
            Long currentUsedCarId = ID_GENERATOR.getAndIncrement();
            usedCarOwner.setId(currentUsedCarId);
            USED_CAR_OWNER_ENTITIES.put(currentUsedCarId, usedCarOwner);
            LOG.info("New car owner with id {} has been created",currentUsedCarId);
        } else {
            LOG.info("The car with id {} has been updated", usedCarOwner.getId());
            USED_CAR_OWNER_ENTITIES.get(usedCarOwner.getId()).setOwner(usedCarOwner);
        }
        return usedCarOwner;
    }

    @Override
    public Collection<UsedCarOwner> findAll() {
        LOG.info("All used car owner has been found");
        return USED_CAR_OWNER_ENTITIES.values();
    }

    @Override
    public void delete(UsedCarOwner usedCarOwner) {
        LOG.info("The used car owner with id {} has been deleted", usedCarOwner.getId());
        USED_CAR_OWNER_ENTITIES.remove(usedCarOwner.getId());
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
