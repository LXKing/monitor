package com.edata.monitor.domain.baseinfo;

import java.sql.Date;

/**
 * 车辆信息类
 * 
 * @author yangzs
 *
 */
public class VehicleInfo {
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

	private String phoneNumber;

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

	private Date serviceStartDate;

	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	private Date serviceEndDate;

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	private Date nextMaintainDate;

	public Date getNextMaintainDate() {
		return nextMaintainDate;
	}

	public void setNextMaintainDate(Date nextMaintainDate) {
		this.nextMaintainDate = nextMaintainDate;
	}

	private String motorcade;

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
}
