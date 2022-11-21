package edu.bbte.idde.vlim2099.backend.dao.jdbc;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UsedCarJdbcDao implements UsedCarDao {

    private final DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarJdbcDao.class);

    public UsedCarJdbcDao() {
            dataSource = DataSourceFactory.getDataSource();
    }

    @Override
    public UsedCar findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCar where usedCarID = ?");
            prep.setLong(1, id);
            ResultSet set = prep.executeQuery();
            if (set.next()) {
                UsedCar currentCar = new UsedCar(set.getString("brand"),
                        set.getString("model"),
                        set.getDouble("engineSize"),
                        set.getInt("horsePower"),
                        set.getDouble("numberOfKm"),
                        set.getInt("yearOfManufacture"),
                        set.getString("chassisNumber"),
                        set.getInt("price"));
                currentCar.setId(set.getLong("usedCarID"));

               return currentCar;
            }

        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return null;
    }

    @Override
    public void createNewUsedCar(UsedCar usedCar) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("insert into UsedCar values(default, ?, ?, ?, ?, ?, ?, ?, ?)");
            prep.setString(1, usedCar.getBrand());
            prep.setString(2, usedCar.getModel());
            prep.setDouble(3, usedCar.getEngineSize());
            prep.setInt(4, usedCar.getHorsePower());
            prep.setDouble(5, usedCar.getNumberOfKm());
            prep.setInt(6, usedCar.getYearOfManufacture());
            prep.setString(7, usedCar.getChassisNumber());
            prep.setInt(8, usedCar.getPrice());
            prep.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }

    }

    @Override
    public Collection<UsedCar> findAllUsedCar() {
        Collection<UsedCar> usedCars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCar");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                UsedCar currentCar = new UsedCar(set.getString("brand"),
                        set.getString("model"),
                        set.getDouble("engineSize"),
                        set.getInt("horsePower"),
                        set.getDouble("numberOfKm"),
                        set.getInt("yearOfManufacture"),
                        set.getString("chassisNumber"),
                        set.getInt("price"));
                currentCar.setId(set.getLong("usedCarID"));

                usedCars.add(currentCar);

            }
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return usedCars;
    }

    @Override
    public void updateUsedCar(UsedCar usedCar, Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("Update UsedCar "
                            + "Set brand = ?, model = ?, engineSize = ?, horsePower = ?,"
                            + "numberOfKm = ?, yearOfManufacture = ?, chassisNumber = ?, price = ? "
                            + "where usedCarID = ?");
            prep.setString(1, usedCar.getBrand());
            prep.setString(2, usedCar.getModel());
            prep.setDouble(3, usedCar.getEngineSize());
            prep.setInt(4, usedCar.getHorsePower());
            prep.setDouble(5, usedCar.getNumberOfKm());
            prep.setInt(6, usedCar.getYearOfManufacture());
            prep.setString(7, usedCar.getChassisNumber());
            prep.setInt(8, usedCar.getPrice());

            prep.setLong(9, id);
            ResultSet set = prep.executeQuery();
            LOGGER.error("Ennyi sor lett frissítve: {}", set);

        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }

    }

    @Override
    public void deleteUsedCar(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("Delete from UsedCar "
                            + "where usedCarID = ?");

            prep.setLong(1, id);
            int set = prep.executeUpdate();
            LOGGER.error("Ennyi sor lett törölve: {}", set);
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }

    }
}
