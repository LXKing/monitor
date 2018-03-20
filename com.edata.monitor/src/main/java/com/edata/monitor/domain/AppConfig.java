package com.edata.monitor.domain;

/**
 * 网站配置
 * 
 * @author 生
 *
 */
public class AppConfig {
	private String godpPortal;

	/**
	 * 获取GOP地址
	 */
	public String getGodpPortal() {
		return godpPortal;
	}

	/**
	 * 设置GOP地址
	 */
	public void setGodpPortal(String godpPortal) {
		this.godpPortal = godpPortal;
	}

	private String godpUser;

	/**
	 * 获取GOP用户帐号
	 */
	public String getGodpUser() {
		return godpUser;
	}

	/**
	 * 设置GOP用户帐号
	 */
	public void setGodpUser(String godpUser) {
		this.godpUser = godpUser;
	}

	private String godpPassword;

	/**
	 * 获取GOP用户密码
	 */
	public String getGodpPassword() {
		return godpPassword;
	}

	/**
	 * 设置GOP用户密码
	 */
	public void setGodpPassword(String godpPassword) {
		this.godpPassword = godpPassword;
	}

	private int operateLogCacheRows = 1;

	/**
	 * 获取操作日志缓存行数
	 */
	public int getOperateLogCacheRows() {
		return operateLogCacheRows;
	}

	/**
	 * 设置操作日志缓存行数
	 */
	public void setOperateLogCacheRows(int operateLogCacheRows) {
		this.operateLogCacheRows = operateLogCacheRows;
	}

	private String topCompanyId;

	/**
	 * 获取顶级企业ID
	 */
	public String getTopCompanyId() {
		return topCompanyId;
	}

	/**
	 * 设置顶级企业ID
	 */
	public void setTopCompanyId(String topCompanyId) {
		this.topCompanyId = topCompanyId;
	}
}
