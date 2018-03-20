package com.edata.monitor.domain.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 公司资料
 * 
 * @author yangzs
 *
 */
public class Company {
	private String id;

	public String getId() {
		return id;
	}

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

	@NotEmpty
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

	private String organCode;

	/**
	 * 获取组织机构编号
	 */
	public String getOrganCode() {
		return organCode;
	}

	/**
	 * 设置组织机构编号
	 */
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	private Date registDate;

	/**
	 * 获取注册日期
	 */
	public Date getRegistDate() {
		return registDate;
	}

	/**
	 * 设置注册日期
	 */
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	private String corporation;

	/**
	 * 获取法人代表
	 */
	public String getCorporation() {
		return corporation;
	}

	/**
	 * 设置法人代表
	 */
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	private String ondutyPhone;

	/**
	 * 获取24小时值班电话
	 */
	public String getOndutyPhone() {
		return ondutyPhone;
	}

	/**
	 * 设置24小时值班电话
	 */
	public void setOndutyPhone(String ondutyPhone) {
		this.ondutyPhone = ondutyPhone;
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

	private String registAddress;

	/**
	 * 获取注册地址
	 */
	public String getRegistAddress() {
		return registAddress;
	}

	/**
	 * 设置注册地址
	 */
	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}

	private boolean parentVisible;

	/**
	 * 获取上级可见否
	 */
	public boolean isParentVisible() {
		return parentVisible;
	}

	/**
	 * 设置上级可见否
	 */
	public void setParentVisible(boolean parentVisible) {
		this.parentVisible = parentVisible;
	}

	private Timestamp editTime;

	/**
	 * 获取公司记录时间戳
	 */
	public Timestamp getEditTime() {
		return editTime;
	}

	/**
	 * 设置公司记录时间戳
	 */
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
}
