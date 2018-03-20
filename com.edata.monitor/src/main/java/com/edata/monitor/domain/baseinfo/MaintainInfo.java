package com.edata.monitor.domain.baseinfo;

import java.sql.Date;

public class MaintainInfo {
	private String id;

	/**
	 * 获取录唯一编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置录唯一编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String plateNumber;

	/**
	 * 获取车辆号
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	/**
	 * 设置车辆号
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	private Date doneDate;

	/**
	 * 获取保养日期
	 */
	public Date getDoneDate() {
		return doneDate;
	}

	/**
	 * 设置保养日期
	 */
	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	private String content;

	/**
	 * 获取保养内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置保养内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	private int mileage;

	/**
	 * 获取保养里程
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * 设置保养里程
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	private double fee;

	/**
	 * 获取保养费用
	 */
	public double getFee() {
		return fee;
	}

	/**
	 * 设置保养费用
	 */
	public void setFee(double fee) {
		this.fee = fee;
	}

	private Date nextDate;

	/**
	 * 获取下次保养日期
	 */
	public Date getNextDate() {
		return nextDate;
	}

	/**
	 * 设置下次保养日期
	 */
	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	private int nextMileage;

	/**
	 * 获取下次保养里程
	 */
	public int getNextMileage() {
		return nextMileage;
	}

	/**
	 * 设置下次保养里程
	 */
	public void setNextMileage(int nextMileage) {
		this.nextMileage = nextMileage;
	}

	private String userName;

	/**
	 * 获取操作员姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置操作员姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
