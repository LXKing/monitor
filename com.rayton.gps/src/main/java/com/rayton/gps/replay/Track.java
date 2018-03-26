package com.rayton.gps.replay;

import java.util.Date;

public class Track {
    private Date gt;
    private Date st;
    private double lng;
    private double lat;
    private float sp;
    private int d;
    private long a;
    private long s;
    private double m;
    private float oil;
    private float vss;
    private byte ovt;
    private long oid;
    private byte iot;
    private long iid;
    private byte iof;
    private long rid;
    private int rt;
    private byte rf;
    private int aid;
    private long exs;
    private int ios;
    private int ad0;
    private int ad1;
    private short net;
    private short sat;

    /**
     * 获取定位时间
     */
    public Date getGt() {
        return gt;
    }

    /**
     * 设置定位时间
     */
    public void setGt(Date gt) {
        this.gt = gt;
    }

    /**
     * 获取服务器时间
     */
    public Date getSt() {
        return st;
    }

    /**
     * 设置服务器时间
     */
    public void setSt(Date st) {
        this.st = st;
    }

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

    /**
     * 获取里程
     */
    public double getM() {
        return m;
    }

    /**
     * 设置里程
     */
    public void setM(double m) {
        this.m = m;
    }

    /**
     * 获取油量
     */
    public float getOil() {
        return oil;
    }

    /**
     * 设置油量
     */
    public void setOil(float oil) {
        this.oil = oil;
    }

    /**
     * 获取车速
     */
    public float getVss() {
        return vss;
    }

    /**
     * 设置车速
     */
    public void setVss(float vss) {
        this.vss = vss;
    }

    /**
     * 获取超速区域类型
     */
    public byte getOvt() {
        return ovt;
    }

    /**
     * 设置超速区域类型
     */
    public void setOvt(byte ovt) {
        this.ovt = ovt;
    }

    /**
     * 获取超速区域id
     */
    public long getOid() {
        return oid;
    }

    /**
     * 设置超速区域id
     */
    public void setOid(long oid) {
        this.oid = oid;
    }

    /**
     * 获取进出区域类型
     */
    public byte getIot() {
        return iot;
    }

    /**
     * 设置进出区域类型
     */
    public void setIot(byte iot) {
        this.iot = iot;
    }

    /**
     * 获取进出区域id
     */
    public long getIid() {
        return iid;
    }

    /**
     * 设置进出区域id
     */
    public void setIid(long iid) {
        this.iid = iid;
    }

    /**
     * 获取进出区域方向
     */
    public byte getIof() {
        return iof;
    }

    /**
     * 设置进出区域方向
     */
    public void setIof(byte iof) {
        this.iof = iof;
    }

    /**
     * 获取行驶路段id
     */
    public long getRid() {
        return rid;
    }

    /**
     * 设置行驶路段id
     */
    public void setRid(long rid) {
        this.rid = rid;
    }

    /**
     * 获取行驶路段时间
     */
    public int getRt() {
        return rt;
    }

    /**
     * 设置行驶路段时间
     */
    public void setRt(int rt) {
        this.rt = rt;
    }

    /**
     * 获取行驶路段结果
     */
    public byte getRf() {
        return rf;
    }

    /**
     * 设置行驶路段结果
     */
    public void setRf(byte rf) {
        this.rf = rf;
    }

    /**
     * 获取人工确认报警id
     */
    public int getAid() {
        return aid;
    }

    /**
     * 设置人工确认报警id
     */
    public void setAid(int aid) {
        this.aid = aid;
    }

    /**
     * 获取扩展车辆信号状态
     */
    public long getExs() {
        return exs;
    }

    /**
     * 设置扩展车辆信号状态
     */
    public void setExs(long exs) {
        this.exs = exs;
    }

    /**
     * 获取IO状态位
     */
    public int getIos() {
        return ios;
    }

    /**
     * 设置IO状态位
     */
    public void setIos(int ios) {
        this.ios = ios;
    }

    /**
     * 获取模拟量0
     */
    public int getAd0() {
        return ad0;
    }

    /**
     * 设置模拟量0
     */
    public void setAd0(int ad0) {
        this.ad0 = ad0;
    }

    /**
     * 获取模拟量1
     */
    public int getAd1() {
        return ad1;
    }

    /**
     * 设置模拟量1
     */
    public void setAd1(int ad1) {
        this.ad1 = ad1;
    }

    /**
     * 获取无线通信网络信号强度
     */
    public short getNet() {
        return net;
    }

    /**
     * 设置无线通信网络信号强度
     */
    public void setNet(short net) {
        this.net = net;
    }

    /**
     * 获取定位卫星数
     */
    public short getSat() {
        return sat;
    }

    /**
     * 设置定位卫星数
     */
    public void setSat(short sat) {
        this.sat = sat;
    }

}
