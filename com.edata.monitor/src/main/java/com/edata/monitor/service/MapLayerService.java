package com.edata.monitor.service;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.baseinfo.circleArea.CircleAreaInfo;
import com.edata.monitor.dao.baseinfo.mapLayer.IMapLayerDao;
import com.edata.monitor.dao.baseinfo.mapLayer.MapLayer;
import com.edata.monitor.dao.baseinfo.mapLayer.MapLayerDto;
import com.edata.monitor.dao.baseinfo.mapLayer.MapLayerInfo;
import com.edata.monitor.dao.baseinfo.poi.PoiInfo;
import com.edata.monitor.dao.baseinfo.polygonArea.PolygonAreaInfo;
import com.edata.monitor.dao.baseinfo.rectangleArea.RectangleAreaInfo;
import com.edata.monitor.dao.baseinfo.routeArea.RouteAreaInfo;
import com.edata.monitor.util.KeyValue;
import com.edata.monitor.util.enums.AreaKinds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapLayerService {
    @Autowired
    private IMapLayerDao mapLayerDao;
    @Autowired
    private CircleAreaService circleAreaService;
    @Autowired
    private RectangleAreaService rectangleAreaService;
    @Autowired
    private PolygonAreaService polygonAreaService;
    @Autowired
    private RouteAreaService routeAreaService;
    @Autowired
    private PoiService poiService;

    public List<MapLayerInfo> query(String userId, String filter) {
        List<MapLayerInfo> list = new ArrayList<MapLayerInfo>();
        List<MapLayerDto> result = mapLayerDao.query(userId, filter);
        for (MapLayerDto dto : result) {
            MapLayerInfo info = new MapLayerInfo();
            info.setId(dto.id);
            info.setName(dto.name);
            info.setRemark(dto.remark);
            info.setVisible(dto.visible);

            dto.circleAreas = mapLayerDao.getCircleAreas(dto.id);
            for (Long id : dto.circleAreas) {
                info.getCircleAreas().add(circleAreaService.fetchInfo(id));
            }

            dto.rectangleAreas = mapLayerDao.getRectangleAreas(dto.id);
            for (Long id : dto.rectangleAreas) {
                info.getRectangleAreas().add(rectangleAreaService.fetchInfo(id));
            }

            dto.polygonAreas = mapLayerDao.getPolygonAreas(dto.id);
            for (Long id : dto.polygonAreas) {
                info.getPolygonAreas().add(polygonAreaService.fetchInfo(id));
            }

            dto.routeAreas = mapLayerDao.getRouteAreas(dto.id);
            for (Long id : dto.routeAreas) {
                info.getRouteAreas().add(routeAreaService.fetchInfo(id));
            }

            dto.pois = mapLayerDao.getPois(dto.id);
            for (Long id : dto.pois) {
                info.getPois().add(poiService.fetchInfo(id));
            }

            list.add(info);
        }
        return list;
    }

    public MapLayerInfo getMapLayInfo(String mapLayerId) {
        MapLayerDto dto = mapLayerDao.getMapLayInfo(mapLayerId);
        MapLayerInfo info = new MapLayerInfo();
        info.setId(dto.id);
        info.setName(dto.name);
        info.setRemark(dto.remark);
        info.setVisible(dto.visible);

        dto.circleAreas = mapLayerDao.getCircleAreas(dto.id);
        for (Long id : dto.circleAreas) {
            info.getCircleAreas().add(circleAreaService.fetchInfo(id));
        }

        dto.rectangleAreas = mapLayerDao.getRectangleAreas(dto.id);
        for (Long id : dto.rectangleAreas) {
            info.getRectangleAreas().add(rectangleAreaService.fetchInfo(id));
        }

        dto.polygonAreas = mapLayerDao.getPolygonAreas(dto.id);
        for (Long id : dto.polygonAreas) {
            info.getPolygonAreas().add(polygonAreaService.fetchInfo(id));
        }

        dto.routeAreas = mapLayerDao.getRouteAreas(dto.id);
        for (Long id : dto.routeAreas) {
            info.getRouteAreas().add(routeAreaService.fetchInfo(id));
        }

        dto.pois = mapLayerDao.getPois(dto.id);
        for (Long id : dto.pois) {
            info.getPois().add(poiService.fetchInfo(id));
        }

        return info;
    }

    @ServiceMethod(id = "baseinfo.mapLayer.create", pid = "baseinfo.mapLayer", name = "创建地图图层")
    @Transactional
    public void create(MapLayer mapLayer) {
        mapLayer.setId(ObjectId.next());
        MapLayerDto dto = new MapLayerDto();
        BeanUtils.copyProperties(mapLayer, dto);
        // dto.id = mapLayer.getId();
        // dto.companyId = mapLayer.getCompanyId();
        // dto.userId = mapLayer.getUserId();
        // dto.name = mapLayer.getName();
        // dto.visible = mapLayer.isVisible();
        // dto.remark = mapLayer.getRemark();

        mapLayerDao.create(dto);
    }

    public MapLayer fetch(String id) {
        MapLayerDto dto = mapLayerDao.fetch(id);
        MapLayer mapLayer = new MapLayer();
        mapLayer.setId(dto.id);
        mapLayer.setCompanyId(dto.companyId);
        mapLayer.setUserId(dto.userId);
        mapLayer.setName(dto.name);
        mapLayer.setVisible(dto.visible);
        mapLayer.setRemark(dto.remark);
        mapLayer.setEditTime(dto.editTime);

        return mapLayer;
    }

    @ServiceMethod(id = "baseinfo.mapLayer.update", pid = "baseinfo.mapLayer", name = "修改地图图层")
    @Transactional
    public void update(MapLayer mapLayer) {
        MapLayerDto dto = new MapLayerDto();
        dto.id = mapLayer.getId();
        dto.companyId = mapLayer.getCompanyId();
        dto.userId = mapLayer.getUserId();
        dto.name = mapLayer.getName();
        dto.visible = mapLayer.isVisible();
        dto.remark = mapLayer.getRemark();
        dto.editTime = mapLayer.getEditTime();

        int rows = mapLayerDao.update(dto);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    @ServiceMethod(id = "baseinfo.mapLayer.delete", pid = "baseinfo.mapLayer", name = "删除地图图层")
    @Transactional
    public void delete(String id) {
        mapLayerDao.delete(id);
        mapLayerDao.deleteAreaInMaplayer(id);
    }

    public List<CircleAreaInfo> getCircleAreas(String mapLayerId) {
        List<CircleAreaInfo> result = new ArrayList<CircleAreaInfo>();
        List<Long> areas = mapLayerDao.getCircleAreas(mapLayerId);
        for (Long id : areas) {
            CircleAreaInfo info = circleAreaService.fetchInfo(id);
            result.add(info);
        }

        return result;
    }

    private List<KeyValue> makeParms(String mapLayerId, List<Long> areaIds, int areaType) {
        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (Long id : areaIds) {
            KeyValue row = new KeyValue();
            row.setKey(mapLayerId);

            KeyValue sub = new KeyValue();
            sub.setKey(id);
            sub.setValue(areaType);

            row.setValue(sub);
            rows.add(row);
        }

        return rows;
    }

    @ServiceMethod(id = "baseinfo.mapLayer.addCircleAreas", pid = "baseinfo.mapLayer", name = "添加圆形区域")
    @Transactional
    public void addCircleAreas(String mapLayerId, List<Long> areaIds) {
        mapLayerDao.addAreas(makeParms(mapLayerId, areaIds, AreaKinds.CircleArea.getIndex()));
    }

    @ServiceMethod(id = "baseinfo.mapLayer.removeCircleArea", pid = "baseinfo.mapLayer", name = "移除圆形区域")
    @Transactional
    public void removeCircleArea(String mapLayerId, long areaId) {
        mapLayerDao.removeArea(mapLayerId, areaId, AreaKinds.CircleArea.getIndex());
    }

    public List<RectangleAreaInfo> getRectangleAreas(String mapLayerId) {
        List<RectangleAreaInfo> result = new ArrayList<RectangleAreaInfo>();
        List<Long> areas = mapLayerDao.getRectangleAreas(mapLayerId);
        areas.forEach(id -> result.add(rectangleAreaService.fetchInfo(id)));
        // for (Long id : areas) {
        //     RectangleAreaInfo info = rectangleAreaService.fetchInfo(id);
        //     result.add(info);
        // }

        return result;
    }

    @ServiceMethod(id = "baseinfo.mapLayer.addRectangleAreas", pid = "baseinfo.mapLayer", name = "添加矩形区域")
    @Transactional
    public void addRectangleAreas(String mapLayerId, List<Long> areaIds) {
        mapLayerDao.addAreas(makeParms(mapLayerId, areaIds, AreaKinds.RectangleArea.getIndex()));
    }

    @ServiceMethod(id = "baseinfo.mapLayer.removeRectangleArea", pid = "baseinfo.mapLayer", name = "移除矩形区域")
    @Transactional
    public void removeRectangleArea(String mapLayerId, long areaId) {
        mapLayerDao.removeArea(mapLayerId, areaId, AreaKinds.RectangleArea.getIndex());
    }

    public List<PolygonAreaInfo> getPolygonAreas(String mapLayerId) {
        List<PolygonAreaInfo> result = new ArrayList<PolygonAreaInfo>();
        List<Long> areas = mapLayerDao.getPolygonAreas(mapLayerId);
        for (Long id : areas) {
            PolygonAreaInfo info = polygonAreaService.fetchInfo(id);
            result.add(info);
        }

        return result;
    }

    @ServiceMethod(id = "baseinfo.mapLayer.addPolygonAreas", pid = "baseinfo.mapLayer", name = "添加多边形区域")
    @Transactional
    public void addPolygonAreas(String mapLayerId, List<Long> areaIds) {
        mapLayerDao.addAreas(makeParms(mapLayerId, areaIds, AreaKinds.PolygonArea.getIndex()));
    }

    @ServiceMethod(id = "baseinfo.mapLayer.removePolygonArea", pid = "baseinfo.mapLayer", name = "移除多边形区域")
    @Transactional
    public void removePolygonArea(String mapLayerId, long areaId) {
        mapLayerDao.removeArea(mapLayerId, areaId, AreaKinds.PolygonArea.getIndex());
    }

    public List<RouteAreaInfo> getRouteAreas(String mapLayerId) {
        List<RouteAreaInfo> result = new ArrayList<RouteAreaInfo>();
        List<Long> areas = mapLayerDao.getRouteAreas(mapLayerId);
        for (Long id : areas) {
            RouteAreaInfo info = routeAreaService.fetchInfo(id);
            result.add(info);
        }
        return result;
    }

    @ServiceMethod(id = "baseinfo.mapLayer.addRouteAreas", pid = "baseinfo.mapLayer", name = "添加路线")
    @Transactional
    public void addRouteAreas(String mapLayerId, List<Long> areaIds) {
        mapLayerDao.addAreas(makeParms(mapLayerId, areaIds, AreaKinds.RouteArea.getIndex()));
    }

    @ServiceMethod(id = "baseinfo.mapLayer.removeRouteArea", pid = "baseinfo.mapLayer", name = "移除路线")
    @Transactional
    public void removeRouteArea(String mapLayerId, long areaId) {
        mapLayerDao.removeArea(mapLayerId, areaId, AreaKinds.RouteArea.getIndex());
    }

    public List<PoiInfo> getPois(String mapLayerId) {
        List<PoiInfo> result = new ArrayList<PoiInfo>();
        List<Long> areas = mapLayerDao.getPois(mapLayerId);
        for (Long id : areas) {
            PoiInfo info = poiService.fetchInfo(id);
            result.add(info);
        }
        return result;
    }

    @ServiceMethod(id = "baseinfo.mapLayer.addPois", pid = "baseinfo.mapLayer", name = "添加兴趣点")
    @Transactional
    public void addPois(String mapLayerId, List<Long> areaIds) {
        mapLayerDao.addAreas(makeParms(mapLayerId, areaIds, AreaKinds.Poi.getIndex()));
    }

    @ServiceMethod(id = "baseinfo.mapLayer.removePoi", pid = "baseinfo.mapLayer", name = "移除兴趣点")
    @Transactional
    public void removePoi(String mapLayerId, long areaId) {
        mapLayerDao.removeArea(mapLayerId, areaId, AreaKinds.Poi.getIndex());
    }

    public void setVisible(String mapLayerId, boolean visible) {
        mapLayerDao.setVisible(mapLayerId, visible);
    }
}
