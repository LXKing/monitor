package com.rayton.gps.dao.statistics;

import java.util.Date;

public class MileageOilCount {

    private String motorcadeId;
    private String motorcade;
    private int vehicles;
    private double mileages;
    private double oils;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "MileageOilCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", " + "vehicles=" + vehicles + ", mileages=" + mileages + ", oils=" + oils + ", start=" + start + ", " + "end=" + end + '}';
    }

    public String getMotorcadeId() {
        return motorcadeId;
    }

    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    public String getMotorcade() {
        return motorcade;
    }

    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }

    public int getVehicles() {
        return vehicles;
    }

    public void setVehicles(int vehicles) {
        this.vehicles = vehicles;
    }

    public double getMileages() {
        return mileages;
    }

    public void setMileages(double mileages) {
        this.mileages = mileages;
    }

    public double getOils() {
        return oils;
    }

    public void setOils(double oils) {
        this.oils = oils;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
