package com.rayton.gps.dao.lisence;
public class Drivervl implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String driverId;
    private Integer vehicleLicenceId;
    public Drivervl() {
        super();
    }
    public Drivervl(String driverId,Integer vehicleLicenceId) {
        super();
        this.driverId = driverId;
        this.vehicleLicenceId = vehicleLicenceId;
    }
    public String getDriverId() {
        return this.driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Integer getVehicleLicenceId() {
        return this.vehicleLicenceId;
    }

    public void setVehicleLicenceId(Integer vehicleLicenceId) {
        this.vehicleLicenceId = vehicleLicenceId;
    }

}
