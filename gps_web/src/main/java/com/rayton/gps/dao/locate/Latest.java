package com.rayton.gps.dao.locate;

import java.util.Date;

/**
 * 最新位置数据
 *
 * @author 生
 */
public class Latest {

    /**
     * 海拔
     */
    public int alt;
    private String dn;
    private String na;
    private Date gt;
    private Date st;
    /**
     * 有否定位否
     */
    private byte val;
    private double lng;
    private double lat;
    private float sp;
    private int d;
    private long a;
    private long s;
    private int o;
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
    /**
     * 人工确认报警id
     */
    private int aid;
    /**
     * 扩展车辆信号状态位
     */
    private long exs;
    /**
     * IO状态位
     */
    private int ios;
    /**
     * 模拟量AD0
     */
    private int ad0;
    /**
     * 模拟量AD1
     */
    private int ad1;
    /**
     * 网络强度
     */
    private short net;
    /**
     * 卫星数量
     */
    private short sat;

    @Override
    public String toString() {
        return "Latest{" + "dn='" + dn + '\'' + ", na='" + na + '\'' + ", gt=" + gt + ", st=" + st + ", val=" + val +
                ", lng=" + lng + ", lat=" + lat + ", alt=" + alt + ", sp=" + sp + ", d=" + d + ", a=" + a + ", s=" +
                s + ", o=" + o + ", m=" + m + ", oil=" + oil + ", vss=" + vss + ", ovt=" + ovt + ", oid=" + oid + ", " +
                "" + "" + "" + "iot=" + iot + ", iid=" + iid + ", iof=" + iof + ", rid=" + rid + ", rt=" + rt + ", " +
                "rf=" + rf + ", " + "aid=" + aid + ", exs=" + exs + ", ios=" + ios + ", ad0=" + ad0 + ", ad1=" + ad1
                + ", " + "net=" + net + "," + " sat=" + sat + '}';
    }

    /**
     * 获取设备号
     */
    public String getDn() {
        return dn;
    }

    /**
     * 设置设备号
     */
    public void setDn(String dn) {
        this.dn = dn;
    }

    /**
     * 获取名称
     */
    public String getNa() {
        return na;
    }

    /**
     * 设置名称
     */
    public void setNa(String na) {
        this.na = na;
    }

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

    /**
     * 获取数据接收时间
     */
    public Date getSt() {
        return st;
    }

    /**
     * 设置数据接收时间
     */
    public void setSt(Date st) {
        this.st = st;
    }

    public byte getVal() {
        return val;
    }

    public void setVal(byte val) {
        this.val = val;
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

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
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
     * 获取在线否，0：离线，1：在线
     */
    public int getO() {
        return o;
    }

    /**
     * 设置在线否，0：离线，1：在线
     */
    public void setO(int o) {
        this.o = o;
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
     * 人工确认报警id
     */
    public int getAid() {
        return aid;
    }

    /**
     * 人工确认报警id
     */
    public void setAid(int aid) {
        this.aid = aid;
    }

    /**
     * 扩展车辆信号状态位
     */
    public long getExs() {
        return exs;
    }

    /**
     * 扩展车辆信号状态位
     */
    public void setExs(long exs) {
        this.exs = exs;
    }

    /**
     * IO状态位
     */
    public int getIos() {
        return ios;
    }

    /**
     * IO状态位
     */
    public void setIos(int ios) {
        this.ios = ios;
    }

    /**
     * 模拟量AD0
     */
    public int getAd0() {
        return ad0;
    }

    /**
     * 模拟量AD0
     */
    public void setAd0(int ad0) {
        this.ad0 = ad0;
    }

    /**
     * 模拟量AD1
     */
    public int getAd1() {
        return ad1;
    }

    /**
     * 模拟量AD1
     */
    public void setAd1(int ad1) {
        this.ad1 = ad1;
    }

    /**
     * 网络强度
     */
    public short getNet() {
        return net;
    }

    /**
     * 网络强度
     */
    public void setNet(short net) {
        this.net = net;
    }

    /**
     * 卫星数量
     */
    public short getSat() {
        return sat;
    }

    /**
     * 卫星数量
     */
    public void setSat(short sat) {
        this.sat = sat;
    }
}
