package com.rayton.gps.dao.baseinfo.areaInDevice;

import java.util.Date;

public class AreaInDeviceInfo {

    private String plateNumber;
    private String deviceNumber;
    private long areaId;
    private byte areaType;
    private Date time;

    @Override
    public String toString() {
        return "AreaInDeviceInfo{" + "plateNumber='" + plateNumber + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", areaId=" + areaId + ", areaType=" + areaType + ", time=" + time + '}';
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public byte getAreaType() {
        return areaType;
    }

    public void setAreaType(byte areaType) {
        this.areaType = areaType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
