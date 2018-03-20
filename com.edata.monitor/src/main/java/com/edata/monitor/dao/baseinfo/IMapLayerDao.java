package com.edata.monitor.dao.baseinfo;

import java.util.List;

import com.edata.monitor.util.KeyValue;

public interface IMapLayerDao {

	List<MapLayerDto> query(String userId, String filter);

	MapLayerDto getMapLayInfo(String mapLayerId);

	void create(MapLayerDto dto);

	MapLayerDto fetch(String id);

	int update(MapLayerDto dto);

	void delete(String id);

	void deleteAreaInMaplayer(String id);

	void addAreas(List<KeyValue> areas);

	void removeArea(String mapLayerId, long areaId, int areaType);

	List<Long> getCircleAreas(String mapLayerId);

	List<Long> getRectangleAreas(String mapLayerId);

	List<Long> getPolygonAreas(String mapLayerId);

	List<Long> getRouteAreas(String mapLayerId);

	List<Long> getPois(String mapLayerId);

	void setVisible(String mapLayerId, boolean visible);

}
