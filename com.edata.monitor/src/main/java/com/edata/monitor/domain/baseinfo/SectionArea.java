package com.edata.monitor.domain.baseinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 路段类
 * 
 * @author yangzs
 *
 */
public class SectionArea {
	private long id;

	/**
	 * 获取路段id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置路段id
	 */
	public void setId(long id) {
		this.id = id;
	}

	private String companyId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private short width;

	/**
	 * 获取路段宽度
	 */
	public short getWidth() {
		return width;
	}

	/**
	 * 设置路段宽度
	 */
	public void setWidth(short width) {
		this.width = width;
	}

	private short flag;

	/**
	 * 获取路段属性
	 */
	public short getFlag() {
		return flag;
	}

	/**
	 * 设置路段属性
	 */
	public void setFlag(short flag) {
		this.flag = flag;
	}

	private int maxSeconds;

	/**
	 * 获取路段行驶过长阈值
	 */
	public int getMaxSeconds() {
		return maxSeconds;
	}

	/**
	 * 设置路段行驶过长阈值
	 */
	public void setMaxSeconds(int maxSeconds) {
		this.maxSeconds = maxSeconds;
	}

	private int minSeconds;

	/**
	 * 获取路段行驶不足阈值
	 */
	public int getMinSeconds() {
		return minSeconds;
	}

	/**
	 * 设置路段行驶不足阈值
	 */
	public void setMinSeconds(int minSeconds) {
		this.minSeconds = minSeconds;
	}

	private int maxSpeed;

	/**
	 * 获取路段最高速度
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * 设置路段最高速度
	 */
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	private short overspeedSeconds;

	/**
	 * 获取路段超速持续时间
	 */
	public short getOverspeedSeconds() {
		return overspeedSeconds;
	}

	/**
	 * 设置路段超速持续时间
	 */
	public void setOverspeedSeconds(short overspeedSeconds) {
		this.overspeedSeconds = overspeedSeconds;
	}

	private String remark;

	public String getRemark() {
		return remark;
	}

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

	private List<SectionPoint> points = new ArrayList<SectionPoint>();

	/**
	 * 获取路径点
	 */
	public List<SectionPoint> getPoints() {
		return points;
	}

	/**
	 * 设置路径点
	 */
	public void setPoints(List<SectionPoint> points) {
		this.points = points;
	}
}
