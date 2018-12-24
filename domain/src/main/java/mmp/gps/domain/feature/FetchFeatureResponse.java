package mmp.gps.domain.feature;

import java.sql.Timestamp;

public class FetchFeatureResponse {
    private String id;
    private int index;
    private int kind;
    private String name;
    private String command;
    private boolean enabled;
    private boolean twiceConfirm;
    private boolean passwordConfirm;
    private int params;
    private String description;
    private Timestamp editTime;

    /**
     * 获取记录号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录号
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * 获取参数总数
     */
    public int getParams() {
        return params;
    }

    /**
     * 设置参数总数
     */
    public void setParams(int params) {
        this.params = params;
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

    /**
     * 获取时间戳
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 设置时间戳
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }
}
