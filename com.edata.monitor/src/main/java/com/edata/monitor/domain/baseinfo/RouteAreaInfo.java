package com.edata.monitor.domain.baseinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩形区域信息
 * 
 * @author yangzs
 *
 */
public class RouteAreaInfo {
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

	private List<SectionAreaInfo> sections = new ArrayList<SectionAreaInfo>();

	/**
	 * 获取路段
	 */
	public List<SectionAreaInfo> getSections() {
		return sections;
	}

	/**
	 * 设置路段
	 */
	public void setSections(List<SectionAreaInfo> sections) {
		this.sections = sections;
	}
}
