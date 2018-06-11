package com.rayton.gps.dao.overview;

public class VehicleStatusOverview {

    private int online;
    private int offline;
    private int accon;
    private int accoff;
    private int alarm;

    @Override
    public String toString() {
        return "VehicleStatusOverview{" + "online=" + online + ", offline=" + offline + ", accon=" + accon + ", " + "accoff=" + accoff + ", alarm=" + alarm + '}';
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }

    public int getAccon() {
        return accon;
    }

    public void setAccon(int accon) {
        this.accon = accon;
    }

    public int getAccoff() {
        return accoff;
    }

    public void setAccoff(int accoff) {
        this.accoff = accoff;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

}
