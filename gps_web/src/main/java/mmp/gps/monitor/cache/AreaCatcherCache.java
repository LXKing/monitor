package mmp.gps.monitor.cache;

import mmp.gps.common.enums.AreaKinds;
import mmp.gps.domain.area.*;
import mmp.gps.domain.locate.Latest;
import mmp.gps.monitor.area.*;
import mmp.gps.monitor.service.AreaCatcherService;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 区域捕捉器缓存
 */
@Component
public class AreaCatcherCache {

    private static Map<String, AreaCatcher> areaCatchers = new ConcurrentHashMap<>();
    private static Map<String, Map<String, AreaCatcher>> deviceCatchers = new ConcurrentHashMap<>();

    private static AreaCatcherService areaCatcherService;

    // 初始化
    @Autowired(required = true)
    public AreaCatcherCache(@Qualifier("areaCatcherService") AreaCatcherService areaCatcherService) {
        AreaCatcherCache.areaCatcherService = areaCatcherService;
        // init();
        // areaCatchers = new ConcurrentHashMap<>();
        // deviceCatchers = new ConcurrentHashMap<>();

        List<AreaInfo> areaInfos = new ArrayList<>();
        // 圆形区域
        areaInfos.addAll(areaCatcherService.getCircles());


        // 矩形区域
        areaInfos.addAll(areaCatcherService.getRectangles());
        areaInfos.addAll(areaCatcherService.getPolygons());
        areaInfos.addAll(areaCatcherService.getRoutes());


        areaInfos.forEach(areaInfo -> AreaCatcherCache.bind(areaInfo.getDeviceNumber(), areaInfo.getAreaId(), areaInfo.getAreaType()));
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
            map = new ConcurrentHashMap<>();
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
