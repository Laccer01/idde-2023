package edu.bbte.idde.vlim2099.spring.dao.jdbc;

import edu.bbte.idde.vlim2099.spring.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCar;
import edu.bbte.idde.vlim2099.spring.dao.model.UsedCarOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Profile("jdbc")
public class UsedCarOwnerJdbcDao implements UsedCarOwnerDao {

    @Autowired
    private final DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarOwnerJdbcDao.class);

    public UsedCarOwnerJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private UsedCarOwner createUsedCarOwnerFromResult(ResultSet set) throws SQLException {
        UsedCarOwner usedCarOwner = new UsedCarOwner(set.getString("firstName"),
                set.getString("lastName"),
                set.getDate("birthDay"),
                set.getString("gender"),
                set.getString("email"),
                set.getString("address"),
                (Collection<UsedCar>) set.getArray("usedCars"));
        usedCarOwner.setId(set.getLong("usedCarOwnerID"));
        return usedCarOwner;
    }

    private PreparedStatement createPreparedStatement(PreparedStatement prep,
                                                       UsedCarOwner usedCarOwner) throws SQLException {
        prep.setString(1, usedCarOwner.getFirstName());
        prep.setString(2, usedCarOwner.getLastName());
        prep.setDate(3, usedCarOwner.getBirthDay());
        prep.setString(4, usedCarOwner.getGender());
        prep.setString(5, usedCarOwner.getEmail());
        prep.setString(6, usedCarOwner.getAddress());
        prep.setArray(7, (Array) usedCarOwner.getUsedCars());
        return prep;
    }

    @Override
    public UsedCarOwner getById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCarOwner where usedCarOwnerID = ?");
            prep.setLong(1, id);
            ResultSet set = prep.executeQuery();
            if (set.next()) {
                return createUsedCarOwnerFromResult(set);
            }
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return null;
    }

    @Override
    public UsedCarOwner saveAndFlush(UsedCarOwner usedCarOwner) {

        try (Connection connection = dataSource.getConnection()) {
            if (usedCarOwner.getId() != null) {
                return null;
            }
            PreparedStatement prep = connection
                    .prepareStatement("insert into UsedCarOwner values(default, ?, ?, ?, ?, ?, ?, ?)");
            prep = createPreparedStatement(prep, usedCarOwner);
            prep.executeUpdate();
            return usedCarOwner;

        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
            return null;
        }

    }

    @Override
    public Collection<UsedCarOwner> findAll() {
        Collection<UsedCarOwner> usedCarOwners = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCarOwner");
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                UsedCarOwner usedCarOwnersCurrent = createUsedCarOwnerFromResult(set);
                usedCarOwners.add(usedCarOwnersCurrent);
            }
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return usedCarOwners;
    }

    @Override
    public void delete(UsedCarOwner usedCarOwner) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("Delete from UsedCarOwner "
                            + "where usedCarOwnerID = ?");

            prep.setLong(1, usedCarOwner.getId());
            int set = prep.executeUpdate();
            LOGGER.error("Ennyi sor lett törölve: {}", set);

        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }

    }

    @Override
    public Collection<UsedCarOwner> findByLastName(String lastName) {
        Collection<UsedCarOwner> usedCarOwners = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("select * from UsedCarOwner Where lastName = ?");
            prep.setString(1, lastName);
            ResultSet set = prep.executeQuery();
            while (set.next()) {

                UsedCarOwner usedCarOwnersCurrent = createUsedCarOwnerFromResult(set);
                usedCarOwners.add(usedCarOwnersCurrent);
            }
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
        return usedCarOwners;
    }
}
