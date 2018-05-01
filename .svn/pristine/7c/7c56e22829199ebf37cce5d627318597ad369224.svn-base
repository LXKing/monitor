package com.rayton.gps.service;
import java.util.List;
import com.rayton.gps.dao.Viewport;
import com.rayton.gps.util.Assist;
public interface ViewportService{
	/**
	 * 获得Viewport数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getViewportRowCount(Assist assist);
	/**
	 * 获得Viewport数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Viewport> selectViewport(Assist assist);
	/**
	 * 获得一个Viewport对象,以参数Viewport对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Viewport selectViewportByObj(Viewport obj);
	/**
	 * 通过Viewport的id获得Viewport对象
	 * @param id
	 * @return
	 */
    Viewport selectViewportById(Integer id);
	/**
	 * 插入Viewport到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertViewport(Viewport value);
	/**
	 * 插入Viewport中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyViewport(Viewport value);
	/**
	 * 批量插入Viewport到数据库
	 * @param value
	 * @return
	 */
    int insertViewportByBatch(List<Viewport> value);
	/**
	 * 通过Viewport的id删除Viewport
	 * @param id
	 * @return
	 */
    int deleteViewportById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除Viewport
	 * @param assist
	 * @return
	 */
    int deleteViewport(Assist assist);
	/**
	 * 通过Viewport的id更新Viewport中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateViewportById(Viewport enti);
 	/**
	 * 通过辅助工具Assist的条件更新Viewport中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateViewport(Viewport value, Assist assist);
	/**
	 * 通过Viewport的id更新Viewport中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyViewportById(Viewport enti);
 	/**
	 * 通过辅助工具Assist的条件更新Viewport中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyViewport(Viewport value, Assist assist);
}