package com.edata.monitor.domain.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 车辆类
 * 
 * @author yangzs
 *
 */
public class Vehicle {
	private String id;

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

	private String companyId;

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

	private String motorcadeId;

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

	@NotEmpty
	private String motorcade;

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

	private String deviceNumber;

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

	private String deviceId;

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

	@NotEmpty
	private String plateNumber;

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

	private String plateColor;

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

	private String vehicleColor;

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

	private String vehicleType;

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

	private String vehicleVoltage;

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

	private String carryType;

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

	private int initialMileage;

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

	private double oilWear;

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

	private int usefullLife;

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

	private Date installDate;

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

	private Date annualSurveyDate;

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

	private String adminArea;

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

	private String marker;

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

	private boolean rotate;

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
	 * 设置是否旋转图标
	 */
	private String remark;

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

	private Timestamp editTime;

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
