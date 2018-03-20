package com.edata.monitor.domain.locate;

/**
 * 车主基本资料类
 * 
 * @author yangzs
 *
 */
public class OwnerBaseInfo {
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

	private String email;

	/**
	 * 获取邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
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
