package com.edata.godp.domain.device;

import javax.validation.constraints.Size;

/**
 * 设备绑定用户请求
 */
public class DeviceBindUserRequest {
    @Size(min = 5, max = 20)
    private String number;
    private boolean bind;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isBind() {
        return bind;
    }

    public void setBind(boolean bind) {
        this.bind = bind;
    }
}
