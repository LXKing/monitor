package com.edata.monitor.domain.devicedata;

import java.util.Date;
import java.util.List;

public class SpeedStatusLog {
	private Date startTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	private Date endTime;

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	private List<Short> content;

	public List<Short> getContent() {
		return content;
	}

	public void setContent(List<Short> content) {
		this.content = content;
	}
}
