package com.edata.monitor.domain.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * sim卡类
 * 
 * @author yangzs
 *
 */
public class Simcard {
	private String id;

	/**
	 * 获取唯一编号
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置唯一编号
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String companyId;

	/**
	 * 获取公司唯一编号
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置公司唯一编号
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private String phoneNumber;

	/**
	 * 获取电话号码
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 设置电话号码
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private String simcardNumber;

	/**
	 * 获取sim卡号
	 */
	public String getSimcardNumber() {
		return simcardNumber;
	}

	/**
	 * 设置sim卡号
	 */
	public void setSimcardNumber(String simcardNumber) {
		this.simcardNumber = simcardNumber;
	}

	private String speechType;

	/**
	 * 获取语音类型
	 */
	public String getSpeechType() {
		return speechType;
	}

	/**
	 * 设置语音类型
	 */
	public void setSpeechType(String speechType) {
		this.speechType = speechType;
	}

	private boolean openSMS;

	/**
	 * 获取是否开通短信
	 */
	public boolean isOpenSMS() {
		return openSMS;
	}

	/**
	 * 设置是否开通短信
	 */
	public void setOpenSMS(boolean openSMS) {
		this.openSMS = openSMS;
	}

	private Date openDate;

	/**
	 * 获取开通日期
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * 设置开通日期
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	private Date purchaseDate;

	/**
	 * 获取购买日期
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * 设置购买日期
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	private String carrierOperator;

	/**
	 * 获取运营商
	 */
	public String getCarrierOperator() {
		return carrierOperator;
	}

	/**
	 * 设置运营商
	 */
	public void setCarrierOperator(String carrierOperator) {
		this.carrierOperator = carrierOperator;
	}

	private double prepayment;

	/**
	 * 获取预交费
	 */
	public double getPrepayment() {
		return prepayment;
	}

	/**
	 * 设置预交费
	 */
	public void setPrepayment(double prepayment) {
		this.prepayment = prepayment;
	}

	private Date expireDate;

	/**
	 * 获取到期日期
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * 设置到期日期
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	private String flowset;

	/**
	 * 获取流量套餐
	 */
	public String getFlowset() {
		return flowset;
	}

	/**
	 * 设置流量套餐
	 */
	public void setFlowset(String flowset) {
		this.flowset = flowset;
	}

	private String remark;

	/**
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
