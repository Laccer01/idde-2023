package edu.bbte.idde.vlim2099.backend.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.vlim2099.backend.config.Config;
import edu.bbte.idde.vlim2099.backend.config.ConfigFactory;
import javax.sql.DataSource;

public class DataSourceFactory {

    private static DataSource dataSource;

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
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

