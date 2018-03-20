package com.edata.monitor.domain.devicedata;

import java.util.Date;
import java.util.List;

public class SpeedLog {
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private List<Short> content;

	public List<Short> getContent() {
		return content;
	}

	public void setContent(List<Short> content) {
		this.content = content;
	}
}
