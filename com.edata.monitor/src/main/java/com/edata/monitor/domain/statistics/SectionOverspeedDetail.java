package com.edata.monitor.domain.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edata.monitor.dao.statistics.SectionOverspeedDto;

public class SectionOverspeedDetail {
	private String deviceNumber;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

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

	private List<SectionOverspeedDto> detail = new ArrayList<SectionOverspeedDto>();

	public List<SectionOverspeedDto> getDetail() {
		return detail;
	}

	public void setDetail(List<SectionOverspeedDto> detail) {
		this.detail = detail;
	}
}
