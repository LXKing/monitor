package mmp.gps.domain.statistics;

import java.util.Date;

public class VehicleFatigueDrivingDetail {

    private String deviceNumber;
    private String motorcade;
    private String plateNumber;
    private int times;
    private double mileages;
    private long duration;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "VehicleFatigueDrivingDetail{" + "deviceNumber='" + deviceNumber + '\'' + ", motorcade='" + motorcade + '\'' + ", plateNumber='" + plateNumber + '\'' + ", times=" + times + ", mileages=" + mileages + ", " + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "duration=" + duration + ", start=" + start + ", " + "end=" + end + '}';
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
