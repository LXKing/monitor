package com.rayton.gps.domain.user;

import org.hibernate.validator.constraints.NotEmpty;

public class UserRolesRequest {
    @NotEmpty
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
