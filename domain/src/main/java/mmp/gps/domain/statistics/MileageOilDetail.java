package mmp.gps.domain.statistics;

import java.util.Date;

public class MileageOilDetail {

    private String motorcade;
    private String deviceNumber;
    private Date start;
    private Date end;
    private String plateNumber;
    private double mileages;
    private double oils;

    @Override
    public String toString() {
        return "MileageOilDetail{" + "motorcade='" + motorcade + '\'' + ", deviceNumber='" + deviceNumber + '\'' + "," + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + " " + "start=" + start + ", end=" + end + ", " + "plateNumber='" + plateNumber + '\'' + ", " + "" + "mileages=" + mileages + ", oils=" + oils + '}';
    }

    public String getMotorcade() {
        return motorcade;
    }

    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getMileages() {
        return mileages;
    }

    public void setMileages(double mileages) {
        this.mileages = mileages;
    }

    public double getOils() {
        return oils;
    }

    public void setOils(double oils) {
        this.oils = oils;
    }

}
