package com.edata.monitor.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Login {
	@Size(min = 3, max = 50, message = "用户名错误！")
	private String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Size(min = 6, max = 50, message = "密码错误！")
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@NotNull
	private String verify;

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
