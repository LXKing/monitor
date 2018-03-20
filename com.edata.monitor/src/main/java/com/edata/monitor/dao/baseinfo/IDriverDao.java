package com.edata.monitor.dao.baseinfo;

import java.util.List;

import com.edata.monitor.util.KeyValue;

/**
 * 驾驶员数据访问接口
 * 
 * @author yangzs
 *
 */
public interface IDriverDao {
	/**
	 * 查询驾驶员行数
	 */
	int queryPageCount(String companyId, String filter);

	/**
	 * 查询驾驶员内容
	 */
	List<DriverInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	/**
	 * 创建驾驶员
	 */
	void create(DriverDto dto);

	/**
	 * 修改驾驶员
	 */
	int update(DriverDto dto);

	/**
	 * 删除驾驶员
	 */
	void delete(String id);

	/**
	 * 删除驾驶员与车辆关联
	 */
	void deleteVehicleInDriver(String driverId);

	/**
	 * 读取驾驶员
	 */
	DriverDto fetch(String id);

	/**
	 * 获取驾驶员与车辆关系
	 */
	List<VehicleInfoDto> assignedVehicles(String driverId);

	/**
	 * 添加驾驶员与车辆关系
	 */
	void addVehicles(List<KeyValue> vehicles);

	/**
	 * 删除驾驶员与车辆关系
	 */
	void removeVehicle(String driverId, String vehicleId);
}
