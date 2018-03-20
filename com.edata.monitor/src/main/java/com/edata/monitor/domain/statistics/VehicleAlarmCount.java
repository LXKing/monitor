package com.edata.monitor.domain.statistics;

import java.util.Date;

public class VehicleAlarmCount {
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

	private int overspeedNoneArea;

	public int getOverspeedNoneArea() {
		return overspeedNoneArea;
	}

	public void setOverspeedNoneArea(int overspeedNoneArea) {
		this.overspeedNoneArea = overspeedNoneArea;
	}

	private int overspeedInArea;

	public int getOverspeedInArea() {
		return overspeedInArea;
	}

	public void setOverspeedInArea(int overspeedInArea) {
		this.overspeedInArea = overspeedInArea;
	}

	private int overspeedInSection;

	public int getOverspeedInSection() {
		return overspeedInSection;
	}

	public void setOverspeedInSection(int overspeedInSection) {
		this.overspeedInSection = overspeedInSection;
	}

	private int fatigueDriving;

	public int getFatigueDriving() {
		return fatigueDriving;
	}

	public void setFatigueDriving(int fatigueDriving) {
		this.fatigueDriving = fatigueDriving;
	}

	private int parkingTimeout;

	public int getParkingTimeout() {
		return parkingTimeout;
	}

	public void setParkingTimeout(int parkingTimeout) {
		this.parkingTimeout = parkingTimeout;
	}

	private int routeDeparture;

	public int getRouteDeparture() {
		return routeDeparture;
	}

	public void setRouteDeparture(int routeDeparture) {
		this.routeDeparture = routeDeparture;
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
