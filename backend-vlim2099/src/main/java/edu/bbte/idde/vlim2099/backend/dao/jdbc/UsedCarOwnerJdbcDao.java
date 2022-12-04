package edu.bbte.idde.vlim2099.backend.dao.jdbc;

import edu.bbte.idde.vlim2099.backend.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCarOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UsedCarOwnerJdbcDao implements UsedCarOwnerDao {

    private final DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarOwnerJdbcDao.class);

    public UsedCarOwnerJdbcDao() {
        dataSource = DataSourceFactory.getDataSource();
    }

    private UsedCarOwner createUsedCarOwnerFromResult (ResultSet set) throws SQLException {
        UsedCarOwner usedCarOwner = new UsedCarOwner(set.getString("firstName"),
                set.getString("lastName"),
                set.getDate("birthDay"),
                set.getString("gender"),
                set.getString("email"),
                set.getString("address"),
                set.getInt("usedCarId"));
        usedCarOwner.setId(set.getLong("usedCarOwnerID"));
        return usedCarOwner;
    }

    private PreparedStatement createPreparedStatement (PreparedStatement prep,
                                                       UsedCarOwner usedCarOwner) throws SQLException {
        prep.setString(1, usedCarOwner.getFirstName());
        prep.setString(2, usedCarOwner.getLastName());
        prep.setDate(3, usedCarOwner.getBirthDay());
        prep.setString(4, usedCarOwner.getGender());
        prep.setString(5, usedCarOwner.getEmail());
        prep.setString(6, usedCarOwner.getAddress());
        prep.setInt(7, usedCarOwner.getUsedCarId());
        return prep;
    }

    @Override
    public UsedCarOwner findById(Long id) {
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
    public void create(UsedCarOwner usedCarOwner) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("insert into UsedCarOwner values(default, ?, ?, ?, ?, ?, ?, ?)");
            prep = createPreparedStatement(prep, usedCarOwner);
            prep.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
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
    public void update(UsedCarOwner usedCarOwner, Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("Update UsedCarOwner "
                            + "Set firstName = ?, lastName = ?, birthDay = ?, gender = ?,"
                            + "email = ?, address = ?, usedCarId = ? "
                            + "where usedCarOwnerID = ?");
            prep = createPreparedStatement(prep, usedCarOwner);
            prep.setLong(8, id);
            int set = prep.executeUpdate();
            LOGGER.error("Ennyi sor lett frissítve: {}", set);

        } catch (SQLException e) {
            LOGGER.error("Hiba: {}", e.toString());
        }
    }

    @Override
    public void delete(Long id) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prep = connection
                    .prepareStatement("Delete from UsedCarOwner "
                            + "where usedCarOwnerID = ?");

            prep.setLong(1, id);
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
