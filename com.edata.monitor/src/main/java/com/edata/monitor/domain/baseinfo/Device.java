package com.edata.monitor.domain.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 设备类
 * 
 * @author 生
 *
 */
public class Device {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String companyId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@NotEmpty
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

	private String simcardId;

	/**
	 * 获取sim卡号
	 */
	public String getSimcardId() {
		return simcardId;
	}

	/**
	 * 设置sim卡号
	 */
	public void setSimcardId(String simcardId) {
		this.simcardId = simcardId;
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

	private String factoryName;

	/**
	 * 获取厂家
	 */
	public String getFactoryName() {
		return factoryName;
	}

	/**
	 * 设置厂家
	 */
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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

	private byte cameras;

	/**
	 * 获取摄像头路数
	 */
	public byte getCameras() {
		return cameras;
	}

	/**
	 * 设置摄像头路数
	 */
	public void setCameras(byte cameras) {
		this.cameras = cameras;
	}

	private boolean hasMicrophone;

	/**
	 * 获取有无麦克风
	 */
	public boolean isHasMicrophone() {
		return hasMicrophone;
	}

	/**
	 * 设置有无麦克风
	 */
	public void setHasMicrophone(boolean hasMicrophone) {
		this.hasMicrophone = hasMicrophone;
	}

	private boolean hasNavigation;

	/**
	 * 获取有无导航屏
	 */
	public boolean isHasNavigation() {
		return hasNavigation;
	}

	/**
	 * 设置有无导航屏
	 */
	public void setHasNavigation(boolean hasNavigation) {
		this.hasNavigation = hasNavigation;
	}

	private String sensors;

	/**
	 * 获取传感器列表
	 */
	public String getSensors() {
		return sensors;
	}

	/**
	 * 设置传感器列表
	 */
	public void setSensors(String sensors) {
		this.sensors = sensors;
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

	private Date purchaseDate;

	/**
	 * 获取购买日期
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * 设置购买日期
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
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

	private Timestamp editTime;

	/**
	 * 获取设备时间戳
	 */
	public Timestamp getEditTime() {
		return editTime;
	}

	/**
	 * 设置设备时间戳
	 */
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
}
