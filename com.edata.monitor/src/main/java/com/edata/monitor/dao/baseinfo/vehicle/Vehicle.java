package com.edata.monitor.dao.baseinfo.vehicle;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 车辆类
 *
 * @author yangzs
 */
public class Vehicle {
    private String id;
    private String companyId;
    private String motorcadeId;
    @NotEmpty
    private String motorcade;
    private String deviceNumber;
    private String deviceId;
    @NotEmpty
    private String plateNumber;
    private String plateColor;
    private String vehicleColor;
    private String vehicleType;
    private String vehicleVoltage;
    private String carryType;
    private int initialMileage;
    private double oilWear;
    private int usefullLife;
    private Date installDate;
    private Date annualSurveyDate;
    private String adminArea;
    private String marker;
    private boolean rotate;

    private Date createTime;
    /**
     * 设置是否旋转图标
     */
    private String remark;
    private Timestamp editTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", motorcadeId='" + motorcadeId
                + '\'' + ", motorcade='" + motorcade + '\'' + ", deviceNumber='" + deviceNumber + '\'' + ", " +
                "deviceId='" + deviceId + '\'' + ", plateNumber='" + plateNumber + '\'' + ", plateColor='" +
                plateColor + '\'' + ", vehicleColor='" + vehicleColor + '\'' + ", vehicleType='" + vehicleType + '\''
                + ", vehicleVoltage='" + vehicleVoltage + '\'' + ", carryType='" + carryType + '\'' + ", " +
                "initialMileage=" + initialMileage + ", oilWear=" + oilWear + ", usefullLife=" + usefullLife + ", " +
                "installDate=" + installDate + ", annualSurveyDate=" + annualSurveyDate + ", adminArea='" + adminArea
                + '\'' + ", marker='" + marker + '\'' + ", rotate=" + rotate + ", createTime=" + createTime + ", " +
                "remark='" + remark + '\'' + ", editTime=" + editTime + '}';
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
     * 获取所属公司唯一编号
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置所属公司唯一编号
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取所属车队唯一编号
     */
    public String getMotorcadeId() {
        return motorcadeId;
    }

    /**
     * 设置所属车队唯一编号
     */
    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    /**
     * 获取所属车队
     */
    public String getMotorcade() {
        return motorcade;
    }

    /**
     * 设置所属车队
     */
    public void setMotorcade(String motorcade) {
        this.motorcade = motorcade;
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
     * 获取设备唯一编号
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备唯一编号
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
     * 获取车辆颜色
     */
    public String getVehicleColor() {
        return vehicleColor;
    }

    /**
     * 设置车辆颜色
     */
    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    /**
     * 获取车辆类型
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 设置车辆类型
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 获取车辆电压
     */
    public String getVehicleVoltage() {
        return vehicleVoltage;
    }

    /**
     * 设置车辆电压
     */
    public void setVehicleVoltage(String vehicleVoltage) {
        this.vehicleVoltage = vehicleVoltage;
    }

    /**
     * 获取载运类型
     */
    public String getCarryType() {
        return carryType;
    }

    /**
     * 设置载运类型
     */
    public void setCarryType(String carryType) {
        this.carryType = carryType;
    }

    /**
     * 获取初始里程
     */
    public int getInitialMileage() {
        return initialMileage;
    }

    /**
     * 设置初始里程
     */
    public void setInitialMileage(int initialMileage) {
        this.initialMileage = initialMileage;
    }

    /**
     * 获取百公里油耗
     */
    public double getOilWear() {
        return oilWear;
    }

    /**
     * 设置百公里油耗
     */
    public void setOilWear(double oilWear) {
        this.oilWear = oilWear;
    }

    /**
     * 获取使用年限
     */
    public int getUsefullLife() {
        return usefullLife;
    }

    /**
     * 设置使用年限
     */
    public void setUsefullLife(int usefullLife) {
        this.usefullLife = usefullLife;
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

    /**
     * 获取所属行政区域
     */
    public String getAdminArea() {
        return adminArea;
    }

    /**
     * 设置所属行政区域
     */
    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
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
     * 获取是否旋转图标
     */
    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
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

    /**
     * 获取时间戳
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 设置时间戳
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }
}
