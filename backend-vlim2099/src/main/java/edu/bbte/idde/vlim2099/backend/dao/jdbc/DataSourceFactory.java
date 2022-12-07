package edu.bbte.idde.vlim2099.backend.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.vlim2099.backend.config.Config;
import edu.bbte.idde.vlim2099.backend.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarJdbcDao.class);

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                LOGGER.error("Hiba: {}", e.toString());
            }

            Config config = ConfigFactory.getConfig();
            HikariConfig hikariConfig = new HikariConfig();

            hikariConfig.setJdbcUrl("jdbc:mysql://" + config.getJdbcUrl() + "/" + config.getJdbcDatabase());
            hikariConfig.setUsername(config.getJdbcUser());
            hikariConfig.setPassword(config.getJdbcPassword());

            hikariConfig.setMaximumPoolSize(config.getJdbcPoolSize());

            dataSource = new HikariDataSource(hikariConfig);
        }
        return dataSource;
    }
}

