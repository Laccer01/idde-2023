package edu.bbte.idde.vlim2099.backend.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
