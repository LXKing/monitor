package com.edata.monitor.dao.baseinfo;

import java.util.List;

/**
 * 车队服务接口
 * 
 * @author 生
 *
 */
public interface IMotorcadeDao {
	/**
	 * 读取车队列表
	 */
	List<MotorcadeDto> list(String companyId);

	/**
	 * 创建新的车队
	 */
	void create(MotorcadeDto dto);

	/**
	 * 修改车队
	 */
	int update(MotorcadeDto dto);

	/**
	 * 删除车队
	 */
	void delete(String id);

	/**
	 * 读取车队
	 */
	MotorcadeDto fetch(String id);

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
