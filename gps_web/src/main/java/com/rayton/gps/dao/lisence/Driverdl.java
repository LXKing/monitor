package com.rayton.gps.dao.lisence;

public class Driverdl implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Integer dlId;
    private String driverId;

    public Driverdl() {
        super();
    }

    public Driverdl(Integer dlId, String driverId) {
        super();
        this.dlId = dlId;
        this.driverId = driverId;
    }

    public Integer getDlId() {
        return this.dlId;
    }

    public void setDlId(Integer dlId) {
        this.dlId = dlId;
    }

    public String getDriverId() {
        return this.driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

}
