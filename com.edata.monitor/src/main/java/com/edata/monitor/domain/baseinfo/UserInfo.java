package com.edata.monitor.domain.baseinfo;

import java.sql.Date;

/**
 * 用户信息类
 * 
 * @author 生
 *
 */
public class UserInfo {
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

	private Date ServiceStartDate;

	/**
	 * 获取服务开始时间
	 */
	public Date getServiceStartDate() {
		return ServiceStartDate;
	}

	/**
	 * 设置服务开始时间
	 */
	public void setServiceStartDate(Date serviceStartDate) {
		ServiceStartDate = serviceStartDate;
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

	private Date createTime;

	/**
	 * 获取注册时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置注册时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
