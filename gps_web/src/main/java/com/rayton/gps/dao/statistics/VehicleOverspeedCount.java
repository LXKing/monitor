package com.rayton.gps.dao.statistics;

import java.util.Date;

public class VehicleOverspeedCount {

    double LAT;
    double LNG;
    private String motorcadeId;
    private String motorcade;
    private int times;
    private long duration;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "VehicleOverspeedCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", times=" + times + ", duration=" + duration + ", start=" + start + ", end=" + end + '}';
    }


    public double getLAT() {
        return LAT;
    }

    public void setLAT(double LAT) {
        this.LAT = LAT;
    }

    public double getLNG() {
        return LNG;
    }

    public void setLNG(double LNG) {
        this.LNG = LNG;
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
}
