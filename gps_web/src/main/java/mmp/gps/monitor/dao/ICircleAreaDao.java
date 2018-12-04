package mmp.gps.monitor.dao;


import mmp.gps.domain.area.CircleArea;
import mmp.gps.domain.area.CircleAreaInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICircleAreaDao {

    int queryPageCount(String companyId, String filter);

    List<CircleAreaInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    int searchPageCount(String companyId, String filter);

    List<CircleAreaInfo> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    void create(CircleArea dto);

    int update(CircleArea dto);

    CircleArea fetch(long id);

    void delete(long id);

    void deleteAreaInDevice(long id);

    void deleteAreaInMaplayer(long id);

    boolean existOutId(String name, String companyId, long id);

    boolean exist(String name, String companyId);
}
