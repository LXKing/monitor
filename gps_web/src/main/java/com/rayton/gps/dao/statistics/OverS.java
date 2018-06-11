package com.rayton.gps.dao.statistics;

import java.util.Date;

public class OverS {

    java.util.Date GPSTIME;
    double LAT;
    double LNG;

    public OverS() {
    }

    public Date getGPSTIME() {
        return GPSTIME;
    }

    public void setGPSTIME(Date GPSTIME) {
        this.GPSTIME = GPSTIME;
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
}
