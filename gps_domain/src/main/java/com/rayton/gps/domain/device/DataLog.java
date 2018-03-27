package com.rayton.gps.domain.device;

import java.util.Date;

public class DataLog {
    private Date time;
    private String raw;

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
