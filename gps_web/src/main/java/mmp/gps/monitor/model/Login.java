package mmp.gps.monitor.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Login {

    @Size(min = 3, max = 50, message = "用户名错误！")
    private String account;

    private String pwd;
    @NotNull
    private String verify;
    private String error;

    @Override
    public String toString() {
        return "Login{" + "account='" + account + '\'' + ", pwd='" + pwd + '\'' + ", verify='" + verify + '\'' + ", " + "error='" + error + '\'' + '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
