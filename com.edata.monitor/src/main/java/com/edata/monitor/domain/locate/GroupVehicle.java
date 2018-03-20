package com.edata.monitor.domain.locate;

/**
 * 组别车辆
 * 
 * @author yangzs
 *
 */
public class GroupVehicle extends Latest {
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

	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
}
