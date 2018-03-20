package com.edata.monitor.domain.baseinfo;

/**
 * 路段点类
 * 
 * @author yangzs
 *
 */
public class SectionPoint {

	private long id;

	/**
	 * 获取拐点id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置拐点id
	 */
	public void setId(long id) {
		this.id = id;
	}

	private Long sectionId;

	/**
	 * 获取路段id
	 */
	public Long getSectionId() {
		return sectionId;
	}

	/**
	 * 设置路段id
	 */
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	private double lat;

	/**
	 * 获取拐点纬度
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * 设置拐点纬度
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	private double lng;

	/**
	 * 获取拐点经度
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * 设置拐点经度
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
