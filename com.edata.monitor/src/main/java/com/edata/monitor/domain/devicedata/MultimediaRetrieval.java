package com.edata.monitor.domain.devicedata;

import java.util.Date;

public class MultimediaRetrieval {
	/**
	 * 设备号
	 */
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 多媒体ID
	 */
	private long mediaId;

	public long getMediaId() {
		return mediaId;
	}

	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * 多媒体类型
	 */
	public String mediaType;

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * 通道ID
	 */
	public byte channelId;

	public byte getChannelId() {
		return channelId;
	}

	public void setChannelId(byte channelId) {
		this.channelId = channelId;
	}

	/**
	 * 事件类型
	 */
	public String eventType;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * 报警标志
	 */
	public long a;

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	/**
	 * 状态
	 */
	public long s;

	public long getS() {
		return s;
	}

	public void setS(long s) {
		this.s = s;
	}

	/**
	 * 纬度
	 */
	public double lat;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * 经度
	 */
	public double lng;

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	/**
	 * 高程
	 */
	public int alt;

	public int getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
		this.alt = alt;
	}

	/**
	 * 速度
	 */
	public float sp;

	public float getSp() {
		return sp;
	}

	public void setSp(float sp) {
		this.sp = sp;
	}

	/**
	 * 方向
	 */
	public int d;

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	/**
	 * 时间
	 */
	public Date gt;

	public Date getGt() {
		return gt;
	}

	public void setGt(Date gt) {
		this.gt = gt;
	}
}
