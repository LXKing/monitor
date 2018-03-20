package com.edata.monitor.dao.baseinfo;

import java.util.List;

/**
 * 路线区域数据访问接口
 * 
 * @author yangzs
 *
 */
public interface ISectionAreaDao {
	int queryPageCount(String companyId, String filter);

	List<SectionAreaInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	int searchPageCount(String companyId, String filter);

	List<SectionAreaInfoDto> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	void create(SectionAreaDto dto);

	SectionAreaDto fetch(long id);

	int update(SectionAreaDto dto);

	void delete(long id);

	void createSectionPoint(List<SectionPointDto> points);

	List<SectionPointDto> fetchSectionPoint(long sectionAreaId);

	void deleteSectionPoint(long sectionAreaId);

	boolean existOutId(String name, String companyId, long id);

	boolean exist(String name, String companyId);
}
