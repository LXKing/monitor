package com.rayton.gps.dao.lisence;
import com.rayton.gps.dao.lisence.Drivervl;
import java.util.List;
import com.rayton.gps.util.Assist;
import org.apache.ibatis.annotations.Param;
public interface DrivervlDao{
	/**
	 * 获得Drivervl数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getDrivervlRowCount(Assist assist);
	/**
	 * 获得Drivervl数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Drivervl> selectDrivervl(Assist assist);
	/**
	 * 获得一个Drivervl对象,以参数Drivervl对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Drivervl selectDrivervlByObj(Drivervl obj);
	/**
	 * 通过Drivervl的id获得Drivervl对象
	 * @param id
	 * @return
	 */
    Drivervl selectDrivervlById(String id);
	/**
	 * 插入Drivervl到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertDrivervl(Drivervl value);
	/**
	 * 插入Drivervl中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyDrivervl(Drivervl value);
	/**
	 * 批量插入Drivervl到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertDrivervlByBatch(List<Drivervl> value);
	/**
	 * 通过Drivervl的id删除Drivervl
	 * @param id
	 * @return
	 */
    int deleteDrivervlById(String id);
	/**
	 * 通过辅助工具Assist的条件删除Drivervl
	 * @param assist
	 * @return
	 */
    int deleteDrivervl(Assist assist);
	/**
	 * 通过Drivervl的id更新Drivervl中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateDrivervlById(Drivervl enti);
 	/**
	 * 通过辅助工具Assist的条件更新Drivervl中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateDrivervl(@Param("enti") Drivervl value, @Param("assist") Assist assist);
	/**
	 * 通过Drivervl的id更新Drivervl中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyDrivervlById(Drivervl enti);
 	/**
	 * 通过辅助工具Assist的条件更新Drivervl中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyDrivervl(@Param("enti") Drivervl value, @Param("assist") Assist assist);
}