package mmp.gps.monitor.dao;

import mmp.gps.domain.area.SectionArea;
import mmp.gps.domain.area.SectionAreaInfo;
import mmp.gps.domain.area.SectionPoint;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 路线区域数据访问接口
 */

@Repository
public interface ISectionAreaDao {
    int queryPageCount(String companyId, String filter);

    List<SectionAreaInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    int searchPageCount(String companyId, String filter);

    List<SectionAreaInfo> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    void create(SectionArea dto);

    SectionArea fetch(long id);

    int update(SectionArea dto);

    void delete(long id);

    void createSectionPoint(List<SectionPoint> points);

    List<SectionPoint> fetchSectionPoint(long sectionAreaId);

    void deleteSectionPoint(long sectionAreaId);

    boolean existOutId(String name, String companyId, long id);

    boolean exist(String name, String companyId);
}
