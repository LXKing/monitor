package mmp.gps.domain.statistics;

import java.util.Date;

public class VehicleAlarmCount {

    private String motorcadeId;
    private String motorcade;
    private int overspeedNoneArea;
    private int overspeedInArea;
    private int overspeedInSection;
    private int fatigueDriving;
    private int parkingTimeout;
    private int routeDeparture;
    private Date start;
    private Date end;

    @Override
    public String toString() {
        return "VehicleAlarmCount{" + "motorcadeId='" + motorcadeId + '\'' + ", motorcade='" + motorcade + '\'' + ", " + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "overspeedNoneArea=" + overspeedNoneArea + ", " + "" + "overspeedInArea=" + overspeedInArea + "," + " " + "overspeedInSection=" + overspeedInSection + ", " + "fatigueDriving=" + fatigueDriving + ", " + "" + "parkingTimeout=" + parkingTimeout + ", " + "routeDeparture=" + routeDeparture + ", start=" + start + ", " + "end=" + end + '}';
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

    public int getOverspeedNoneArea() {
        return overspeedNoneArea;
    }

    public void setOverspeedNoneArea(int overspeedNoneArea) {
        this.overspeedNoneArea = overspeedNoneArea;
    }

    public int getOverspeedInArea() {
        return overspeedInArea;
    }

    public void setOverspeedInArea(int overspeedInArea) {
        this.overspeedInArea = overspeedInArea;
    }

    public int getOverspeedInSection() {
        return overspeedInSection;
    }

    public void setOverspeedInSection(int overspeedInSection) {
        this.overspeedInSection = overspeedInSection;
    }

    public int getFatigueDriving() {
        return fatigueDriving;
    }

    public void setFatigueDriving(int fatigueDriving) {
        this.fatigueDriving = fatigueDriving;
    }

    public int getParkingTimeout() {
        return parkingTimeout;
    }

    public void setParkingTimeout(int parkingTimeout) {
        this.parkingTimeout = parkingTimeout;
    }

    public int getRouteDeparture() {
        return routeDeparture;
    }

    public void setRouteDeparture(int routeDeparture) {
        this.routeDeparture = routeDeparture;
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
