package com.edata.monitor.domain.cache;


public class AreaInfo {
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
}
