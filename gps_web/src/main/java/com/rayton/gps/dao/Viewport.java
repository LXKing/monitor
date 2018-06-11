package com.rayton.gps.dao;

public class Viewport implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Double lng;
    private String level;
    private Double alt;
    private String userId;

    public Viewport() {
        super();
    }

    public Viewport(Integer id, Double lng, String level, Double alt, String userId) {
        super();
        this.id = id;
        this.lng = lng;
        this.level = level;
        this.alt = alt;
        this.userId = userId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getAlt() {
        return this.alt;
    }

    public void setAlt(Double alt) {
        this.alt = alt;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
