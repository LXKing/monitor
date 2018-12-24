package mmp.gps.monitor.dao;

import mmp.gps.domain.poi.Poi;
import mmp.gps.domain.poi.PoiInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPoiDao {
    int queryPageCount(String companyId, String filter);

    List<PoiInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    int searchPageCount(String companyId, String filter);

    List<PoiInfo> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    PoiInfo fetchInfo(long id);

    void create(Poi dto);

    int update(Poi dto);

    Poi fetch(long id);

    void delete(long id);

    boolean existOutId(String name, String companyId, long id);

    boolean exist(String name, String companyId);
}
