package com.edata.monitor.dao.baseinfo;

import java.sql.Date;

/**
 * 设备信息数据传输类
 * 
 * @author yangzs
 *
 */
public class DeviceInfoDto {
	/**
	 * 记录ID
	 */
	public String id;
	/**
	 * 设备号
	 */
	public String deviceNumber;
	/**
	 * SIM号
	 */
	public String phoneNumber;
	/**
	 * 出厂号
	 */
	public String factoryNumber;
	/**
	 * 型号
	 */
	public String model;
	/**
	 * 是否启用
	 */
	public boolean enable;
	/**
	 * 服务开始时间
	 */
	public Date serviceStartDate;

	/**
	 * 服务结束时间
	 */
	public Date serviceEndDate;
	/**
	 * 保修期
	 */
	public Date warranty;
	/**
	 * 备注
	 */
	public String remark;
}
