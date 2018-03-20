package com.edata.monitor.dao.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 车辆保养数据传输类
 * 
 * @author yangzs
 *
 */
public class MaintainDto {
	public String id;
	public String companyId;
	public String vehicleId;
	public String plateNumber;
	public Date doneDate;
	public String type;
	public String content;
	public int mileage;
	public double fee;
	public String garage;
	public String agent;
	public Date nextDate;
	public int nextMileage;
	public String userId;
	public String userName;
	public Timestamp editTime;
}
