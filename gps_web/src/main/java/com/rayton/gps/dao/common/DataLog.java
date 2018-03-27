package com.rayton.gps.dao.common;

import java.util.Date;

public class DataLog {

    private Date time;
    private String raw;

    @Override
    public String toString() {
        return "DataLog{" + "time=" + time + ", raw='" + raw + '\'' + '}';
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
