package com.edata.monitor.cache;

import com.edata.monitor.dao.baseinfo.circleArea.CircleArea;
import com.edata.monitor.dao.baseinfo.polygonArea.PolygonArea;
import com.edata.monitor.dao.baseinfo.rectangleArea.RectangleArea;
import com.edata.monitor.dao.baseinfo.routeArea.RouteArea;
import com.edata.monitor.dao.baseinfo.sectionArea.SectionArea;
import com.edata.monitor.dao.cache.area.*;
import com.edata.monitor.dao.locate.Latest;
import com.edata.monitor.service.AreaCatcherService;
import com.edata.monitor.util.enums.AreaKinds;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 区域捕捉器缓存
 *
 * @author yangzs
 */
@Component
public class AreaCatcherCache {
    private static Map<String, AreaCatcher> areaCatchers = null;
    private static Map<String, Map<String, AreaCatcher>> deviceCatchers = null;

    private static AreaCatcherService areaCatcherService;

    @Autowired(required = true)
    public AreaCatcherCache(@Qualifier("areaCatcherService") AreaCatcherService areaCatcherService) {
        AreaCatcherCache.areaCatcherService = areaCatcherService;
        init();
    }

    private synchronized static void init() {
        areaCatchers = new ConcurrentHashMap<String, AreaCatcher>();
        deviceCatchers = new ConcurrentHashMap<String, Map<String, AreaCatcher>>();

        // 圆形区域
        List<AreaInfo> circles = areaCatcherService.getCircles();
        for (AreaInfo info : circles) {
            bind(info.getDeviceNumber(), info.getAreaId(), info.getAreaType());
        }
        // 矩形区域
        List<AreaInfo> rectangles = areaCatcherService.getRectangles();
        for (AreaInfo info : rectangles) {
            bind(info.getDeviceNumber(), info.getAreaId(), info.getAreaType());
        }
        // 多边形区域
        List<AreaInfo> polygons = areaCatcherService.getPolygons();
        for (AreaInfo info : polygons) {
            bind(info.getDeviceNumber(), info.getAreaId(), info.getAreaType());
        }
        // 路线区域
        List<AreaInfo> routes = areaCatcherService.getRoutes();
        for (AreaInfo info : routes) {
            bind(info.getDeviceNumber(), info.getAreaId(), info.getAreaType());
        }
    }

    /**
     * 区域计算
     *
     * @param latest 最后位置数据
     * @return 返回0表示无报警，返回1表示有区域报警
     */
    public static boolean hasAlarm(Latest latest) {
        String deviceNumber = latest.getDn();
        Map<String, AreaCatcher> map = deviceCatchers.get(deviceNumber);
        if (map == null)
            return false;
        for (AreaCatcher catcher : map.values()) {
            if (catcher.hasAlarm(latest))
                return true;
        }
        return false;
    }

    public static void remove(long areaId, int areaKind) {
        String catcherKey = areaKind + "_" + areaId;
        areaCatchers.remove(catcherKey);
    }

    public static void refresh(long areaId, int areaKind) {
        String catcherKey = areaKind + "_" + areaId;
        areaCatchers.remove(catcherKey);

        if (areaKind == AreaKinds.CircleArea.getIndex()) {
            CircleArea circleArea = areaCatcherService.getCircle(areaId);
            if (circleArea == null || circleArea.isDeviceCatch())
                return;

            CircleAreaCatcher circleAreaCatcher = new CircleAreaCatcher();
            circleAreaCatcher.setCircleArea(circleArea);
            areaCatchers.put(catcherKey, circleAreaCatcher);
        }
        // 矩形区域
        if (areaKind == AreaKinds.RectangleArea.getIndex()) {
            RectangleArea rectangleArea = areaCatcherService.getRectangleArea(areaId);
            if (rectangleArea == null || rectangleArea.isDeviceCatch())
                return;

            RectangleAreaCatcher rectangleAreaCatcher = new RectangleAreaCatcher();
            rectangleAreaCatcher.setRectangleArea(rectangleArea);
            areaCatchers.put(catcherKey, rectangleAreaCatcher);
        }
        // 多边形
        if (areaKind == AreaKinds.PolygonArea.getIndex()) {
            PolygonArea polygonArea = areaCatcherService.getPolygonArea(areaId);
            if (polygonArea == null || polygonArea.isDeviceCatch())
                return;

            PolygonAreaCatcher polygonAreaCatcher = new PolygonAreaCatcher();
            polygonAreaCatcher.setPolygonArea(polygonArea);
            areaCatchers.put(catcherKey, polygonAreaCatcher);
        }
        // 路线
        if (areaKind == AreaKinds.RouteArea.getIndex()) {
            RouteArea routeArea = areaCatcherService.getRouteArea(areaId);
            if (routeArea == null || routeArea.isDeviceCatch())
                return;

            List<SectionArea> sections = areaCatcherService.getSectionArea(areaId);
            RouteAreaCatcher routeAreaCatcher = new RouteAreaCatcher();
            routeAreaCatcher.setRouteArea(routeArea);
            routeAreaCatcher.setSections(sections);
            areaCatchers.put(catcherKey, routeAreaCatcher);
        }

    }

