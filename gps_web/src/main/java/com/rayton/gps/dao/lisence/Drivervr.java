package com.rayton.gps.dao.lisence;
public class Drivervr implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String driverId;
    private Integer vrId;
    public Drivervr() {
        super();
    }
    public Drivervr(String driverId,Integer vrId) {
        super();
        this.driverId = driverId;
        this.vrId = vrId;
    }
    public String getDriverId() {
        return this.driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Integer getVrId() {
        return this.vrId;
    }

    public void setVrId(Integer vrId) {
        this.vrId = vrId;
    }

}
