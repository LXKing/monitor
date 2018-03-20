package com.edata.monitor.domain.cache;

public class MonitorTarget {
	private String id;

	/**
	 * 获取组别或车辆id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置组别或车辆id
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String pid;

	/**
	 * 获取组别或车辆的父id
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * 设置组别或车辆的父id
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	private int type;

	/**
	 * 获取类型:0车辆，1车队,2企业
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置类型:0车辆，1车队,2企业
	 */
	public void setType(int type) {
		this.type = type;
	}

	private String name;

	/**
	 * 获取名称，车队名、车牌号、企业名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称，车队名、车牌号、企业名
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String deviceNumber;

	/**
	 * 获取设备号
	 */
	public String getDeviceNumber() {
		return deviceNumber;
	}

	/**
	 * 设置设备号
	 */
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	
	private String marker;

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	private int rotate;

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj.hashCode() == this.hashCode();
	}
}
