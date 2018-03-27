package com.rayton.gps.dao.common;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public long getAlarms() {
        return alarms;
    }

    public void setAlarms(long alarms) {
        this.alarms = alarms;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public float getOil() {
        return oil;
    }

    public void setOil(float oil) {
        this.oil = oil;
    }

    public float getVss() {
        return vss;
    }

    public void setVss(float vss) {
        this.vss = vss;
    }

    public byte getOvt() {
        return ovt;
    }

    public void setOvt(byte ovt) {
        this.ovt = ovt;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public byte getIot() {
        return iot;
    }

    public void setIot(byte iot) {
        this.iot = iot;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public byte getIof() {
        return iof;
    }

    public void setIof(byte iof) {
        this.iof = iof;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public byte getRf() {
        return rf;
    }

    public void setRf(byte rf) {
        this.rf = rf;
    }
}
