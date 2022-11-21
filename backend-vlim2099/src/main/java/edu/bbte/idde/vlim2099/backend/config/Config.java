package edu.bbte.idde.vlim2099.backend.config;

public class Config {
    private String daoType;
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcDatabase;
    private String jdbcUrl;

    private int jdbcPoolSize;

    public int getJdbcPoolSize() {
        return jdbcPoolSize;
    }

    public void setJdbcPoolSize(int jdbcPoolSize) {
        this.jdbcPoolSize = jdbcPoolSize;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcDatabase() {
        return jdbcDatabase;
    }

    public void setJdbcDatabase(String jdbcDatabase) {
        this.jdbcDatabase = jdbcDatabase;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }
}

