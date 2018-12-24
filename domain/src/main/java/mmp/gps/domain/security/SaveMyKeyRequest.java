package mmp.gps.domain.security;

import javax.validation.constraints.Size;

public class SaveMyKeyRequest {
    @Size(min = 6, max = 50, message = "原密码错误！")
    private String oldKey;
    @Size(min = 6, max = 50, message = "新密码错误！")
    private String newKey;
    @Size(min = 6, max = 50, message = "确认密码错误！")
    private String confirmKey;

    public String getOldKey() {
        return oldKey;
    }

    public void setOldKey(String oldKey) {
        this.oldKey = oldKey;
    }

    public String getNewKey() {
        return newKey;
    }

    public void setNewKey(String newKey) {
        this.newKey = newKey;
    }

    public String getConfirmKey() {
        return confirmKey;
    }

    public void setConfirmKey(String confirmKey) {
        this.confirmKey = confirmKey;
    }
}
