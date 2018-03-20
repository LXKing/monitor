package com.edata.monitor.domain.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 车辆保养类
 * 
 * @author yangzs
 *
 */
public class Maintain {
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

	private String companyId;

	/**
	 * 获取公司唯一编号
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置公司唯一编号
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@NotEmpty
	private String vehicleId;

	/**
	 * 获取车辆id
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * 设置车辆id
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	@NotEmpty
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

	private String type;

	/**
	 * 获取保养类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置保养类型
	 */
	public void setType(String type) {
		this.type = type;
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

	private String garage;

	/**
	 * 获取保养单位
	 */
	public String getGarage() {
		return garage;
	}

	/**
	 * 设置保养单位
	 */
	public void setGarage(String garage) {
		this.garage = garage;
	}

	private String agent;

	/**
	 * 获取经办人
	 */
	public String getAgent() {
		return agent;
	}

	/**
	 * 设置经办人
	 */
	public void setAgent(String agent) {
		this.agent = agent;
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

	private String userId;

	/**
	 * 获取操作员ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置操作员ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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

	private Timestamp editTime;

	/**
	 * 获取时间戳
	 */
	public Timestamp getEditTime() {
		return editTime;
	}

	/**
	 * 设置时间戳
	 */
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

}
