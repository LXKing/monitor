package com.edata.monitor.domain.baseinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩形区域信息
 * 
 * @author yangzs
 *
 */
public class SectionAreaInfo {
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
