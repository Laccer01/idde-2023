package edu.bbte.idde.vlim2099.desktop;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.memory.UsedCarMemoryDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        UsedCarDao usedCarDao = new UsedCarMemoryDao();
        UsedCar firstCar = new UsedCar("audi", "A4", 1900.5, 120, 200000.12, 2016, "KNDPBCA25B7076883", 5000,1);

        usedCarDao.create(firstCar);
        LOG.info(firstCar.toString());

        UsedCar secondCar = new UsedCar("bmw", "X5",2000.9, 190, 100800.12, 2012, "1FTEX1C85AFB83192", 4200,1);

        usedCarDao.create(secondCar);
        LOG.info("Second car: {}", secondCar);

        LOG.info(usedCarDao.findById(firstCar.getId()).toString());

        UsedCar thirdCar = new UsedCar("wolksvagen", "passat cc",1800.9, 210, 10800.12,
                2020, "JH4KA8162MC010197", 10200,1);
        usedCarDao.create(thirdCar);
        usedCarDao.update(thirdCar, firstCar.getId());
        LOG.info("First car updated: {}", firstCar);

        usedCarDao.delete(thirdCar.getId());
        LOG.info(usedCarDao.findAll().toString());

    }
}
