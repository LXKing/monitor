package com.edata.monitor.model.baseinfo;

import java.util.Map;

public class UserOptionModel {
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private Map<String, Object> options;

	public Map<String, Object> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}

}
