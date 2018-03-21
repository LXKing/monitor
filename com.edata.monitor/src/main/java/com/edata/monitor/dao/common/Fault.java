package com.edata.monitor.dao.common;

import java.util.Date;

public class Fault {

    private String number;
    private Date time;
    private int level;
    private int systemId;
    private String brand;
    private String descriptionC;
    private String descriptionE;
    private String solution;
    private String sensors;

    @Override
    public String toString() {
        return "Fault{" + "number='" + number + '\'' + ", time=" + time + ", level=" + level + ", systemId=" +
                systemId + ", brand='" + brand + '\'' + ", descriptionC='" + descriptionC + '\'' + ", descriptionE='"
                + descriptionE + '\'' + ", solution='" + solution + '\'' + ", sensors='" + sensors + '\'' + '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSensors() {
        return sensors;
    }

    public void setSensors(String sensors) {
        this.sensors = sensors;
    }
}
