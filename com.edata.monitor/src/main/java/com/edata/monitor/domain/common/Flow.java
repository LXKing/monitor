package com.edata.monitor.domain.common;

import java.util.Date;

public class Flow {
	private String number;

	/**
	 * 获取设备号
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * 设置设备号
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	private Date time;

	/**
	 * 获取时间
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 设置时间
	 */
	public void setTime(Date time) {
		this.time = time;
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

	private int mileage;

	/**
	 * 获取里程
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * 设置里程
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	private float bv;

	/**
	 * 获取电压
	 */
	public float getBv() {
		return bv;
	}

	/**
	 * 设置电压
	 */
	public void setBv(float bv) {
		this.bv = bv;
	}

	private int rpm;

	/**
	 * 获取转速
	 */
	public int getRpm() {
		return rpm;
	}

	/**
	 * 设置转速
	 */
	public void setRpm(int rpm) {
		this.rpm = rpm;
	}

	private byte ect;

	/**
	 * 获取发动机冷却液温度
	 */
	public byte getEct() {
		return ect;
	}

	/**
	 * 设置发动机冷却液温度
	 */
	public void setEct(byte ect) {
		this.ect = ect;
	}

	private int runtm;

	/**
	 * 获取发动机运行时间
	 */
	public int getRuntm() {
		return runtm;
	}

	/**
	 * 设置发动机运行时间
	 */
	public void setRuntm(int runtm) {
		this.runtm = runtm;
	}

	private byte eot;

	/**
	 * 获取发动机机油温度
	 */
	public byte getEot() {
		return eot;
	}

	/**
	 * 设置发动机机油温度
	 */
	public void setEot(byte eot) {
		this.eot = eot;
	}

	private float ifc;

	/**
	 * 获取瞬时油耗
	 */
	public float getIfc() {
		return ifc;
	}

	/**
	 * 设置瞬时油耗
	 */
	public void setIfc(float ifc) {
		this.ifc = ifc;
	}

	private float ot;

	/**
	 * 获取油箱油量
	 */
	public float getOt() {
		return ot;
	}

	/**
	 * 设置油箱油量
	 */
	public void setOt(float ot) {
		this.ot = ot;
	}

	private byte iat;

	/**
	 * 获取进气温度
	 */
	public byte getIat() {
		return iat;
	}

	/**
	 * 设置进气温度
	 */
	public void setIat(byte iat) {
		this.iat = iat;
	}

	private byte et;

	/**
	 * 获取进气温度
	 */
	public byte getEt() {
		return et;
	}

	/**
	 * 设置进气温度
	 */
	public void setEt(byte et) {
		this.et = et;
	}

	private int maf;

	/**
	 * 获取空气流量
	 */
	public int getMaf() {
		return maf;
	}

	/**
	 * 设置空气流量
	 */
	public void setMaf(int maf) {
		this.maf = maf;
	}

	private short ap;

	/**
	 * 获取大气压力
	 */
	public short getAp() {
		return ap;
	}

	/**
	 * 设置大气压力
	 */
	public void setAp(short ap) {
		this.ap = ap;
	}

	private float loadpct;

	/**
	 * 获取计算负荷
	 */
	public float getLoadpct() {
		return loadpct;
	}

	/**
	 * 设置计算负荷
	 */
	public void setLoadpct(float loadpct) {
		this.loadpct = loadpct;
	}

	private short map;

	/**
	 * 获取进气歧管绝对压力
	 */
	public short getMap() {
		return map;
	}

	/**
	 * 设置进气歧管绝对压力
	 */
	public void setMap(short map) {
		this.map = map;
	}

	private short lftp;

	/**
	 * 获取左前轮胎压力
	 */
	public short getLftp() {
		return lftp;
	}

	/**
	 * 设置左前轮胎压力
	 */
	public void setLftp(short lftp) {
		this.lftp = lftp;
	}

	private short altp;

	/**
	 * 获取左后轮胎压力
	 */
	public short getAltp() {
		return altp;
	}

	/**
	 * 设置左后轮胎压力
	 */
	public void setAltp(short altp) {
		this.altp = altp;
	}

	private short rrtp;

	/**
	 * 获取右后轮胎压力
	 */
	public short getRrtp() {
		return rrtp;
	}

	/**
	 * 设置右后轮胎压力
	 */
	public void setRrtp(short rrtp) {
		this.rrtp = rrtp;
	}

	private short rftp;

	/**
	 * 获取右前轮胎压力
	 */
	public short getRftp() {
		return rftp;
	}

	/**
	 * 设置右前轮胎压力
	 */
	public void setRftp(short rftp) {
		this.rftp = rftp;
	}

	private long mord;

	/**
	 * 获取亮灯里程
	 */
	public long getMord() {
		return mord;
	}

	/**
	 * 设置亮灯里程
	 */
	public void setMord(long mord) {
		this.mord = mord;
	}

	private float iaa;

	/**
	 * 获取点火提前角
	 */
	public float getIaa() {
		return iaa;
	}

	/**
	 * 设置点火提前角
	 */
	public void setIaa(float iaa) {
		this.iaa = iaa;
	}

	private float longftb1;

	/**
	 * 获取长期燃油修正B1
	 */
	public float getLongftb1() {
		return longftb1;
	}

	/**
	 * 设置长期燃油修正B1
	 */
	public void setLongftb1(float longftb1) {
		this.longftb1 = longftb1;
	}

	private float shrtftb1;

	/**
	 * 获取短期燃油修正B1
	 */
	public float getShrtftb1() {
		return shrtftb1;
	}

	/**
	 * 设置短期燃油修正B1
	 */
	public void setShrtftb1(float shrtftb1) {
		this.shrtftb1 = shrtftb1;
	}

	private float shrtftb1s1;

	/**
	 * 获取短期燃油修正B1S1
	 */
	public float getShrtftb1s1() {
		return shrtftb1s1;
	}

	/**
	 * 设置短期燃油修正B1S1
	 */
	public void setShrtftb1s1(float shrtftb1s1) {
		this.shrtftb1s1 = shrtftb1s1;
	}

	private float shrtftb1s2;

	/**
	 * 获取短期燃油修正B1S2
	 */
	public float getShrtftb1s2() {
		return shrtftb1s2;
	}

	/**
	 * 设置短期燃油修正B1S2
	 */
	public void setShrtftb1s2(float shrtftb1s2) {
		this.shrtftb1s2 = shrtftb1s2;
	}

	private byte faults;

	/**
	 * 获取故障数量
	 */
	public byte getFaults() {
		return faults;
	}

	/**
	 * 设置故障数量
	 */
	public void setFaults(byte faults) {
		this.faults = faults;
	}

	private float tp;

	/**
	 * 获取节气门开度
	 */
	public float getTp() {
		return tp;
	}

	/**
	 * 设置节气门开度
	 */
	public void setTp(float tp) {
		this.tp = tp;
	}

	private float tpalb;

	/**
	 * 获取节气门绝对位置B
	 */
	public float getTpalb() {
		return tpalb;
	}

	/**
	 * 设置节气门绝对位置B
	 */
	public void setTpalb(float tpalb) {
		this.tpalb = tpalb;
	}

	private float tpalc;

	/**
	 * 获取节气门绝对位置C
	 */
	public float getTpalc() {
		return tpalc;
	}

	/**
	 * 设置节气门绝对位置C
	 */
	public void setTpalc(float tpalc) {
		this.tpalc = tpalc;
	}

	private short frp;

	/**
	 * 获取燃油压力
	 */
	public short getFrp() {
		return frp;
	}

	/**
	 * 设置燃油压力
	 */
	public void setFrp(short frp) {
		this.frp = frp;
	}

	private String fuelsys1;

	/**
	 * 获取燃油状态1
	 */
	public String getFuelsys1() {
		return fuelsys1;
	}

	/**
	 * 设置燃油状态1
	 */
	public void setFuelsys1(String fuelsys1) {
		this.fuelsys1 = fuelsys1;
	}

	private String fuelsys2;

	/**
	 * 获取燃油状态2
	 */
	public String getFuelsys2() {
		return fuelsys2;
	}

	/**
	 * 设置燃油状态2
	 */
	public void setFuelsys2(String fuelsys2) {
		this.fuelsys2 = fuelsys2;
	}

	private float o2sb1s1;

	/**
	 * 获取氧传感器电压B1S1
	 */
	public float getO2sb1s1() {
		return o2sb1s1;
	}

	/**
	 * 设置氧传感器电压B1S1
	 */
	public void setO2sb1s1(float o2sb1s1) {
		this.o2sb1s1 = o2sb1s1;
	}

	private float o2sb1s2;

	/**
	 * 获取氧传感器电压B1S2
	 */
	public float getO2sb1s2() {
		return o2sb1s2;
	}

	/**
	 * 设置氧传感器电压B1S2
	 */
	public void setO2sb1s2(float o2sb1s2) {
		this.o2sb1s2 = o2sb1s2;
	}

	private float ppsd;

	/**
	 * 获取油门踏板位置D
	 */
	public float getPpsd() {
		return ppsd;
	}

	/**
	 * 设置油门踏板位置D
	 */
	public void setPpsd(float ppsd) {
		this.ppsd = ppsd;
	}

	private float ppse;

	/**
	 * 获取油门踏板位置E
	 */
	public float getPpse() {
		return ppse;
	}

	/**
	 * 设置油门踏板位置E
	 */
	public void setPpse(float ppse) {
		this.ppse = ppse;
	}

	private byte vaptb1s1;

	/**
	 * 获取蒸发汽温度B1S1
	 */
	public byte getVaptb1s1() {
		return vaptb1s1;
	}

	/**
	 * 设置蒸发汽温度B1S1
	 */
	public void setVaptb1s1(byte vaptb1s1) {
		this.vaptb1s1 = vaptb1s1;
	}

	private byte vaptb1s2;

	/**
	 * 获取蒸发汽温度B1S2
	 */
	public byte getVaptb1s2() {
		return vaptb1s2;
	}

	/**
	 * 设置蒸发汽温度B1S2
	 */
	public void setVaptb1s2(byte vaptb1s2) {
		this.vaptb1s2 = vaptb1s2;
	}

}
