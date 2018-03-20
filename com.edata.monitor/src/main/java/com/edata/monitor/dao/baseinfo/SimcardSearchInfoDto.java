package com.edata.monitor.dao.baseinfo;

/**
 * Sim卡数据传输类
 * 
 * @author yangzs
 *
 */
public class SimcardSearchInfoDto {
	/**
	 * 唯一编号
	 */
	public String id;

	/**
	 * 电话号码
	 */
	public String phoneNumber;
	/**
	 * 语音类型
	 */
	public String speechType;

	/**
	 * 是否开通短信
	 */
	public boolean openSMS;
	/**
	 * 运营商
	 */
	public String carrierOperator;
	/**
	 * 备注
	 */
	public String remark;
}
