package com.edata.monitor.domain.baseinfo;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Owner {
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
