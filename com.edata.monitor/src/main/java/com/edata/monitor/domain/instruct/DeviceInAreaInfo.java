package com.edata.monitor.domain.instruct;

import java.util.Date;

public class DeviceInAreaInfo {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String deviceNumber;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	private long areaId;

	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	private byte areaType;

	public byte getAreaType() {
		return areaType;
	}

	public void setAreaType(byte areaType) {
		this.areaType = areaType;
	}

	private byte action;

	public byte getAction() {
		return action;
	}

	public void setAction(byte action) {
		this.action = action;
	}

	private String unid;

	public String getUnid() {
		return unid;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}

	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private Date sendTime;

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	private Date ackTime;

	public Date getAckTime() {
		return ackTime;
	}

	public void setAckTime(Date ackTime) {
		this.ackTime = ackTime;
	}
}
