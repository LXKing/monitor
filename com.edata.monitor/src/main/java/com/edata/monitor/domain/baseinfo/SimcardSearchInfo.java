package com.edata.monitor.domain.baseinfo;

public class SimcardSearchInfo {
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
