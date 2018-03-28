package com.rayton.gps.dao.device;

import java.util.Date;
import java.util.List;

/**
 * 设备信息接口
 *
 * @author 生
 */
public interface IDeviceDao {
    /**
     * 创建新的设备信息
     *
     * @param number       设备号
     * @param protocolKind 协议类型
     * @return 设备信息
     */
    void create(String number, int protocolKind);

    /**
     * 读取所有设备信息
     *
     * @return 设备信息列表
     */
    List<DeviceDto> reload();

    /**
     * 设备升级完成报告
     *
     * @param id      设备id
     * @param version 版本号
     */
    void upgradeCompletedReport(String number, String version);

    /**
     * 休眠状态设置
     *
     * @param id    设备id
     * @param start true开始，false结束
     */
    void setSleepStatus(String number, boolean start);

    /**
     * 全车匹配结果报告
     *
     * @param id     设备id
     * @param result 结果
     */
    void vehicleSystemMatchReport(String number, String result);

    /**
     * 设置维修模式
     *
     * @param id    设备id
     * @param start true开始，false结束
     */
    void setRepairMode(String number, boolean start);

    /**
     * 设置调试模式
     *
     * @param id      设备id
     * @param enabled true开启，false禁止
     */
    void setDebuggingMode(String number, boolean enabled);

    /**
     * 读取设备数据日志记录总行数
     */
    int loadLogPageCount(String number, Date start, Date end);

    /**
     * 读取设备数据日志记录页内容
     *
     * @param number    设备号
     * @param start     开始时间
     * @param end       结束时间
     * @param pageIndex 页序号
     * @param pageSize  页大小
     */
    List<DataLogDto> loadLogPageDetail(String number, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    /**
     * 设备绑定用户
     */
    void bindUser(String userId, String number) throws Exception;

    /**
     * 设备解除用户
     */
    void unbindUser(String userId, String number) throws Exception;

    /**
     * 获取设备与用户关系
     *
     * @throws Exception
     */
    List<DeviceInUserDto> getDeviceInUsers() throws Exception;

    /**
     * 注册设备
     *
     * @throws Exception
     */
    void regist(DeviceRegistrationDto dto);
}