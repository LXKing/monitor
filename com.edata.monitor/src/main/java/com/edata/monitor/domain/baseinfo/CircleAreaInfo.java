package com.edata.monitor.domain.baseinfo;

/**
 * 圆形区域信息类
 * 
 * @author yangzs
 *
 */
public class CircleAreaInfo {
	private long id;

	/**
	 * 获取唯一编号
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置唯一编号
	 */
	public void setId(long id) {
		this.id = id;
	}

	private String name;

	/**
	 * 获取区域名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置区域名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	private boolean deviceCatch = true;

	/**
	 * 获取终端计算否
	 */
	public boolean isDeviceCatch() {
		return deviceCatch;
	}

	/**
	 * 设置终端计算否
	 */
	public void setDeviceCatch(boolean deviceCatch) {
		this.deviceCatch = deviceCatch;
	}

	private double lat;

	/**
	 * 获取中心点纬度
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * 设置中心点纬度
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	private double lng;

	/**
	 * 获取中心点经度
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * 设置中心点经度
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	private int radius;

	/**
	 * 获取半径
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * 设置半径
	 */
	public void setRadius(int radius) {
		this.radius = radius;
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
