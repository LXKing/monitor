package com.edata.godp.dao.firmware;

/**
 * 固件信息传输类
 *
 * @author 生
 */
public class FirmwareInfoDto {
    /**
     * 文件长度
     */
    public int fileLength;
    /**
     * 文件校验码
     */
    public short fileXor;
    /**
     * 固件版本号
     */
    public String version;
}
