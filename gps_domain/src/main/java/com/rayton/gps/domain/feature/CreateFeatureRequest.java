package com.rayton.gps.domain.feature;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateFeatureRequest {
    @NotNull
    @Range(min = 1, max = 9999)
    private int index;
    @NotNull
    @Min(1)
    private int kind;
    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    @NotNull
    @Size(min = 1, max = 20)
    private String command;
    private boolean enabled;
    private boolean twiceConfirm;
    private boolean passwordConfirm;
    private String description;

    /**
     * 获取序号
     */
    public int getIndex() {
        return index;
    }

    /**
     * 设置序号
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 获取协议类型
     */
    public int getKind() {
        return kind;
    }

    /**
     * 设置协议类型
     */
    public void setKind(int kind) {
        this.kind = kind;
    }

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

    /**
     * 命令
     */
    public String getCommand() {
        return command;
    }

    /**
     * 设置命令
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * 获取启用否
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 设置启用否
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取两次确认否
     */
    public boolean isTwiceConfirm() {
        return twiceConfirm;
    }

    /**
     * 设置两次确认否
     */
    public void setTwiceConfirm(boolean twiceConfirm) {
        this.twiceConfirm = twiceConfirm;
    }

    /**
     * 获取密码确认否
     */
    public boolean isPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * 设置密码确认否
     */
    public void setPasswordConfirm(boolean passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * 获取描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
