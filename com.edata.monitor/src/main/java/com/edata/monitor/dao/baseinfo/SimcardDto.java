package com.edata.monitor.dao.baseinfo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Sim卡数据传输类
 * 
 * @author yangzs
 *
 */
public class SimcardDto {
	/**
	 * 唯一编号
	 */
	public String id;

	/**
	 * 公司唯一编号
	 */
	public String companyId;
	/**
	 * 电话号码
	 */
	public String phoneNumber;

	/**
	 * sim卡号
	 */
	public String simcardNumber;

	/**
	 * 语音类型
	 */
	public String speechType;

	/**
	 * 是否开通短信
	 */

	public boolean openSMS;

	/**
	 * 开通日期
	 */
	public Date openDate;

	/**
	 * 购买日期
	 */
	public Date purchaseDate;
	/**
	 * 运营商
	 */
	public String carrierOperator;

	/**
	 * 预交费
	 */
	public double prepayment;

	/**
	 * 到期日期
	 */
	public Date expireDate;

	/**
	 * 流量套餐
	 */
	public String flowset;
	/**
	 * 创建时间
	 */
	public Date createTime;
	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 时间戳
	 */
	public Timestamp editTime;
}
