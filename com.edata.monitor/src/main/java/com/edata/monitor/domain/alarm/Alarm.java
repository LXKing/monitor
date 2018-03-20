package com.edata.monitor.domain.alarm;

import java.sql.Timestamp;
import java.util.Date;

public class Alarm {
	private String id;

	/**
	 * 获取id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String dn;

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

	private String na;

	/**
	 * 获取车牌号
	 */
	public String getNa() {
		return na;
	}

	/**
	 * 设置车牌号
	 */
	public void setNa(String na) {
		this.na = na;
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

	private Date st;

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

	/**
	 * 有否定位否
	 */
	private byte val;

	public byte getVal() {
		return val;
	}

	public void setVal(byte val) {
		this.val = val;
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

	/**
	 * 海拔
	 */
	public int alt;

	public int getAlt() {
		return alt;
	}

	public void setAlt(int alt) {
		this.alt = alt;
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

	private double m;

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

	private float oil;

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

	private float vss;

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

	private byte ovt;

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

	private long oid;

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

	private byte iot;

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

	private long iid;

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

	private byte iof;

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

	private long rid;

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

	private int rt;

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

	private byte rf;

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
	private int aid;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	/**
	 * 扩展车辆信号状态位
	 */
	private long exs;

	public long getExs() {
		return exs;
	}

	public void setExs(long exs) {
		this.exs = exs;
	}

	/**
	 * IO状态位
	 */
	private int ios;

	public int getIos() {
		return ios;
	}

	public void setIos(int ios) {
		this.ios = ios;
	}

	/**
	 * 模拟量AD0
	 */
	private int ad0;

	public int getAd0() {
		return ad0;
	}

	public void setAd0(int ad0) {
		this.ad0 = ad0;
	}

	/**
	 * 模拟量AD1
	 */
	private int ad1;

	public int getAd1() {
		return ad1;
	}

	public void setAd1(int ad1) {
		this.ad1 = ad1;
	}

	/**
	 * 网络强度
	 */
	private short net;

	public short getNet() {
		return net;
	}

	public void setNet(short net) {
		this.net = net;
	}

	/**
	 * 卫星数量
	 */
	private short sat;

	/**
	 * 获取卫星数量
	 */
	public short getSat() {
		return sat;
	}

	public void setSat(short sat) {
		this.sat = sat;
	}

	private byte from;

	public byte getFrom() {
		return from;
	}

	public void setFrom(byte from) {
		this.from = from;
	}

	private String userName;

	/**
	 * 获取用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	private Date userTime;

	/**
	 * 获取处理时间
	 */
	public Date getUserTime() {
		return userTime;
	}

	/**
	 * 设置处理时间
	 */
	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}

	private int userconfirm;

	/**
	 * 获取用户确认警类型
	 */
	public int getUserconfirm() {
		return userconfirm;
	}

	/**
	 * 设置用户确认警类型
	 */
	public void setUserconfirm(int userconfirm) {
		this.userconfirm = userconfirm;
	}

	private String userMethod;

	/**
	 * 获取处理方式
	 */
	public String getUserMethod() {
		return userMethod;
	}

	/**
	 * 设置处理方式
	 */
	public void setUserMethod(String userMethod) {
		this.userMethod = userMethod;
	}

	private String userRemark;

	/**
	 * 获取处理内容
	 */
	public String getUserRemark() {
		return userRemark;
	}

	/**
	 * 设置处理内容
	 */
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	private Timestamp editTime;

	/**
	 * 获取时间戳
	 */
	public Timestamp getEditTime() {
		return editTime;
	}

	/**
	 * 设置时间戳
	 */
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
}
