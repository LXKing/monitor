package com.edata.monitor.domain.baseinfo;

import java.sql.Date;

/**
 * sim卡信息类
 * 
 * @author yangzs
 *
 */
public class SimcardInfo {
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

	private Date createTime;

	/**
	 * 获取创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
}
