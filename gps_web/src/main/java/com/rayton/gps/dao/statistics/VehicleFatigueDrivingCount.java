package com.rayton.gps.dao.statistics;

import java.util.Date;

public class VehicleFatigueDrivingCount {

    private String motorcadeId;
    private String motorcade;
    private int times;
    private double mileages;
    private long duration;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "VehicleFatigueDrivingCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade +
                '\'' + ", times=" + times + ", mileages=" + mileages + ", duration=" + duration + ", start=" + start
                + ", end=" + end + '}';
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

    public double getMileages() {
        return mileages;
    }

    public void setMileages(double mileages) {
        this.mileages = mileages;
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
