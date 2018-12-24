package mmp.gps.domain.statistics;

import java.util.Date;

public class HistoryOnlineTimeCount {

    private String motorcadeId;
    private String motorcade;
    private Date start;
    private Date end;
    private Integer must;
    private Integer real;
    private float onlineRate;
    private float offlineRate = 100;

    @Override
    public String toString() {
        return "HistoryOnlineTimeCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", start=" + start + ", end=" + end + ", must=" + must + ", real=" + real + ", onlineRate=" + onlineRate + ", offlineRate=" + offlineRate + '}';
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

    public Integer getMust() {
        return must;
    }

    public void setMust(Integer must) {
        this.must = must;
    }

    public Integer getReal() {
        return real;
    }

    public void setReal(Integer real) {
        this.real = real;
    }

    public float getOnlineRate() {
        return onlineRate;
    }

    public void setOnlineRate(float onlineRate) {
        this.onlineRate = onlineRate;
    }

    public float getOfflineRate() {
        return offlineRate;
    }

    public void setOfflineRate(float offlineRate) {
        this.offlineRate = offlineRate;
    }
}
