package com.edata.monitor.domain.baseinfo;

import java.sql.Date;

public class DeviceInfo {
	private String id;

	/**
	 * 获取ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置ID
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
	 * 设备设备号
	 */
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	private String phoneNumber;

	/**
	 * 获取SIM号
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 设备SIM号
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private String factoryNumber;

	/**
	 * 获取出厂号
	 */
	public String getFactoryNumber() {
		return factoryNumber;
	}

	/**
	 * 设置出厂号
	 */
	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber;
	}

	private String model;

	/**
	 * 获取型号
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 设置型号
	 */
	public void setModel(String model) {
		this.model = model;
	}

	private boolean enable;

	/**
	 * 获取是否启用
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * 设置是否启用
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	private Date serviceStartDate;

	/**
	 * 获取服务开始时间
	 */
	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	/**
	 * 设置服务开始时间
	 */
	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	private Date serviceEndDate;

	/**
	 * 获取服务结束时间
	 */
	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	/**
	 * 设置服务结束时间
	 */
	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	private Date warranty;

	/**
	 * 获取保修期
	 */
	public Date getWarranty() {
		return warranty;
	}

	/**
	 * 设置保修期
	 */
	public void setWarranty(Date warranty) {
		this.warranty = warranty;
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
