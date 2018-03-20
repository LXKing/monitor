package com.edata.monitor.domain.baseinfo;

import java.sql.Date;

public class DeviceCombinInfo {
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

	private String pid;

	/**
	 * 获取父ID
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * 设置父ID
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	private String deviceId;

	/**
	 * 获取设备ID
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 设置设备ID
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	private String userId;

	/**
	 * 获取用户ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String groupId;

	/**
	 * 获取组别ID
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 设置组别ID
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	private int type;

	/**
	 * 获取类型：0为设备组，1为设备
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置类型：0为设备组，1为设备
	 */
	public void setType(int type) {
		this.type = type;
	}

	private String number;

	/**
	 * 获取设备号
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * 设置设备号
	 */
	public void setNumber(String number) {
		this.number = number;
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

	private Integer enable;

	/**
	 * 获取是否启用
	 */
	public Integer getEnable() {
		return enable;
	}

	/**
	 * 设置是否启用
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	private String sim;

	/**
	 * 获取电话
	 */
	public String getSim() {
		return sim;
	}

	/**
	 * 设置电话
	 */
	public void setSim(String sim) {
		this.sim = sim;
	}

	private Date ServiceStartTime;

	/**
	 * 获取服务开始时间
	 */
	public Date getServiceStartTime() {
		return ServiceStartTime;
	}

	/**
	 * 设置服务开始时间
	 */
	public void setServiceStartTime(Date serviceStartTime) {
		ServiceStartTime = serviceStartTime;
	}

	private Date serviceEndTime;

	/**
	 * 获取服务结束时间
	 */
	public Date getServiceEndTime() {
		return serviceEndTime;
	}

	/**
	 * 设置服务结束时间
	 */
	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
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
