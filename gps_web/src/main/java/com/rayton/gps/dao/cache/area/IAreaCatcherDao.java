package com.rayton.gps.dao.cache.area;

import java.util.List;

public interface IAreaCatcherDao {
    public List<AreaInfo> getCircles();

    public List<AreaInfo> getRectangles();

    public List<AreaInfo> getPolygons();

    public List<AreaInfo> getRoutes();

    public List<Long> getSectionIds(long areaId);
}
