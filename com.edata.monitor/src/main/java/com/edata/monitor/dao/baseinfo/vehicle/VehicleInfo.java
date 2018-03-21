package com.edata.monitor.dao.baseinfo.vehicle;

import java.sql.Date;

/**
 * 车辆信息类
 *
 * @author yangzs
 */
public class VehicleInfo {

    private String id;
    private String deviceNumber;
    private String phoneNumber;
    private String plateNumber;
    private String plateColor;
    private Date installDate;
    private Date annualSurveyDate;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private Date nextMaintainDate;
    private String motorcade;
    private String marker;
    private String remark;

    @Override
    public String toString() {
        return "VehicleInfo{" + "id='" + id + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", phoneNumber='" +
                phoneNumber + '\'' + ", plateNumber='" + plateNumber + '\'' + ", plateColor='" + plateColor + '\'' +
                ", installDate=" + installDate + ", annualSurveyDate=" + annualSurveyDate + ", serviceStartDate=" +
                serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", nextMaintainDate=" + nextMaintainDate +
                ", motorcade='" + motorcade + '\'' + ", marker='" + marker + '\'' + ", remark='" + remark + '\'' + '}';
    }

    /**
     * 获取唯一编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取设备号
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * 设置设备号
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    /**
     * 获取电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取车牌号码
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * 设置车牌号码
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * 获取车牌颜色
     */
    public String getPlateColor() {
        return plateColor;
    }

    /**
     * 设置车牌颜色
     */
    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    /**
     * 获取安装日期
     */
    public Date getInstallDate() {
        return installDate;
    }

    /**
     * 设置安装日期
     */
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    /**
     * 获取年检日期
     */
    public Date getAnnualSurveyDate() {
        return annualSurveyDate;
    }

    /**
     * 设置年检日期
     */
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

    /**
     * 获取车队
     */
    public String getMotorcade() {
        return motorcade;
    }

    /**
     * 设置车队
     */
    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
    }

    /**
     * 获取车辆图标
     */
    public String getMarker() {
        return marker;
    }

    /**
     * 设置车辆图标
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
