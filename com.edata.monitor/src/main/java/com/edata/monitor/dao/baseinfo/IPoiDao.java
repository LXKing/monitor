package com.edata.monitor.dao.baseinfo;

import java.util.List;

public interface IPoiDao {
	int queryPageCount(String companyId, String filter);

	List<PoiInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	int searchPageCount(String companyId, String filter);

	List<PoiInfoDto> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	PoiInfoDto fetchInfo(long id);

	void create(PoiDto dto);

	int update(PoiDto dto);

	PoiDto fetch(long id);

	void delete(long id);

	boolean existOutId(String name, String companyId, long id);

	boolean exist(String name, String companyId);
}
