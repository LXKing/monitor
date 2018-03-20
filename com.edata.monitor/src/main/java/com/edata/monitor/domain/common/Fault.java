package com.edata.monitor.domain.common;

import java.util.Date;

public class Fault {
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private int systemId;

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	private String descriptionC;

	public String getDescriptionC() {
		return descriptionC;
	}

	public void setDescriptionC(String descriptionC) {
		this.descriptionC = descriptionC;
	}

	private String descriptionE;

	public String getDescriptionE() {
		return descriptionE;
	}

	public void setDescriptionE(String descriptionE) {
		this.descriptionE = descriptionE;
	}

	private String solution;

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	private String sensors;

	public String getSensors() {
		return sensors;
	}

	public void setSensors(String sensors) {
		this.sensors = sensors;
	}
}
