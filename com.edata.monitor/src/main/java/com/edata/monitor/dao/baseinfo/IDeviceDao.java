package com.edata.monitor.dao.baseinfo;

import java.util.List;

public interface IDeviceDao {
	/**
	 * 查询设备总行数
	 */
	int queryPageCount(String companyId, String filter);

	/**
	 * 查询设备页内容
	 */
	List<DeviceInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	/**
	 * 创建新的设备
	 */
	void create(DeviceDto dto);

	/**
	 * 获取设备号
	 */
	String getDeviceNumber(String id);

	/**
	 * 获取车辆号
	 */
	String getPlateNumber(String deviceNumber);

	/**
	 * 修改设备
	 */
	int update(DeviceDto dto);

	/**
	 * 已绑定的车辆
	 */
	String assignedVehicle(String id);

	/**
	 * 删除设备
	 */
	void delete(String id);

	/**
	 * 读取设备
	 */
	DeviceDto fetch(String id);

	/**
	 * 是否已存在设备
	 */
	boolean exist(String number);

	/**
	 * 是否已存在设备
	 */
	boolean existOutId(String number, String id);

	/**
	 * 搜索设备资料总行数
	 */
	int searchPageCount(String companyId, String filter);

	/**
	 * 搜索设备资料页内容
	 */
	List<DeviceSearchInfoDto> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	/**
	 * 搜索未绑定车辆的设备资料总行数
	 */
	int freePageCount(String companyId, String deviceNumber);

	/**
	 * 搜索未绑定车辆的设备资料页内容
	 */
	List<DeviceSearchInfoDto> freePageDetail(String companyId, String deviceNumber, int pageIndex, int pageSize);

	/**
	 * 清除事件菜单
	 */
	void clearEventMenu(String deviceNumber);

	/**
	 * 更新、修改、重设事件菜单
	 */
	void resetEventNumber(String deviceNumber, short eventId, String content);

	/**
	 * 删除事件菜单
	 */
	void removeEventNumber(String deviceNumber, short eventId);
}
