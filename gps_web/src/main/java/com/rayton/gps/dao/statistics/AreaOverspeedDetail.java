package com.rayton.gps.dao.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaOverspeedDetail {

    private String deviceNumber;
    private String motorcade;
    private String plateNumber;
    private int times;
    private long duration;
    private Date start;
    private Date end;
    private List<AreaOverspeedDto> detail = new ArrayList<AreaOverspeedDto>();

    @Override
    public String toString() {
        return "AreaOverspeedDetail{" + "deviceNumber='" + deviceNumber + '\'' + ", motorcade='" + motorcade + '\'' +
                ", plateNumber='" + plateNumber + '\'' + ", times=" + times + ", duration=" + duration + ", start=" +
                start + ", end=" + end + ", detail=" + detail + '}';
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

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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

    public List<AreaOverspeedDto> getDetail() {
        return detail;
    }

    public void setDetail(List<AreaOverspeedDto> detail) {
        this.detail = detail;
    }
}
