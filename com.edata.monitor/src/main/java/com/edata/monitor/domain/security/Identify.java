package com.edata.monitor.domain.security;

/**
 * 身份认证
 * 
 * @author 生
 *
 */
public class Identify {
	private String id;

	/**
	 * 获取用户id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置用户id
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String companyId;

	/**
	 * 获取公司id
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置公司id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private String unid;

	/**
	 * 获取用户唯一id
	 */
	public String getUnid() {
		return unid;
	}

	/**
	 * 设置用户唯一id
	 */
	public void setUnid(String unid) {
		this.unid = unid;
	}

	private String account;

	/**
	 * 获取帐号
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 设置帐号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	private String name;

	/**
	 * 获取名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	private int kind;

	/**
	 * 获取用户类型
	 */
	public int getKind() {
		return kind;
	}

	/**
	 * 设置用户类型
	 */
	public void setKind(int kind) {
		this.kind = kind;
	}

	private String[] roles;

	/**
	 * 获取角色列表
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * 设置角色列表
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
