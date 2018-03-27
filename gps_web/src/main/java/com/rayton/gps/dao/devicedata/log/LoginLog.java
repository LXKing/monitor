package com.rayton.gps.dao.devicedata.log;

import java.util.Date;

public class LoginLog {

    private String number;
    private Date time;
    private String license;
    private String driver;
    private String event;

    @Override
    public String toString() {
        return "LoginLog{" + "number='" + number + '\'' + ", time=" + time + ", license='" + license + '\'' + ", " +
                "driver='" + driver + '\'' + ", event='" + event + '\'' + '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
