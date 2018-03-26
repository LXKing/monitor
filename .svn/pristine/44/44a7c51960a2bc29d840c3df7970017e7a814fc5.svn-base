package com.edata.monitor.dao.baseinfo.polygonArea;

import com.edata.common.LatLng;
import com.edata.monitor.util.KeyValue;

import java.util.List;

/**
 * 多边形区域数据访问接口
 *
 * @author yangzs
 */
public interface IPolygonAreaDao {
    int queryPageCount(String companyId, String filter);

    List<PolygonAreaInfo> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    int searchPageCount(String companyId, String filter);

    List<PolygonAreaInfo> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    void create(PolygonArea dto);

    PolygonArea fetch(long id);

    int update(PolygonArea dto);

    void delete(long id);

    void createPolygonPoint(List<KeyValue> points);

    List<LatLng> fetchPolygonPoint(long polygonAreaId);

    void deletePolygonPoint(long polygonAreaId);

    boolean existOutId(String name, String companyId, long id);

    boolean exist(String name, String companyId);
}
