package com.edata.monitor.domain.locate;

import java.util.Date;

public class Fault {
	/**
	 * 设备id
	 */
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * GPS时间
	 */
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * 系统id
	 */
	private short sysId;

	public short getSysId() {
		return sysId;
	}

	public void setSysId(short sysId) {
		this.sysId = sysId;
	}

	/**
	 * 模式id
	 */
	private short modeId;

	public short getModeId() {
		return modeId;
	}

	public void setModeId(short modeId) {
		this.modeId = modeId;
	}

	/**
	 * 第一字节
	 */
	private short code1;

	public short getCode1() {
		return code1;
	}

	public void setCode1(short code1) {
		this.code1 = code1;
	}

	/**
	 * 第二字节
	 */
	private short code2;

	public short getCode2() {
		return code2;
	}

	public void setCode2(short code2) {
		this.code2 = code2;
	}

	/**
	 * 第三字节
	 */
	private short code3;

	public short getCode3() {
		return code3;
	}

	public void setCode3(short code3) {
		this.code3 = code3;
	}
}
