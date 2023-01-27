package edu.bbte.idde.vlim2099.backend.dao.memory;

import edu.bbte.idde.vlim2099.backend.dao.DaoFactory;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;

public class MemoryDaoFactory extends DaoFactory {
    private static UsedCarMemoryDao daoUsedCar;
    private static UsedCarOwnerMemoryDao daoUsedCarOwner;

    @Override
    public synchronized UsedCarDao getUsedCarDao() {
        if (daoUsedCar == null) {
            daoUsedCar = new UsedCarMemoryDao();
            daoUsedCar.create(new UsedCar("wolksvagen", "passat cc",1800.9, 210, 10800.12,
                    2020, "JH4KA8162MC010197", 10200,1));
            daoUsedCar.create(new UsedCar("bmw", "X5",2000.9, 190, 100800.12,
                    2012, "1FTEX1C85AFB83192", 4200,1));
        }
        return daoUsedCar;
    }

    @Override
    public synchronized UsedCarOwnerDao getUsedCarOwnerDao() {
        if (daoUsedCarOwner == null) {
            daoUsedCarOwner = new UsedCarOwnerMemoryDao();
        }
        return daoUsedCarOwner;
    }
}

