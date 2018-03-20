package com.edata.monitor.domain.common;

import java.util.Date;

public class Trip {
	private String number;

	/**
	 * 获取设备号
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * 设置设备号
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	private Date startTime;

	/**
	 * 获取开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	private Date endTime;

	/**
	 * 获取结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private long mileage;

	/**
	 * 获取里程
	 */
	public long getMileage() {
		return mileage;
	}

	/**
	 * 设置里程
	 */
	public void setMileage(long mileage2) {
		this.mileage = mileage2;
	}

	private short maxSpeed;

	/**
	 * 获取最高速度
	 */
	public short getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * 设置最主速度
	 */
	public void setMaxSpeed(short maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	private short avgSpeed;

	/**
	 * 获取平均速度
	 */
	public short getAvgSpeed() {
		return avgSpeed;
	}

	/**
	 * 设置平均速度
	 */
	public void setAvgSpeed(short avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	private short overSpeed;

	/**
	 * 获取超速次数
	 */
	public short getOverSpeed() {
		return overSpeed;
	}

	/**
	 * 设置超速次数
	 */
	public void setOverSpeed(short overSpeed) {
		this.overSpeed = overSpeed;
	}

	private short overSpeedTime;

	/**
	 * 获取超速时长
	 */
	public short getOverSpeedTime() {
		return overSpeedTime;
	}

	/**
	 * 设置超速时长
	 */
	public void setOverSpeedTime(short overSpeedTime) {
		this.overSpeedTime = overSpeedTime;
	}

	private short breaks;

	/**
	 * 获取急刹车次数
	 */
	public short getBreaks() {
		return breaks;
	}

	/**
	 * 设置急刹车次数
	 */
	public void setBreaks(short breaks) {
		this.breaks = breaks;
	}

	private short speedUp;

	/**
	 * 获取急加速次数
	 */
	public short getSpeedUp() {
		return speedUp;
	}

	/**
	 * 设置急加速次数
	 */
	public void setSpeedUp(short speedUp) {
		this.speedUp = speedUp;
	}

	private byte maxEct;

	/**
	 * 获取最高水温
	 */
	public byte getMaxEct() {
		return maxEct;
	}

	/**
	 * 设置最高水温
	 */
	public void setMaxEct(byte maxEct) {
		this.maxEct = maxEct;
	}

	private short maxRpm;

	/**
	 * 获取最高转速
	 */
	public short getMaxRpm() {
		return maxRpm;
	}

	/**
	 * 设置最高转速
	 */
	public void setMaxRpm(short maxRpm) {
		this.maxRpm = maxRpm;
	}

	private float avgBv;

	/**
	 * 获取电池平均电压
	 */
	public float getAvgBv() {
		return avgBv;
	}

	/**
	 * 设置电池平均电压
	 */
	public void setAvgBv(float avgBv) {
		this.avgBv = avgBv;
	}

	private float totalOil;

	/**
	 * 获取总油耗
	 */
	public float getTotalOil() {
		return totalOil;
	}

	/**
	 * 设置总油耗
	 */
	public void setTotalOil(float totalOil) {
		this.totalOil = totalOil;
	}

	private float avgOil;

	/**
	 * 获取平均油耗
	 */
	public float getAvgOil() {
		return avgOil;
	}

	/**
	 * 设置平均油耗
	 */
	public void setAvgOil(float avgOil) {
		this.avgOil = avgOil;
	}

	private int fatigueTime;

	/**
	 * 获取疲驾时长
	 */
	public int getFatigueTime() {
		return fatigueTime;
	}

	/**
	 * 设置疲驾时长
	 */
	public void setFatigueTime(int fatigueTime) {
		this.fatigueTime = fatigueTime;
	}

	private int idleTime;

	/**
	 * 获取怠速时长
	 */
	public int getIdleTime() {
		return idleTime;
	}

	/**
	 * 设置怠速时长
	 */
	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}

}
