package com.edata.monitor.domain.locate;

import java.util.Date;

public class MultimediaEventReport {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private String plateNumber;

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
}
