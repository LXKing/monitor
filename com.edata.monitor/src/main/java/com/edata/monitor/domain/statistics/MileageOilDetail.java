package com.edata.monitor.domain.statistics;

import java.util.Date;

public class MileageOilDetail {
	private String motorcade;

	public String getMotorcade() {
		return motorcade;
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

	private double mileages;

	public double getMileages() {
		return mileages;
	}

	public void setMileages(double mileages) {
		this.mileages = mileages;
	}

	private double oils;

	public double getOils() {
		return oils;
	}

	public void setOils(double oils) {
		this.oils = oils;
	}

}
