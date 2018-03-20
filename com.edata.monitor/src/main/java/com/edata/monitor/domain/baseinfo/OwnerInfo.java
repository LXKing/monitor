package com.edata.monitor.domain.baseinfo;

import java.sql.Date;

public class OwnerInfo {
	private String id;

	/**
	 * 获取记录唯一编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置记录唯一编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String ownerName;

	/**
	 * 获取姓名
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * 设置姓名
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	private String phone;

	/**
	 * 获取电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String idType;

	/**
	 * 获取证件类型
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * 设置证件类型
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	private String idNumber;

	/**
	 * 获取证件编号
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * 设置证件编号
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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

	private boolean enabled;

	/**
	 * 获取启用否
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 设置启用否
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private Date createTime;

	/**
	 * 获取入网时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置入网时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
