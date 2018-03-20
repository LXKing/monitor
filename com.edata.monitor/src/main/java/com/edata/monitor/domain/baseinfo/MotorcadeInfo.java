package com.edata.monitor.domain.baseinfo;

/**
 * 车队信息类
 * 
 * @author yangzs
 *
 */
public class MotorcadeInfo {
	private String id;

	/**
	 * 获取记录ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置记录ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String type;

	/**
	 * 获取车队类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置车队类型
	 */
	public void setType(String type) {
		this.type = type;
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

	private boolean parentVisible;

	/**
	 * 获取上级可见否
	 */
	public boolean isParentVisible() {
		return parentVisible;
	}

	/**
	 * 设置上级可见否
	 */
	public void setParentVisible(boolean parentVisible) {
		this.parentVisible = parentVisible;
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
