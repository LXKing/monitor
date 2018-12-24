package mmp.gps.domain.locate;

import java.util.Date;

/**
 * 最后位置信息
 */
public class LatestDto {
    public byte val;


    /**
     * 设备号
     */
    public String dn;
    /**
     * 定位时间
     */
    public Date gt;

    public byte o;
    /**
     * 服务器时间
     */
    public Date st;
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
    public float sp;
    /**
     * 方向
     */
    public int d;
    /**
     * 报警
     */
    public long a;
    /**
     * 状态
     */
    public long s;
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
    /**
     * 人工确认报警id
     */
    public int aid;

    /**
     * 扩展车辆信号状态位
     */
    public long exs;

    /**
     * IO状态位
     */
    public int ios;

    /**
     * 模拟量AD0
     */
    public int ad0;

    /**
     * 模拟量AD1
     */
    public int ad1;

    /**
     * 网络强度
     */
    public short net;

    /**
     * 卫星数量
     */
    public short sat;

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public Date getGt() {
        return gt;
    }

    public void setGt(Date gt) {
        this.gt = gt;
    }

    public Date getSt() {
        return st;
    }

    public void setSt(Date st) {
        this.st = st;
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

    public float getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getS() {
        return s;
    }

    public void setS(long s) {
        this.s = s;
    }

    public double getM() {
        return m;
    }

    public void setM(long m) {
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

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public long getExs() {
        return exs;
    }

    public void setExs(long exs) {
        this.exs = exs;
    }

    public int getIos() {
        return ios;
    }

    public void setIos(int ios) {
        this.ios = ios;
    }

    public int getAd0() {
        return ad0;
    }

    public void setAd0(int ad0) {
        this.ad0 = ad0;
    }

    public int getAd1() {
        return ad1;
    }

    public void setAd1(int ad1) {
        this.ad1 = ad1;
    }

    public short getNet() {
        return net;
    }

    public void setNet(short net) {
        this.net = net;
    }

    public short getSat() {
        return sat;
    }

    public void setSat(short sat) {
        this.sat = sat;
    }
}
