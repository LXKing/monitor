package com.edata.monitor.dao.common;

import java.util.Date;

public class AlarmDto {
	/**
	 * 时间
	 */
	public Date time;
	/**
	 * 经度
	 */
	public double lng;
	/**
	 * 纬度
	 */
	public double lat;
	/**
	 * 速度
	 */
	public int speed;
	/**
	 * 方向
	 */
	public int direct;
	/**
	 * 报警
	 */
	public long alarms;
	/**
	 * 状态
	 */
	public long status;
	/**
	 * 里程
	 */
	public double m;
	/**
	 * 油量
	 */
	public float oil;

	/**
	 * 获取车速
	 */
	public float vss;

	/**
	 * 超速区域类型
	 */
	public byte ovt;

	/**
	 * 超速区域id
	 */
	public long oid;

	/**
	 * 进出区域类型
	 */
	public byte iot;

	/**
	 * 进出区域id
	 */
	public long iid;

	/**
	 * 进出区域方向
	 */
	public byte iof;

	/**
	 * 行驶路段id
	 */
	public long rid;

	/**
	 * 行驶路段时间
	 */
	public int rt;

	/**
	 * 行驶路段结果
	 */
	public byte rf;

}
