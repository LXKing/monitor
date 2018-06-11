package com.rayton.gps.dao.baseinfo.sysvid;

import com.rayton.gps.util.Assist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysvidDao {
    /**
     * 获得Sysvid数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
     *
     * @param assist
     * @return
     */
    long getSysvidRowCount(Assist assist);

    /**
     * 获得Sysvid数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
     *
     * @param assist
     * @return
     */
    List<Sysvid> selectSysvid(Assist assist);

    /**
     * 获得一个Sysvid对象,以参数Sysvid对象中不为空的属性作为条件进行查询
     *
     * @param obj
     * @return
     */
    Sysvid selectSysvidByObj(Sysvid obj);

    /**
     * 通过Sysvid的id获得Sysvid对象
     *
     * @param id
     * @return
     */
    Sysvid selectSysvidById(Integer id);

    /**
     * 插入Sysvid到数据库,包括null值
     *
     * @param value
     * @return
     */
    int insertSysvid(Sysvid value);

    /**
     * 插入Sysvid中属性值不为null的数据到数据库
     *
     * @param value
     * @return
     */
    int insertNonEmptySysvid(Sysvid value);

    /**
     * 批量插入Sysvid到数据库,包括null值
     *
     * @param value
     * @return
     */
    int insertSysvidByBatch(List<Sysvid> value);

    /**
     * 通过Sysvid的id删除Sysvid
     *
     * @param id
     * @return
     */
    int deleteSysvidById(Integer id);

    /**
     * 通过辅助工具Assist的条件删除Sysvid
     *
     * @param assist
     * @return
     */
    int deleteSysvid(Assist assist);

    /**
     * 通过Sysvid的id更新Sysvid中的数据,包括null值
     *
     * @param enti
     * @return
     */
    int updateSysvidById(Sysvid enti);

    /**
     * 通过辅助工具Assist的条件更新Sysvid中的数据,包括null值
     *
     * @param value
     * @param assist
     * @return
     */
    int updateSysvid(@Param("enti") Sysvid value, @Param("assist") Assist assist);

    /**
     * 通过Sysvid的id更新Sysvid中属性不为null的数据
     *
     * @param enti
     * @return
     */
    int updateNonEmptySysvidById(Sysvid enti);

    /**
     * 通过辅助工具Assist的条件更新Sysvid中属性不为null的数据
     *
     * @param value
     * @param assist
     * @return
     */
    int updateNonEmptySysvid(@Param("enti") Sysvid value, @Param("assist") Assist assist);
}