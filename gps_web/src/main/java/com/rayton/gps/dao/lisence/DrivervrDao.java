package com.rayton.gps.dao.lisence;
import com.rayton.gps.dao.lisence.Drivervr;
import java.util.List;
import com.rayton.gps.util.Assist;
import org.apache.ibatis.annotations.Param;
public interface DrivervrDao{
	/**
	 * 获得Drivervr数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getDrivervrRowCount(Assist assist);
	/**
	 * 获得Drivervr数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Drivervr> selectDrivervr(Assist assist);
	/**
	 * 获得一个Drivervr对象,以参数Drivervr对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Drivervr selectDrivervrByObj(Drivervr obj);
	/**
	 * 通过Drivervr的id获得Drivervr对象
	 * @param id
	 * @return
	 */
    Drivervr selectDrivervrById(String id);
	/**
	 * 插入Drivervr到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertDrivervr(Drivervr value);
	/**
	 * 插入Drivervr中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyDrivervr(Drivervr value);
	/**
	 * 批量插入Drivervr到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertDrivervrByBatch(List<Drivervr> value);
	/**
	 * 通过Drivervr的id删除Drivervr
	 * @param id
	 * @return
	 */
    int deleteDrivervrById(String id);
	/**
	 * 通过辅助工具Assist的条件删除Drivervr
	 * @param assist
	 * @return
	 */
    int deleteDrivervr(Assist assist);
	/**
	 * 通过Drivervr的id更新Drivervr中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateDrivervrById(Drivervr enti);
 	/**
	 * 通过辅助工具Assist的条件更新Drivervr中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateDrivervr(@Param("enti") Drivervr value, @Param("assist") Assist assist);
	/**
	 * 通过Drivervr的id更新Drivervr中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyDrivervrById(Drivervr enti);
 	/**
	 * 通过辅助工具Assist的条件更新Drivervr中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyDrivervr(@Param("enti") Drivervr value, @Param("assist") Assist assist);
}