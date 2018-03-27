package com.rayton.gps.dao.devicedata.log;

import java.util.Date;
import java.util.List;

public class AccidentDoubtLog {

    private Date time;
    private String license;
    private double lng;
    private double lat;
    private int alt;
    private List<Short> content;

    @Override
    public String toString() {
        return "AccidentDoubtLog{" + "time=" + time + ", license='" + license + '\'' + ", lng=" + lng + ", lat=" +
                lat + ", alt=" + alt + ", content=" + content + '}';
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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public List<Short> getContent() {
        return content;
    }

    public void setContent(List<Short> content) {
        this.content = content;
    }
}
