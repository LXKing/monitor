package com.rayton.gps.dao.instruct;

import java.util.Date;

public class DeviceInAreaInfo {

    private String id;
    private String deviceNumber;
    private long areaId;
    private byte areaType;
    private byte action;
    private String unid;
    private String user;
    private Date sendTime;
    private Date ackTime;

    @Override
    public String toString() {
        return "DeviceInAreaInfo{" + "id='" + id + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", areaId=" + areaId + ", areaType=" + areaType + ", action=" + action + ", unid='" + unid + '\'' + ", user='" + user + '\'' + ", sendTime=" + sendTime + ", ackTime=" + ackTime + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public byte getAction() {
        return action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getAckTime() {
        return ackTime;
    }

    public void setAckTime(Date ackTime) {
        this.ackTime = ackTime;
    }
}
