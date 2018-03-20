package com.edata.monitor.dao.baseinfo;

import java.util.List;

import com.edata.monitor.util.KeyValue;

/**
 * 路线区域数据访问接口
 * 
 * @author yangzs
 *
 */
public interface IRouteAreaDao {
	int queryPageCount(String companyId, String filter);

	List<RouteAreaInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	int searchPageCount(String companyId, String filter);

	List<RouteAreaInfoDto> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	void create(RouteAreaDto dto);

	RouteAreaDto fetch(long id);

	int update(RouteAreaDto dto);

	void delete(long id);

	void deleteRouteSection(long routeId);

	boolean existOutId(String name, String companyId, long id);

	boolean exist(String name, String companyId);

	List<SectionAreaInfoDto> assignedSections(long routeId);

	void addSections(List<KeyValue> sections);

	void removeSection(long routeId, long sectionId);

	RouteAreaInfoDto fetchInfo(Long id);

}
