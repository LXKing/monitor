package com.edata.godp.domain.feature;

import org.hibernate.validator.constraints.NotEmpty;

public class DeleteFeatureRequest {
    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
