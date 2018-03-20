package com.edata.monitor.dao.cache;

import java.util.List;

public interface IAreaCatcherDao {
	public List<AreaInfoDto> getCircles();

	public List<AreaInfoDto> getRectangles();

	public List<AreaInfoDto> getPolygons();

	public List<AreaInfoDto> getRoutes();

	public List<Long> getSectionIds(long areaId);
}
