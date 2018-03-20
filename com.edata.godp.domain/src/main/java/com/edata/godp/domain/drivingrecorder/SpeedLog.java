package com.edata.godp.domain.drivingrecorder;

import java.util.Date;

public class SpeedLog {
    private String number;
    private Date time;
    private byte[] content;

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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
