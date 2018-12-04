package mmp.gps.gateway.statistics;

import java.util.Date;

public class DeviceOnlineOffline {

    private String number;
    private boolean on;
    private Date online;
    private Date offline;


    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public Date getOnline() {
        return this.online;
    }

    public void setOnline(Date online) {
        this.online = online;
    }

    public Date getOffline() {
        return this.offline;
    }

    public void setOffline(Date offline) {
        this.offline = offline;
    }
}
