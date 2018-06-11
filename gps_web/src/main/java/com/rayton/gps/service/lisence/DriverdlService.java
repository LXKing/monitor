package com.rayton.gps.service.lisence;

import com.rayton.gps.dao.lisence.Driverdl;
import com.rayton.gps.util.Assist;

import java.util.List;

public interface DriverdlService {
    /**
     * 获得Driverdl数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
     *
     * @param assist
     * @return
     */
    long getDriverdlRowCount(Assist assist);

    /**
     * 获得Driverdl数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
     *
     * @param assist
     * @return
     */
    List<Driverdl> selectDriverdl(Assist assist);

    /**
     * 获得一个Driverdl对象,以参数Driverdl对象中不为空的属性作为条件进行查询
     *
     * @param obj
     * @return
     */
    Driverdl selectDriverdlByObj(Driverdl obj);

    /**
     * 通过Driverdl的id获得Driverdl对象
     *
     * @param id
     * @return
     */
    Driverdl selectDriverdlById(Integer id);

    /**
     * 插入Driverdl到数据库,包括null值
     *
     * @param value
     * @return
     */
    int insertDriverdl(Driverdl value);

    /**
     * 插入Driverdl中属性值不为null的数据到数据库
     *
     * @param value
     * @return
     */
    int insertNonEmptyDriverdl(Driverdl value);

    /**
     * 批量插入Driverdl到数据库
     *
     * @param value
     * @return
     */
    int insertDriverdlByBatch(List<Driverdl> value);

    /**
     * 通过Driverdl的id删除Driverdl
     *
     * @param id
     * @return
     */
    int deleteDriverdlById(Integer id);

    /**
     * 通过辅助工具Assist的条件删除Driverdl
     *
     * @param assist
     * @return
     */
    int deleteDriverdl(Assist assist);

    /**
     * 通过Driverdl的id更新Driverdl中的数据,包括null值
     *
     * @param enti
     * @return
     */
    int updateDriverdlById(Driverdl enti);

    /**
     * 通过辅助工具Assist的条件更新Driverdl中的数据,包括null值
     *
     * @param value
     * @param assist
     * @return
     */
    int updateDriverdl(Driverdl value, Assist assist);

    /**
     * 通过Driverdl的id更新Driverdl中属性不为null的数据
     *
     * @param enti
     * @return
     */
    int updateNonEmptyDriverdlById(Driverdl enti);

    /**
     * 通过辅助工具Assist的条件更新Driverdl中属性不为null的数据
     *
     * @param value
     * @param assist
     * @return
     */
    int updateNonEmptyDriverdl(Driverdl value, Assist assist);
}