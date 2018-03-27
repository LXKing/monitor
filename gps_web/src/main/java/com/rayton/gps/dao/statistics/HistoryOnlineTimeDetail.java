package com.rayton.gps.dao.statistics;

import java.util.Date;

public class HistoryOnlineTimeDetail {

    private String motorcade;
    private String plateNumber;
    private String deviceNumber;
    private Date start;
    private Date end;
    private int must;
    private int real;
    private float onlineRate;
    private float offlineRate;

    @Override
    public String toString() {
        return "HistoryOnlineTimeDetail{" + "motorcade='" + motorcade + '\'' + ", plateNumber='" + plateNumber + '\''
                + ", deviceNumber='" + deviceNumber + '\'' + ", start=" + start + ", end=" + end + ", must=" + must +
                ", real=" + real + ", onlineRate=" + onlineRate + ", offlineRate=" + offlineRate + '}';
    }

    public String getMotorcade() {
        return motorcade;
    }

    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
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

    public int getMust() {
        return must;
    }

    public void setMust(int must) {
        this.must = must;
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public float getOnlineRate() {
        return onlineRate;
    }

    public void setOnlineRate(float onlineRate) {
        this.onlineRate = onlineRate;
    }

    public float getOfflineRate() {
        return offlineRate;
    }

    public void setOfflineRate(float offlineRate) {
        this.offlineRate = offlineRate;
    }
}
