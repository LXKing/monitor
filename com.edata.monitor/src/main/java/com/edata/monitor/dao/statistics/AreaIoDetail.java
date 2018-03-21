package com.edata.monitor.dao.statistics;

import java.util.Date;

public class AreaIoDetail {

    private String deviceNumber;
    private String motorcade;
    private String plateNumber;
    private int in;
    private int out;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "AreaIoDetail{" + "deviceNumber='" + deviceNumber + '\'' + ", motorcade='" + motorcade + '\'' + ", " +
                "plateNumber='" + plateNumber + '\'' + ", in=" + in + ", out=" + out + ", start=" + start + ", end="
                + end + '}';
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
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

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
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
}
