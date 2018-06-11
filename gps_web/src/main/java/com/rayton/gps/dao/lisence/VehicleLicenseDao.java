package com.rayton.gps.dao.lisence;

import com.rayton.gps.util.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleLicenseDao {
    /**
     * 获得VehicleLicense数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
     *
     * @param assist
     * @return
     */
    long getVehicleLicenseRowCount(Assist assist);

    /**
     * 获得VehicleLicense数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
     *
     * @param assist
     * @return
     */
    List<VehicleLicense> selectVehicleLicense(Assist assist);

    /**
     * 获得一个VehicleLicense对象,以参数VehicleLicense对象中不为空的属性作为条件进行查询
     *
     * @param obj
     * @return
     */
    VehicleLicense selectVehicleLicenseByObj(VehicleLicense obj);

    /**
     * 通过VehicleLicense的id获得VehicleLicense对象
     *
     * @param id
     * @return
     */
    VehicleLicense selectVehicleLicenseById(Integer id);

    /**
     * 插入VehicleLicense到数据库,包括null值
     *
     * @param value
     * @return
     */
    int insertVehicleLicense(VehicleLicense value);

    /**
     * 插入VehicleLicense中属性值不为null的数据到数据库
     *
     * @param value
     * @return
     */
    int insertNonEmptyVehicleLicense(VehicleLicense value);

    /**
     * 批量插入VehicleLicense到数据库,包括null值
     *
     * @param value
     * @return
     */
    int insertVehicleLicenseByBatch(List<VehicleLicense> value);

    /**
     * 通过VehicleLicense的id删除VehicleLicense
     *
     * @param id
     * @return
     */
    int deleteVehicleLicenseById(Integer id);

    /**
     * 通过辅助工具Assist的条件删除VehicleLicense
     *
     * @param assist
     * @return
     */
    int deleteVehicleLicense(Assist assist);

    /**
     * 通过VehicleLicense的id更新VehicleLicense中的数据,包括null值
     *
     * @param enti
     * @return
     */
    int updateVehicleLicenseById(VehicleLicense enti);

    /**
     * 通过辅助工具Assist的条件更新VehicleLicense中的数据,包括null值
     *
     * @param value
     * @param assist
     * @return
     */
    int updateVehicleLicense(@Param("enti") VehicleLicense value, @Param("assist") Assist assist);

    /**
     * 通过VehicleLicense的id更新VehicleLicense中属性不为null的数据
     *
     * @param enti
     * @return
     */
    int updateNonEmptyVehicleLicenseById(VehicleLicense enti);

    /**
     * 通过辅助工具Assist的条件更新VehicleLicense中属性不为null的数据
     *
     * @param value
     * @param assist
     * @return
     */
    int updateNonEmptyVehicleLicense(@Param("enti") VehicleLicense value, @Param("assist") Assist assist);
}