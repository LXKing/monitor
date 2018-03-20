package com.edata.monitor.dao.baseinfo;

import java.util.List;

public interface ICircleAreaDao {

	int queryPageCount(String companyId, String filter);

	List<CircleAreaInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	int searchPageCount(String companyId, String filter);

	List<CircleAreaInfoDto> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	void create(CircleAreaDto dto);

	int update(CircleAreaDto dto);

	CircleAreaDto fetch(long id);

	void delete(long id);

	void deleteAreaInDevice(long id);

	void deleteAreaInMaplayer(long id);

	boolean existOutId(String name, String companyId, long id);

	boolean exist(String name, String companyId);
}
