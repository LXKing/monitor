package com.edata.monitor.dao.baseinfo;

import java.sql.Date;
import java.util.List;

public interface IMaintainDao {
	/**
	 * 查询保养总行数
	 */
	int queryPageCount(String companyId, String plateNumber, Date from, Date to);

	/**
	 * 查询保养页内容
	 */
	List<MaintainInfoDto> queryPageDetail(String companyId, String plateNumber, Date from, Date to, int pageIndex, int pageSize);

	/**
	 * 创建新的保养
	 */
	void create(MaintainDto dto);

	/**
	 * 修改保养
	 */
	int update(MaintainDto dto);

	/**
	 * 更新下次保养日期
	 */
	void updateNextMaintainDate(String vehicleId, Date date);

	/**
	 * 删除保养
	 */
	void delete(String id);

	/**
	 * 读取保养
	 */
	MaintainDto fetch(String id);
}
