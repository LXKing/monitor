package com.edata.monitor.domain.devicedata;

import java.util.Date;
import java.util.List;

public class AccidentDoubtLog {
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private String license;

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	private double lng;

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	private double lat;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	private int alt;

	public int getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
		this.alt = alt;
	}

	private List<Short> content;

	public List<Short> getContent() {
		return content;
	}

	public void setContent(List<Short> content) {
		this.content = content;
	}
}
