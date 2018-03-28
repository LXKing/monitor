package com.rayton.gps.domain.firmware;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateFirmwareRequest {
    @Range(min = 0, max = 254)
    private short factoryId;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @Size(min = 1, max = 10)
    private String version;
    private String description;

    /**
     * 获取车厂id
     */
    public short getFactoryId() {
        return factoryId;
    }

    /**
     * 设置车厂id
     */
    public void setFactoryId(short factoryId) {
        this.factoryId = factoryId;
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
     * 获取版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本号
     */
    public void setVersion(String version) {
        this.version = version;
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
