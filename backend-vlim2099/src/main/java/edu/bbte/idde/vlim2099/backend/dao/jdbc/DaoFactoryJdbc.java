package edu.bbte.idde.vlim2099.backend.dao.jdbc;

import edu.bbte.idde.vlim2099.backend.dao.DaoFactory;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarOwnerDao;

public class DaoFactoryJdbc extends DaoFactory {
    private static UsedCarJdbcDao daoUsedCar;
    private static UsedCarOwnerJdbcDao daoUsedCarOwner;
    private static final Object lock = new Object();


    @Override
    public UsedCarDao getUsedCarDao() {
        return (daoUsedCar != null ? daoUsedCar : (daoUsedCar = new UsedCarJdbcDao()));
    }

    @Override
    public UsedCarOwnerDao getUsedCarOwnerDao() {
        return (daoUsedCarOwner != null ? daoUsedCarOwner : (daoUsedCarOwner = new UsedCarOwnerJdbcDao()));
    }
}

