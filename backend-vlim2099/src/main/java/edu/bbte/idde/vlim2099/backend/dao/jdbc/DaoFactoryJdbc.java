package edu.bbte.idde.vlim2099.backend.dao.jdbc;

import edu.bbte.idde.vlim2099.backend.dao.DaoFactory;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarOwnerDao;

public class DaoFactoryJdbc extends DaoFactory {
    private static UsedCarJdbcDao daoUsedCar;
    private static UsedCarOwnerJdbcDao daoUsedCarOwner;


    @Override
    public UsedCarDao getUsedCarDao() {
        if (daoUsedCar == null) {
            daoUsedCar = new UsedCarJdbcDao();
        }
        return daoUsedCar;
    }

    @Override
    public UsedCarOwnerDao getUsedCarOwnerDao() {
        if (daoUsedCarOwner == null) {
            daoUsedCarOwner = new UsedCarOwnerJdbcDao();
        }
        return daoUsedCarOwner;
    }


}

