package com.edata.monitor.domain.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 驾驶员类
 * 
 * @author yangzs
 *
 */
public class Driver {
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

	private String companyId;

	/**
	 * 获取公司唯一编号
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置公司唯一编号
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@NotBlank
	private String name;

	/**
	 * 获取姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String sex="男";

	/**
	 * 获取性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	 * 获取证件号
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * 设置证件号
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@NotBlank
	private String drivingLicenseNumber;

	/**
	 * 获取驾驶证号
	 */
	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	/**
	 * 设置驾驶证号
	 */
	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	private Date drivingLicenseExpiryDate;

	/**
	 * 获取驾驶证有效日期
	 */
	public Date getDrivingLicenseExpiryDate() {
		return drivingLicenseExpiryDate;
	}

	/**
	 * 设置驾驶证有效日期
	 */
	public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate) {
		this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
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
