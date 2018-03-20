package com.edata.monitor.domain.statistics;

import java.util.Date;

public class HistoryOnlineTimeDetail {
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

	private int must;

	public int getMust() {
		return must;
	}

	public void setMust(int must) {
		this.must = must;
	}

	private int real;

	public int getReal() {
		return real;
	}

	public void setReal(int real) {
		this.real = real;
	}

	private float onlineRate;

	public float getOnlineRate() {
		return onlineRate;
	}

	public void setOnlineRate(float onlineRate) {
		this.onlineRate = onlineRate;
	}

	private float offlineRate;

	public float getOfflineRate() {
		return offlineRate;
	}

	public void setOfflineRate(float offlineRate) {
		this.offlineRate = offlineRate;
	}
}
