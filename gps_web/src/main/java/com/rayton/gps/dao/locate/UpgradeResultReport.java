package com.rayton.gps.dao.locate;

import java.util.Date;

public class UpgradeResultReport {

    private String id;
    private String number;
    private String plateNumber;
    private Date time;
    private String type;
    private String result;

    @Override
    public String toString() {
        return "UpgradeResultReport{" + "id='" + id + '\'' + ", number='" + number + '\'' + ", plateNumber='" +
                plateNumber + '\'' + ", time=" + time + ", type='" + type + '\'' + ", result='" + result + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
