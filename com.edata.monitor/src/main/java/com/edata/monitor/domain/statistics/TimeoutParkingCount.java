package com.edata.monitor.domain.statistics;

import java.util.Date;

public class TimeoutParkingCount {
	private String motorcadeId;

	public String getMotorcadeId() {
		return motorcadeId;
	}

	public void setMotorcadeId(String motorcadeId) {
		this.motorcadeId = motorcadeId;
	}

	private String motorcade;

	public String getMotorcade() {
		return motorcade;
	}

	public void setMotorcade(String motorcade) {
		this.motorcade = motorcade;
	}

	private int times;

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	
	private long duration;

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	private Date start;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	private Date end;

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
