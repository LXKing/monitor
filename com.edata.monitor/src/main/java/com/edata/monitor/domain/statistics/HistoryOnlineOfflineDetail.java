package com.edata.monitor.domain.statistics;

import java.util.Date;

public class HistoryOnlineOfflineDetail {
	private String motorcade;

	public String getMotorcade() {
		return motorcade;
	}

	public void setMotorcade(String motorcade) {
		this.motorcade = motorcade;
	}

	private String plateNumber;

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	private String deviceNumber;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
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

	private boolean online;

	public boolean getOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
