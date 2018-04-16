package com.rayton.gps.dao.cache.area;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IAreaCatcherDao {
    List<AreaInfo> getCircles();

    List<AreaInfo> getRectangles();

    List<AreaInfo> getPolygons();

    List<AreaInfo> getRoutes();

    List<Long> getSectionIds(long areaId);
}
