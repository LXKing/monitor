package mmp.gps.monitor.dao;

import mmp.gps.common.util.KeyValue;
import mmp.gps.domain.area.RouteArea;
import mmp.gps.domain.area.RouteAreaInfo;
import mmp.gps.domain.area.SectionAreaInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 路线区域数据访问接口
 */

@Repository
public interface IRouteAreaDao {
    int queryPageCount(String companyId, String filter);

    List<RouteAreaInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    int searchPageCount(String companyId, String filter);

    List<RouteAreaInfo> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    void create(RouteArea dto);

    RouteArea fetch(long id);

    int update(RouteArea dto);

    void delete(long id);

    void deleteRouteSection(long routeId);

    boolean existOutId(String name, String companyId, long id);

    boolean exist(String name, String companyId);

    List<SectionAreaInfo> assignedSections(long routeId);

    void addSections(List<KeyValue> sections);

    void removeSection(long routeId, long sectionId);

    RouteAreaInfo fetchInfo(Long id);

}
