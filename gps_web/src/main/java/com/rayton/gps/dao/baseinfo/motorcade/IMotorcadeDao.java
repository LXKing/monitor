package com.rayton.gps.dao.baseinfo.motorcade;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车队服务接口
 *
 * @author 生
 */

@Repository
public interface IMotorcadeDao {
    /**
     * 读取车队列表
     */
    List<Motorcade> list(String companyId);

    /**
     * 创建新的车队
     */
    void create(Motorcade dto);

    /**
     * 修改车队
     */
    int update(Motorcade dto);

    /**
     * 删除车队
     */
    void delete(String id);

    /**
     * 读取车队
     */
    Motorcade fetch(String id);

    /**
     * 是否已存在车队
     */
    boolean exist(String name, String companyId);

    /**
     * 是否已存在车队
     */
    boolean existOutId(String name, String companyId, String id);

    /**
     * 是否有车辆
     */
    boolean hasVehicle(String id);


}
