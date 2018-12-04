package mmp.gps.monitor.dao;

import mmp.gps.domain.area.RectangleArea;
import mmp.gps.domain.area.RectangleAreaInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 矩形区域数据访问接口
 */

@Repository
public interface IRectangleAreaDao {
    int queryPageCount(String companyId, String filter);

    List<RectangleAreaInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    int searchPageCount(String companyId, String filter);

    List<RectangleAreaInfo> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    void create(RectangleArea dto);

    RectangleArea fetch(long id);

    int update(RectangleArea dto);

    void delete(long id);

    boolean existOutId(String name, String companyId, long id);

    boolean exist(String name, String companyId);
}
