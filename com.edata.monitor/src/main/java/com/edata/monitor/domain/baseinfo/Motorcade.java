package com.edata.monitor.domain.baseinfo;

import java.sql.Timestamp;

/**
 * 车队类
 * 
 * @author yangzs
 *
 */
public class Motorcade {
	private String id;

	/**
	 * 获取记录ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置记录ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String companyId;

	/**
	 * 获取公司ID
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置公司ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private String type;

	/**
	 * 获取车队类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置车队类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	private String name;

	/**
	 * 获取名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
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

	private String linkMan;

	/**
	 * 获取车队联系人
	 */
	public String getLinkMan() {
		return linkMan;
	}

	/**
	 * 设置车队联系人
	 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	private String phone;

	/**
	 * 获取车队联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置车队联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
