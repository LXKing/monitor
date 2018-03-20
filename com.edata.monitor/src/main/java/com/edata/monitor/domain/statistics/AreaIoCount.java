package com.edata.monitor.domain.statistics;

import java.util.Date;

public class AreaIoCount {
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

	private int in;

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	private int out;

	public int getOut() {
		return out;
	}

	public void setOut(int out) {
		this.out = out;
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
