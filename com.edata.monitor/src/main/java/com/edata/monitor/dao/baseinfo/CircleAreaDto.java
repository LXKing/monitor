package com.edata.monitor.dao.baseinfo;

import java.sql.Timestamp;
import java.util.Date;

public class CircleAreaDto {
	public long id;
	public String companyId;
	public String name;
	public boolean deviceCatch;
	public int flag;
	public double lat;
	public double lng;
	public int radius;
	public int maxSpeed;
	public short overspeedSeconds;
	public Date startTime;
	public Date endTime;
	public String remark;
	public Timestamp editTime;
}
