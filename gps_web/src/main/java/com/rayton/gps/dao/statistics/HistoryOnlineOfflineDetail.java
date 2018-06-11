package com.rayton.gps.dao.statistics;

import java.util.Date;

public class HistoryOnlineOfflineDetail {

    private String motorcade;
    private String plateNumber;
    private String deviceNumber;
    private Date start;
    private Date end;
    private boolean online;

    @Override
    public String toString() {
        return "HistoryOnlineOfflineDetail{" + "motorcade='" + motorcade + '\'' + ", plateNumber='" + plateNumber + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", start=" + start + ", end=" + end + ", online=" + online + '}';
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

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
