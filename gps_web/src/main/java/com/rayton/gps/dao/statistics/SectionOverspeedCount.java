package com.rayton.gps.dao.statistics;

import java.util.Date;

public class SectionOverspeedCount {

    private String motorcadeId;
    private String motorcade;
    private int times;
    private long duration;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "SectionOverspeedCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", times=" + times + ", duration=" + duration + ", start=" + start + ", end=" + end + '}';
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
