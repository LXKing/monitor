package com.edata.monitor.dao;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	/**
	 * 数据列表
	 */
	public List<T> rows = new ArrayList<T>();
	/**
	 * 总记录数
	 */
	public int total;
}
