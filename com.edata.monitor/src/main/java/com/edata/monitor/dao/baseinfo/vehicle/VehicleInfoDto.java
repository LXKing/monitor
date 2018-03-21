package com.edata.monitor.dao.baseinfo.vehicle;

import java.sql.Date;

public class VehicleInfoDto {
    public String id;
    public String deviceNumber;
    public String phoneNumber;
    public String plateNumber;
    public String plateColor;
    public Date installDate;
    public Date annualSurveyDate;
    public Date serviceStartDate;
    public Date serviceEndDate;
    public Date nextMaintainDate;
    public String motorcade;
    public String marker;
    public String remark;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Date getAnnualSurveyDate() {
        return annualSurveyDate;
    }

    public void setAnnualSurveyDate(Date annualSurveyDate) {
        this.annualSurveyDate = annualSurveyDate;
    }

    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(Date serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public Date getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(Date serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public Date getNextMaintainDate() {
        return nextMaintainDate;
    }

    public void setNextMaintainDate(Date nextMaintainDate) {
        this.nextMaintainDate = nextMaintainDate;
    }

    public String getMotorcade() {
        return motorcade;
    }

    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "VehicleInfoDto{" + "id='" + id + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", phoneNumber='"
                + phoneNumber + '\'' + ", plateNumber='" + plateNumber + '\'' + ", plateColor='" + plateColor + '\''
                + ", installDate=" + installDate + ", annualSurveyDate=" + annualSurveyDate + ", serviceStartDate=" +
                serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", nextMaintainDate=" + nextMaintainDate +
                ", motorcade='" + motorcade + '\'' + ", marker='" + marker + '\'' + ", remark='" + remark + '\'' + '}';
    }
}
