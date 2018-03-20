package com.edata.monitor.domain.devicedata;

import java.util.Date;

/**
 * 图片信息类
 * 
 * @author yangzs
 *
 */
public class PhotoInfo {
	private String id;

	/**
	 * 获取记录号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置记录号
	 */
	public void setId(String id) {
		this.id = id;
	}

	private Date gt;

	/**
	 * 获取时间
	 */
	public Date getGt() {
		return gt;
	}

	/**
	 * 设置时间
	 */
	public void setGt(Date gt) {
		this.gt = gt;
	}

	private long mediaId;

	/**
	 * 获取媒体id
	 */
	public long getMediaId() {
		return mediaId;
	}

	/**
	 * 设置媒体id
	 */
	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}

	private String mediaType;

	/**
	 * 获取媒体类型
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * 设置媒体类型
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	private String formatType;

	/**
	 * 获取格式类型
	 */
	public String getFormatType() {
		return formatType;
	}

	/**
	 * 设置格式类型
	 */
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	private String eventType;

	/**
	 * 获取事件类型
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * 设置事件类型
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	private byte channelId;

	/**
	 * 获取通道id
	 */
	public byte getChannelId() {
		return channelId;
	}

	/**
	 * 设置通道id
	 */
	public void setChannelId(byte channelId) {
		this.channelId = channelId;
	}

	private double lng;

	/**
	 * 获取经度
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * 设置经度
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	private double lat;

	/**
	 * 获取纬度
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * 设置纬度
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	private float sp;

	/**
	 * 获取速度
	 */
	public float getSp() {
		return sp;
	}

	/**
	 * 设置速度
	 */
	public void setSp(float sp) {
		this.sp = sp;
	}

	private int d;

	/**
	 * 获取方向
	 */
	public int getD() {
		return d;
	}

	/**
	 * 设置方向
	 */
	public void setD(int d) {
		this.d = d;
	}

	private long a;

	/**
	 * 获取报警
	 */
	public long getA() {
		return a;
	}

	/**
	 * 设置报警
	 */
	public void setA(long a) {
		this.a = a;
	}

	private long s;

	/**
	 * 获取状态
	 */
	public long getS() {
		return s;
	}

	/**
	 * 设置状态
	 */
	public void setS(long s) {
		this.s = s;
	}
}
