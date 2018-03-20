package com.edata.monitor.domain.baseinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edata.common.LatLng;

/**
 * 多边形区域类
 * 
 * @author yangzs
 *
 */
public class PolygonArea {
	private long id;

	/**
	 * 获取记录唯一编号
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置记录唯一编号
	 */
	public void setId(long id) {
		this.id = id;
	}

	private String companyId;

	/**
	 * 获取企业唯一编号
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置企业唯一编号
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	private boolean deviceCatch;

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

	private int flag;

	/**
	 * 获取属性
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * 设置属性
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	private int maxSpeed;

	/**
	 * 获取最高速度
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * 设置最高速度
	 */
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	private short overspeedSeconds;

	/**
	 * 获取超速持续时间
	 */
	public short getOverspeedSeconds() {
		return overspeedSeconds;
	}

	/**
	 * 设置超速持续时间
	 */
	public void setOverspeedSeconds(short overspeedSeconds) {
		this.overspeedSeconds = overspeedSeconds;
	}

	private Date startTime;

	/**
	 * 获取开始时间
	 */
	public Date getStartTime() {
		return startTime;
	};

	/**
	 * 设置开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	private Date endTime;

	/**
	 * 获取结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	private List<LatLng> points = new ArrayList<LatLng>();

	/**
	 * 获取路径点
	 */
	public List<LatLng> getPoints() {
		return points;
	}

	/**
	 * 设置路径点
	 */
	public void setPoints(List<LatLng> points) {
		this.points = points;
	}
}
