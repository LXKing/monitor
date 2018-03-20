package com.edata.monitor.dao.baseinfo;

import java.util.List;

import com.edata.common.LatLng;
import com.edata.monitor.util.KeyValue;

/**
 * 多边形区域数据访问接口
 * 
 * @author yangzs
 *
 */
public interface IPolygonAreaDao {
	int queryPageCount(String companyId, String filter);

	List<PolygonAreaInfoDto> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	int searchPageCount(String companyId, String filter);

	List<PolygonAreaInfoDto> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

	void create(PolygonAreaDto dto);

	PolygonAreaDto fetch(long id);

	int update(PolygonAreaDto dto);

	void delete(long id);

	void createPolygonPoint(List<KeyValue> points);

	List<LatLng> fetchPolygonPoint(long polygonAreaId);

	void deletePolygonPoint(long polygonAreaId);

	boolean existOutId(String name, String companyId, long id);

	boolean exist(String name, String companyId);
}
