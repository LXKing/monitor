package com.edata.monitor.dao.baseinfo;

import java.util.List;

/**
 * 矩形区域数据访问接口
 * 
 * @author yangzs
 *
 */
public interface IRectangleAreaDao {
	int queryPageCount(String companyId, String filter);

	List<RectangleAreaInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	int searchPageCount(String companyId, String filter);
	
	List<RectangleAreaInfoDto> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	void create(RectangleAreaDto dto);

	RectangleAreaDto fetch(long id);

	int update(RectangleAreaDto dto);

	void delete(long id);

	boolean existOutId(String name, String companyId, long id);

	boolean exist(String name, String companyId);
}
