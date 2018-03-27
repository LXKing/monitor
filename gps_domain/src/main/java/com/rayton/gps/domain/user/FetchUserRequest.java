package com.rayton.gps.domain.user;

import org.hibernate.validator.constraints.NotEmpty;

public class FetchUserRequest {
    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
