package com.edata.godp.domain.feature;

/**
 * 设备功能信息
 *
 * @author 生
 */
public class FeatureInfo {
    private String id;
    private int index;
    private String name;
    private String command;
    private boolean enabled;
    private int params;
    private String description;

    /**
     * 获取记录ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录ID
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
     * 获取命令
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
     * 获取参数个数
     */
    public int getParams() {
        return params;
    }

    /**
     * 设置参数个数
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

}
