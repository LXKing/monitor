package com.edata.monitor.model.baseinfo;

import com.edata.monitor.domain.baseinfo.PolygonArea;

public class PolygonAreaModel extends PolygonArea {
	private String path;

	/**
	 * 获取路径点json字符
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置路径点json字符
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
