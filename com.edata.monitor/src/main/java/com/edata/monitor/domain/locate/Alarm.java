package com.edata.monitor.domain.locate;

public class Alarm extends Latest {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private byte from;

	public byte getFrom() {
		return from;
	}

	public void setFrom(byte from) {
		this.from = from;
	}
}
