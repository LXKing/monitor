package com.rayton.gps.dao.lisence;
import com.rayton.gps.dao.lisence.VehicleRegister;
import java.util.List;
import com.rayton.gps.util.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRegisterDao{
	/**
	 * 获得VehicleRegister数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getVehicleRegisterRowCount(Assist assist);
	/**
	 * 获得VehicleRegister数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<VehicleRegister> selectVehicleRegister(Assist assist);
	/**
	 * 获得一个VehicleRegister对象,以参数VehicleRegister对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    VehicleRegister selectVehicleRegisterByObj(VehicleRegister obj);
	/**
	 * 通过VehicleRegister的id获得VehicleRegister对象
	 * @param id
	 * @return
	 */
    VehicleRegister selectVehicleRegisterById(Integer id);
	/**
	 * 插入VehicleRegister到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertVehicleRegister(VehicleRegister value);
	/**
	 * 插入VehicleRegister中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyVehicleRegister(VehicleRegister value);
	/**
	 * 批量插入VehicleRegister到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertVehicleRegisterByBatch(List<VehicleRegister> value);
	/**
	 * 通过VehicleRegister的id删除VehicleRegister
	 * @param id
	 * @return
	 */
    int deleteVehicleRegisterById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除VehicleRegister
	 * @param assist
	 * @return
	 */
    int deleteVehicleRegister(Assist assist);
	/**
	 * 通过VehicleRegister的id更新VehicleRegister中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateVehicleRegisterById(VehicleRegister enti);
 	/**
	 * 通过辅助工具Assist的条件更新VehicleRegister中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateVehicleRegister(@Param("enti") VehicleRegister value, @Param("assist") Assist assist);
	/**
	 * 通过VehicleRegister的id更新VehicleRegister中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyVehicleRegisterById(VehicleRegister enti);
 	/**
	 * 通过辅助工具Assist的条件更新VehicleRegister中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyVehicleRegister(@Param("enti") VehicleRegister value, @Param("assist") Assist assist);
}