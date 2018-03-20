package com.edata.monitor.domain.locate;

/**
 * Godp数据块类
 * 
 * @author yangzs
 *
 */
public class GodpDataBlock {
	private String kind;

	/**
	 * 获取数据类型
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * 设置数据类型
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	private Object data;

	/**
	 * 获取数据
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 设置数据
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
