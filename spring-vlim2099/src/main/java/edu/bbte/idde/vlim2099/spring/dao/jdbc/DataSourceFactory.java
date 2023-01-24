package edu.bbte.idde.vlim2099.spring.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("jdbc")
public class DataSourceFactory {

    @Value("${jdbc.url:localhost}")
    private  String jdbcUrl;
    @Value("${jdbc.user:root}")
    private  String jdbcUser;
    @Value("${jdbc.passwd:root}")
    private  String passwd;
    @Value("${jdbc.poolSize:10}")
    private  Integer connectionNumber;

    @Bean
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(jdbcUser);
        hikariConfig.setPassword(passwd);
        hikariConfig.setMaximumPoolSize(connectionNumber);

        return new HikariDataSource(hikariConfig);
    }

}


