package com.edata.monitor.domain.devicedata;

import java.util.Date;

public class TimeoutLog {
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

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

	private String license;

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	private double startLng;

	public double getStartLng() {
		return startLng;
	}

	public void setStartLng(double startLng) {
		this.startLng = startLng;
	}

	private double startLat;

	public double getStartLat() {
		return startLat;
	}

	public void setStartLat(double startLat) {
		this.startLat = startLat;
	}

	private int startAlt;

	public int getStartAlt() {
		return startAlt;
	}

	public void setStartAlt(int startAlt) {
		this.startAlt = startAlt;
	}

	private double endLng;

	public double getEndLng() {
		return endLng;
	}

	public void setEndLng(double endLng) {
		this.endLng = endLng;
	}

	private double endLat;

	public double getEndLat() {
		return endLat;
	}

	public void setEndLat(double endLat) {
		this.endLat = endLat;
	}

	private int endAlt;

	public int getEndAlt() {
		return endAlt;
	}

	public void setEndAlt(int endAlt) {
		this.endAlt = endAlt;
	}
}
