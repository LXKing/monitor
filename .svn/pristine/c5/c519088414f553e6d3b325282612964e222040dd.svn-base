package com.rayton.gps.dao.feature;

import java.util.List;

/**
 * 设备功能信息接口
 *
 * @author 生
 */
public interface IFeatureDao {
    /**
     * 创建设备功能信息
     *
     * @throws Exception
     */
    void create(FeatureDto dto) throws Exception;

    /**
     * 读取设备功能信息
     *
     * @throws Exception
     */
    FeatureDto fetch(String id) throws Exception;

    /**
     * 更新设备功能信息
     *
     * @throws Exception
     */
    int update(FeatureDto dto) throws Exception;

    /**
     * 删除参数
     */
    void delteParameters(String id);

    /**
     * 删除设备功能信息
     *
     * @throws Exception
     */
    void delete(String id) throws Exception;

    /**
     * 查询设备功能列表
     *
     * @param kind 协议类型
     * @throws Exception
     */
    List<FeatureDto> list(int kind) throws Exception;

    /**
     * 匹配设备信令
     *
     * @param kind 协议类型
     * @throws Exception
     */
    List<FeatureDto> match(int kind) throws Exception;
}
