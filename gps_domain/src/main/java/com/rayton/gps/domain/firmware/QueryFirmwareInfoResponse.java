package com.rayton.gps.domain.firmware;

public class QueryFirmwareInfoResponse {
    private int size;
    private short pages;
    private short checkCode;
    private String version;

    /**
     * 获取文件长度
     *
     * @return 文件长度
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置文件长度
     *
     * @param size 文件长度
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获取总包数
     *
     * @return 总包数
     */
    public short getPages() {
        return pages;
    }

    /**
     * 设置总包数
     *
     * @param pages 总包数
     */
    public void setPages(short pages) {
        this.pages = pages;
    }

    /**
     * 获取文件校验码
     *
     * @return 文件校验码
     */
    public short getCheckCode() {
        return checkCode;
    }

    /**
     * 设置文件校验码
     *
     * @param checkCode 文件校验码
     */
    public void setCheckCode(short checkCode) {
        this.checkCode = checkCode;
    }

    /**
     * 获取固件版本号
     *
     * @return 固件版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置固件版本号
     *
     * @param version 固件版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