    public static void unbind(String deviceNumber, long areaId, int areaKind) {
        String catcherKey = areaKind + "_" + areaId;
        Map<String, AreaCatcher> map = deviceCatchers.get(deviceNumber);
        if (map != null)
            map.remove(catcherKey);
    }

    public static void bind(String deviceNumber, long areaId, int areaKind) {
        String catcherKey = areaKind + "_" + areaId;
        Map<String, AreaCatcher> map = deviceCatchers.get(deviceNumber);
        if (map == null) {
            map = new ConcurrentHashMap<String, AreaCatcher>();
            deviceCatchers.put(deviceNumber, map);
        }
        // 圆形区域
        if (areaKind == AreaKinds.CircleArea.getIndex()) {
            AreaCatcher catcher = areaCatchers.get(catcherKey);
            if (catcher == null) {
                CircleArea circleArea = areaCatcherService.getCircle(areaId);
                if (circleArea == null)
                    return;
                CircleAreaCatcher circleAreaCatcher = new CircleAreaCatcher();
                circleAreaCatcher.setCircleArea(circleArea);
                catcher = circleAreaCatcher;
                areaCatchers.put(catcherKey, catcher);
            }

            map.put(catcherKey, catcher);
        }
        // 矩形区域
        if (areaKind == AreaKinds.RectangleArea.getIndex()) {
            AreaCatcher catcher = areaCatchers.get(catcherKey);
            if (catcher == null) {
                RectangleArea rectangleArea = areaCatcherService.getRectangleArea(areaId);
                if (rectangleArea == null)
                    return;
                RectangleAreaCatcher rectangleAreaCatcher = new RectangleAreaCatcher();
                rectangleAreaCatcher.setRectangleArea(rectangleArea);
                catcher = rectangleAreaCatcher;
                areaCatchers.put(catcherKey, catcher);
            }

            map.put(catcherKey, catcher);
        }
        // 多边形
        if (areaKind == AreaKinds.PolygonArea.getIndex()) {
            AreaCatcher catcher = areaCatchers.get(catcherKey);
            if (catcher == null) {
                PolygonArea polygonArea = areaCatcherService.getPolygonArea(areaId);
                if (polygonArea == null)
                    return;
                PolygonAreaCatcher polygonAreaCatcher = new PolygonAreaCatcher();
                polygonAreaCatcher.setPolygonArea(polygonArea);
                catcher = polygonAreaCatcher;
                areaCatchers.put(catcherKey, catcher);
            }

            map.put(catcherKey, catcher);
        }
        // 路线
        if (areaKind == AreaKinds.RouteArea.getIndex()) {
            AreaCatcher catcher = areaCatchers.get(catcherKey);
            if (catcher == null) {
                RouteArea routeArea = areaCatcherService.getRouteArea(areaId);
                if (routeArea == null)
                    return;
                List<SectionArea> sections = areaCatcherService.getSectionArea(areaId);
                RouteAreaCatcher routeAreaCatcher = new RouteAreaCatcher();
                routeAreaCatcher.setRouteArea(routeArea);
                routeAreaCatcher.setSections(sections);
                catcher = routeAreaCatcher;
                areaCatchers.put(catcherKey, catcher);
            }

            map.put(catcherKey, catcher);
        }
    }

}
