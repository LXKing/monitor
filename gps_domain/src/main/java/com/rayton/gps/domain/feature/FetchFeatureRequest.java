package com.rayton.gps.domain.feature;

import org.hibernate.validator.constraints.NotEmpty;


public class FetchFeatureRequest {
    @NotEmpty
    private String id;

    /**
     * 获取记录号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录号
     */
    public void setId(String id) {
        this.id = id;
    }
}
