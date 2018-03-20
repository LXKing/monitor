package com.edata.monitor.domain.statistics;

import java.util.Date;

public class MileageOilCount {
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

	private int vehicles;

	public int getVehicles() {
		return vehicles;
	}

	public void setVehicles(int vehicles) {
		this.vehicles = vehicles;
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
