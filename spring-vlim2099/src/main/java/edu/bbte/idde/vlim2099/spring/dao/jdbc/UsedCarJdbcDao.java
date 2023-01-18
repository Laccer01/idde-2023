package edu.bbte.idde.vlim2099.spring.dao.jdbc;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Profile("jdbc")
public class UsedCarJdbcDao implements UsedCarDao {
    @Autowired
    private final DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarJdbcDao.class);

    public UsedCarJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private UsedCar createUsedCarFromResult(ResultSet set) throws SQLException {
        UsedCar currentCar = new UsedCar(set.getString("brand"),
                set.getString("model"),
                set.getDouble("engineSize"),
                set.getInt("horsePower"),
                set.getDouble("numberOfKm"),
                set.getInt("yearOfManufacture"),
                set.getString("chassisNumber"),
                set.getInt("price"),
                (UsedCarOwner) set.getObject("usedCarOwner"));
        currentCar.setId(set.getLong("usedCarID"));
        return currentCar;
    }

    private PreparedStatement createPreparedStatement(PreparedStatement prep, UsedCar usedCar) throws SQLException {
        prep.setString(1, usedCar.getBrand());
        prep.setString(2, usedCar.getModel());
        prep.setDouble(3, usedCar.getEngineSize());
        prep.setInt(4, usedCar.getHorsePower());
        prep.setDouble(5, usedCar.getNumberOfKm());
        prep.setInt(6, usedCar.getYearOfManufacture());
        prep.setString(7, usedCar.getChassisNumber());
        prep.setInt(8, usedCar.getPrice());
        return prep;
    }

    @Override
    public UsedCar getById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCar where usedCarID = ?");
            prep.setLong(1, id);
            ResultSet set = prep.executeQuery();
            if (set.next()) {
                return createUsedCarFromResult(set);
            }

        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return null;
    }

    @Override
    public UsedCar saveAndFlush(UsedCar usedCar) {
        try (Connection connection = dataSource.getConnection()) {
            if (usedCar.getId() != null) {
                return null;
            }


            PreparedStatement prep = connection
                    .prepareStatement("insert into UsedCar values(default, ?, ?, ?, ?, ?, ?, ?, ?)");
            prep = createPreparedStatement(prep, usedCar);

            prep.executeUpdate();
            ResultSet keys = prep.getGeneratedKeys();
            if (keys.next()) {
                usedCar.setId(keys.getLong(1));
            }

            return usedCar;
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
            return null;
        }



    }

    @Override
    public Collection<UsedCar> findAll() {
        Collection<UsedCar> usedCars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCar");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                UsedCar currentCar = createUsedCarFromResult(set);
                usedCars.add(currentCar);
            }
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return usedCars;
    }


    @Override
    public void delete(UsedCar usedCar) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("Delete from UsedCar "
                            + "where usedCarID = ?");

            prep.setLong(1, usedCar.getId());
            try {
                int set = prep.executeUpdate();
                LOGGER.error("Ennyi sor lett törölve: {}", set);
            } catch (SQLException e) {
                LOGGER.error("Hiba, nem lehet kitörölni az adott autót: {}", e.toString());
                //ha egy autóhoz tartozik legalább egy owner akkor nem lehet kitörölni
            }

        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }

    }

    @Override
    public Collection<UsedCar> findByBrand(String brand) {
        Collection<UsedCar> usedCars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCar where brand = ?");
            prep.setString(1, brand);
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                UsedCar currentCar = createUsedCarFromResult(set);
                usedCars.add(currentCar);
            }
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return usedCars;
    }

}
