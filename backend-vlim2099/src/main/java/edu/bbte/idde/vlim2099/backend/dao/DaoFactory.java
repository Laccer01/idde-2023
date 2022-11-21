package edu.bbte.idde.vlim2099.backend.dao;

import edu.bbte.idde.vlim2099.backend.config.Config;
import edu.bbte.idde.vlim2099.backend.config.ConfigFactory;
import edu.bbte.idde.vlim2099.backend.dao.jdbc.DaoFactoryJdbc;
import edu.bbte.idde.vlim2099.backend.dao.memory.MemoryDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory instance;

    public abstract UsedCarDao getUsedCarDao();
    public abstract UsedCarOwnerDao getUsedCarOwnerDao();

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            Config config = ConfigFactory.getConfig();

            if ("jdbc".equals(config.getDaoType())) {
                instance = new DaoFactoryJdbc();
            } else {
                instance = new MemoryDaoFactory();
            }
        }
        return instance;
    }
}
