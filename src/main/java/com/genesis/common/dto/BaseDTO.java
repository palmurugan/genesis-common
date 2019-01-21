package com.genesis.common.dto;

import java.io.Serializable;

/**
 * @author PalMurugan C
 */
public class BaseDTO implements Serializable {

    private long id;

    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
