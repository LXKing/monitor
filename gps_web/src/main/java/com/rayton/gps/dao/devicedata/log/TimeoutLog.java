package com.rayton.gps.dao.devicedata.log;

import java.util.Date;

public class TimeoutLog {

    private String number;
    private Date startTime;
    private Date endTime;
    private String license;
    private double startLng;
    private double startLat;
    private int startAlt;
    private double endLng;
    private double endLat;
    private int endAlt;

    @Override
    public String toString() {
        return "TimeoutLog{" + "number='" + number + '\'' + ", startTime=" + startTime + ", endTime=" + endTime + ", " + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "license='" + license + '\'' + ", startLng=" + startLng + ", " + "" + "startLat=" + startLat + ", " + "startAlt=" + startAlt + ", endLng=" + endLng + ", " + "endLat=" + endLat + ", " + "endAlt=" + endAlt + '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public double getStartLng() {
        return startLng;
    }

    public void setStartLng(double startLng) {
        this.startLng = startLng;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public int getStartAlt() {
        return startAlt;
    }

    public void setStartAlt(int startAlt) {
        this.startAlt = startAlt;
    }

    public double getEndLng() {
        return endLng;
    }

    public void setEndLng(double endLng) {
        this.endLng = endLng;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public int getEndAlt() {
        return endAlt;
    }

    public void setEndAlt(int endAlt) {
        this.endAlt = endAlt;
    }
}
