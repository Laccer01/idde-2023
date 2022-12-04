package edu.bbte.idde.vlim2099.backend.config;

import lombok.Data;

@Data
public class Config {
    private String daoType;
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcDatabase;
    private String jdbcUrl;
    private int jdbcPoolSize;

}

