package com.batdemir.entity.model.core;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
