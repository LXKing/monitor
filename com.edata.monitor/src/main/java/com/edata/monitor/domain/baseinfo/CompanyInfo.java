package com.edata.monitor.domain.baseinfo;

import java.util.Date;

/**
 * 公司信息
 * 
 * @author yangzs
 *
 */
public class CompanyInfo {
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

	private String fullName;

	/**
	 * 获取全称
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 设置全称
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private String shortName;

	/**
	 * 获取简称
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * 设置简称
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	private String officeAddress;

	/**
	 * 获取办公地址
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * 设置办公地址
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
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

	private String ondutyPhone;

	public String getOndutyPhone() {
		return ondutyPhone;
	}

	public void setOndutyPhone(String ondutyPhone) {
		this.ondutyPhone = ondutyPhone;
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
