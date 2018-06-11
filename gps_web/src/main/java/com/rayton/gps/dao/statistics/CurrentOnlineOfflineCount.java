package com.rayton.gps.dao.statistics;

public class CurrentOnlineOfflineCount {

    private String motorcadeId;
    private String motorcade;
    private int total;
    private int online;
    private int onlineRate;
    private int offline;
    private int offlineRate = 100;

    @Override
    public String toString() {
        return "CurrentOnlineOfflineCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", total=" + total + ", online=" + online + ", onlineRate=" + onlineRate + ", offline=" + offline + ", offlineRate=" + offlineRate + '}';
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getOnlineRate() {
        return onlineRate;
    }

    public void setOnlineRate(int onlineRate) {
        this.onlineRate = onlineRate;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }

    public int getOfflineRate() {
        return offlineRate;
    }

    public void setOfflineRate(int offlineRate) {
        this.offlineRate = offlineRate;
    }

}
