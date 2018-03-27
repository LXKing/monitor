package com.rayton.gps.domain.gateway;

import java.util.Date;

/**
 * 多媒体保存请求
 *
 * @author yangzs
 */
public class MultimediaSaveRequest {
    private String id;
    private String number;
    private long mediaId;
    private byte mediaType;
    private byte formatType;
    private byte eventType;
    private byte channelId;
    private long alarms;
    private long status;
    private double lat;
    private double lng;
    private int altitude;
    private float speed;
    private int angle;
    private Date time;
    private byte[] data;

    /**
     * 获取记录唯一号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录唯一号
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getMediaId() {
        return mediaId;
    }

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public byte getMediaType() {
        return mediaType;
    }

    /**
     * 媒体类型:0 图像，1 音频，2 视频
     **/
    public void setMediaType(byte mediaType) {
        this.mediaType = mediaType;
    }

    public byte getFormatType() {
        return formatType;
    }

    /**
     * 格式类型
     */
    public void setFormatType(byte formatType) {
        this.formatType = formatType;
    }

    public byte getEventType() {
        return eventType;
    }

    /**
     * 事件类型 0:平台下发指令 1:定时动作 2:抢劫报警触发 3:碰撞侧翻报警触发
     */
    public void setEventType(byte eventType) {
        this.eventType = eventType;
    }

    public byte getChannelId() {
        return channelId;
    }

    /**
     * 道道ID
     **/
    public void setChannelId(byte channelId) {
        this.channelId = channelId;
    }

    public long getAlarms() {
        return alarms;
    }

    /**
     * 报警标志
     */
    public void setAlarms(long alarms) {
        this.alarms = alarms;
    }

    public long getStatus() {
        return status;
    }

    /**
     * 状态
     */
    public void setStatus(long status) {
        this.status = status;
    }

    public double getLat() {
        return lat;
    }

    /**
     * 纬度
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    /**
     * 经度
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getAltitude() {
        return altitude;
    }

    /**
     * 高程
     */
    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    /**
     * 速度
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    /**
     * 方向
     *
     * @param angle 0-359, 正北为 0 ，顺时针
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
