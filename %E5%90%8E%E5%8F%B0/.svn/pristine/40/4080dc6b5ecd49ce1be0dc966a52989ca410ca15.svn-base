package com.rayton.gps.cache;

import com.rayton.gps.common.DateFormats;
import com.rayton.gps.common.LatLng;
import com.rayton.gps.dao.baseinfo.circleArea.CircleArea;
import com.rayton.gps.dao.baseinfo.polygonArea.PolygonArea;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleArea;
import com.rayton.gps.dao.baseinfo.routeArea.RouteArea;
import com.rayton.gps.dao.baseinfo.sectionArea.SectionArea;
import com.rayton.gps.dao.baseinfo.sectionArea.SectionPoint;
import com.rayton.gps.dao.cache.DeviceInstruct;
import com.rayton.gps.dao.cache.DeviceInstructSender;
import com.rayton.gps.dao.instruct.DeviceInAreaInfo;
import com.rayton.gps.service.SyncInstructService;
import com.rayton.gps.util.JsonMapper;
import com.rayton.gps.util.enums.AreaActions;
import com.rayton.gps.util.enums.AreaKinds;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 指令同步器缓存
 *
 * @author yangzs
 */
@Component
public class SynchronizerCache {
    private static SyncInstructService syncInstructService;
    private static Map<String, DeviceInstructSender> senders = null;

    @Autowired(required = true)
    public SynchronizerCache(@Qualifier("syncInstructService") SyncInstructService syncInstructService) {
        SynchronizerCache.syncInstructService = syncInstructService;
        init();
    }

    public static void sendNext(String deviceNumber) {
        DeviceInstructSender sender = get(deviceNumber);
        if (sender == null)
            return;
        sender.sendNext();
    }

    private static DeviceInstructSender get(String deviceNumber) {
        if (deviceNumber == null)
            return null;
        return senders.get(deviceNumber);
    }

    private synchronized static void init() {
        if (senders != null)
            return;
        senders = new ConcurrentHashMap<String, DeviceInstructSender>();

        List<DeviceInAreaInfo> areas = syncInstructService.loadUnfinishedAreaInDevice();
        for (DeviceInAreaInfo info : areas) {
            DeviceInstructSender sender = senders.get(info.getDeviceNumber());
            if (sender == null) {
                sender = new DeviceInstructSender();
                sender.setDeviceNumber(info.getDeviceNumber());
                sender.setSent(new Date());
                senders.put(info.getDeviceNumber(), sender);
            }

            put(info);
        }
    }

    public static void put(DeviceInAreaInfo info) {
        if (info.getAreaType() == AreaKinds.CircleArea.getIndex()) {
            circleAreaSync(info);
        } else if (info.getAreaType() == AreaKinds.RectangleArea.getIndex()) {
            rectangleAreaSync(info);
        } else if (info.getAreaType() == AreaKinds.PolygonArea.getIndex()) {
            polygonAreaSync(info);
        } else if (info.getAreaType() == AreaKinds.RouteArea.getIndex()) {
            routeAreaSync(info);
        }
    }

    /**
     * 路线同步
     */
    private static void routeAreaSync(DeviceInAreaInfo info) {
        if (info.getAction() == AreaActions.Remove.getIndex()) {
            // {"list":["12","23"]}
            Map<String, List<Long>> map = new HashMap<String, List<Long>>();
            List<Long> list = new ArrayList<Long>();
            list.add(info.getAreaId());
            map.put("list", list);
            DeviceInstructSender sender = get(info.getDeviceNumber());
            if (sender == null) {
                sender = new DeviceInstructSender();
                sender.setDeviceNumber(info.getDeviceNumber());
                sender.setSent(new Date());
                senders.put(info.getDeviceNumber(), sender);
            }

            DeviceInstruct deviceInstruct = new DeviceInstruct();
            deviceInstruct.setSerialNumber(info.getId());
            deviceInstruct.setCommand("8607");
            deviceInstruct.setDeviceNumber(info.getDeviceNumber());
            deviceInstruct.setName("删除路线");
            deviceInstruct.setParams(JsonMapper.toJson(map));
            deviceInstruct.setUnid(info.getUnid());
            deviceInstruct.setUser(info.getUser());
            deviceInstruct.setSendTime(info.getSendTime());
            sender.getInstructs().put(info.getId(), deviceInstruct);
        }
        RouteArea routeArea = syncInstructService.getRouteArea(info.getAreaId());
        if (routeArea == null)
            return;
        if (!routeArea.isDeviceCatch())
            return;
        List<SectionArea> SectionAreas = syncInstructService.getSectionIds(info.getAreaId());
        // {"areaId":"23","flag":"435","startTime":"2015-09-29 10:00:00","endTime":"2016-09-29 10:00:00",
        // "sections":[{"pointId":"34","sectionId":"23","lat":"23.34534","lng":"114.546456","width":"30",
        // "flag":"232","maxTime":"300","minTime":"120","maxSpeed":"120","overspeedSeconds":"5"}]}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("areaId", routeArea.getId());
        map.put("flag", routeArea.getFlag());
        map.put("startTime", DateFormats.toDateTimeString(routeArea.getStartTime()));
        map.put("endTime", DateFormats.toDateTimeString(routeArea.getStartTime()));

        List<Map<String, Object>> sections = new ArrayList<Map<String, Object>>();
        for (SectionArea item : SectionAreas) {
            for (SectionPoint p : item.getPoints()) {
                Map<String, Object> point = new HashMap<String, Object>();
                point.put("pointId", p.getId());
                point.put("sectionId", p.getSectionId());
                point.put("lat", p.getLat());
                point.put("lng", p.getLng());
                point.put("width", item.getWidth());
                point.put("flag", item.getFlag());
                point.put("maxTime", item.getMaxSeconds());
                point.put("minTime", item.getMinSeconds());
                point.put("maxSpeed", item.getMaxSpeed());
                point.put("overspeedSeconds", item.getOverspeedSeconds());
                sections.add(point);
            }
        }

        map.put("sections", sections);

        DeviceInstructSender sender = get(info.getDeviceNumber());
        if (sender == null) {
            sender = new DeviceInstructSender();
            sender.setDeviceNumber(info.getDeviceNumber());
            sender.setSent(new Date());
            senders.put(info.getDeviceNumber(), sender);
        }

        DeviceInstruct deviceInstruct = new DeviceInstruct();
        deviceInstruct.setSerialNumber(info.getId());
        deviceInstruct.setCommand("8606");
        deviceInstruct.setDeviceNumber(info.getDeviceNumber());
        deviceInstruct.setName("设置路线");
        deviceInstruct.setParams(JsonMapper.toJson(map));
        deviceInstruct.setUnid(info.getUnid());
        deviceInstruct.setUser(info.getUser());
        deviceInstruct.setSendTime(info.getSendTime());

        sender.getInstructs().put(info.getId(), deviceInstruct);
    }

    /**
     * 多边形区域同步
     */
    private static void polygonAreaSync(DeviceInAreaInfo info) {
        if (info.getAction() == AreaActions.Remove.getIndex()) {
            // {"list":["2","1"]}
            Map<String, List<Long>> map = new HashMap<String, List<Long>>();
            List<Long> list = new ArrayList<Long>();
            list.add(info.getAreaId());
            map.put("list", list);
            DeviceInstructSender sender = get(info.getDeviceNumber());
            if (sender == null) {
                sender = new DeviceInstructSender();
                sender.setDeviceNumber(info.getDeviceNumber());
                sender.setSent(new Date());
                senders.put(info.getDeviceNumber(), sender);
            }

            DeviceInstruct deviceInstruct = new DeviceInstruct();
            deviceInstruct.setSerialNumber(info.getId());
            deviceInstruct.setCommand("8605");
            deviceInstruct.setDeviceNumber(info.getDeviceNumber());
            deviceInstruct.setName("删除多边形区域");
            deviceInstruct.setParams(JsonMapper.toJson(map));
            deviceInstruct.setUnid(info.getUnid());
            deviceInstruct.setUser(info.getUser());
            deviceInstruct.setSendTime(info.getSendTime());
            sender.getInstructs().put(info.getId(), deviceInstruct);
        }
        PolygonArea polygonArea = syncInstructService.getPolygonArea(info.getAreaId());
        if (polygonArea == null)
            return;
        if (!polygonArea.isDeviceCatch())
            return;
        // {"areaId":"23","flag":"35445","startTime":"2015-09-29 00:00:00","endTime":"2015-10-29 00:00:00",
        // "maxSpeed":"120","overspeedSeconds":"5","points":[{"lat":"23.4364557","lng":"113.45465"}]}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("areaId", polygonArea.getId());
        map.put("flag", polygonArea.getFlag());
        map.put("startTime", DateFormats.toDateTimeString(polygonArea.getStartTime()));
        map.put("endTime", DateFormats.toDateTimeString(polygonArea.getStartTime()));
        map.put("maxSpeed", polygonArea.getMaxSpeed());
        map.put("overspeedSeconds", polygonArea.getOverspeedSeconds());

        List<Map<String, Object>> points = new ArrayList<Map<String, Object>>();
        for (LatLng item : polygonArea.getPoints()) {
            Map<String, Object> point = new HashMap<String, Object>();
            point.put("lat", item.lat);
            point.put("lng", item.lng);
            points.add(point);
        }

        map.put("points", points);

        DeviceInstructSender sender = get(info.getDeviceNumber());
        if (sender == null) {
            sender = new DeviceInstructSender();
            sender.setDeviceNumber(info.getDeviceNumber());
            sender.setSent(new Date());
            senders.put(info.getDeviceNumber(), sender);
        }

        DeviceInstruct deviceInstruct = new DeviceInstruct();
        deviceInstruct.setSerialNumber(info.getId());
        deviceInstruct.setCommand("8604");
        deviceInstruct.setDeviceNumber(info.getDeviceNumber());
        deviceInstruct.setName("设置多边形区域");
        deviceInstruct.setParams(JsonMapper.toJson(map));
        deviceInstruct.setUnid(info.getUnid());
        deviceInstruct.setUser(info.getUser());
        deviceInstruct.setSendTime(info.getSendTime());

        sender.getInstructs().put(info.getId(), deviceInstruct);
    }

    /**
     * 矩形区域同步
     */
    private static void rectangleAreaSync(DeviceInAreaInfo info) {
        if (info.getAction() == AreaActions.Remove.getIndex()) {
            // {"list":["2","1"]}
            Map<String, List<Long>> map = new HashMap<String, List<Long>>();
            List<Long> list = new ArrayList<Long>();
            list.add(info.getAreaId());
            map.put("list", list);
            DeviceInstructSender sender = get(info.getDeviceNumber());
            if (sender == null) {
                sender = new DeviceInstructSender();
                sender.setDeviceNumber(info.getDeviceNumber());
                sender.setSent(new Date());
                senders.put(info.getDeviceNumber(), sender);
            }

            DeviceInstruct deviceInstruct = new DeviceInstruct();
            deviceInstruct.setSerialNumber(info.getId());
            deviceInstruct.setCommand("8603");
            deviceInstruct.setDeviceNumber(info.getDeviceNumber());
            deviceInstruct.setName("删除矩形区域");
            deviceInstruct.setParams(JsonMapper.toJson(map));
            deviceInstruct.setUnid(info.getUnid());
            deviceInstruct.setUser(info.getUser());
            deviceInstruct.setSendTime(info.getSendTime());
            sender.getInstructs().put(info.getId(), deviceInstruct);
        }
        RectangleArea rectangleArea = syncInstructService.getRectangleArea(info.getAreaId());
        if (rectangleArea == null)
            return;
        if (!rectangleArea.isDeviceCatch())
            return;
        // {"action":"0","areas":[{"areaId":"1","flag":"123","ulLat":"23.322523","ulLng":"114.435443",
        // "brLat":"24.354435","brLng":"115.354353","startTime":"2015-09-28 00:00:00","endTime":"2015-10-28
        // 00:00:00","maxSpeed":"120","overspeedSeconds":"5"}]}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("action", info.getAction());

        Map<String, Object> areas = new HashMap<String, Object>();
        areas.put("areaId", rectangleArea.getId());
        areas.put("flag", rectangleArea.getFlag());
        areas.put("ulLat", rectangleArea.getUllat());
        areas.put("ulLng", rectangleArea.getUllng());
        areas.put("brLat", rectangleArea.getBrlat());
        areas.put("brLng", rectangleArea.getBrlng());
        areas.put("startTime", DateFormats.toDateTimeString(rectangleArea.getStartTime()));
        areas.put("endTime", DateFormats.toDateTimeString(rectangleArea.getStartTime()));
        areas.put("maxSpeed", rectangleArea.getMaxSpeed());
        areas.put("overspeedSeconds", rectangleArea.getOverspeedSeconds());

        List<Object> list = new ArrayList<Object>();
        list.add(areas);
        map.put("areas", list);
        DeviceInstructSender sender = get(info.getDeviceNumber());
        if (sender == null) {
            sender = new DeviceInstructSender();
            sender.setDeviceNumber(info.getDeviceNumber());
            sender.setSent(new Date());
            senders.put(info.getDeviceNumber(), sender);
        }

        DeviceInstruct deviceInstruct = new DeviceInstruct();
        deviceInstruct.setSerialNumber(info.getId());
        deviceInstruct.setCommand("8602");
        deviceInstruct.setDeviceNumber(info.getDeviceNumber());
        deviceInstruct.setName("设置矩形区域");
        deviceInstruct.setParams(JsonMapper.toJson(map));
        deviceInstruct.setUnid(info.getUnid());
        deviceInstruct.setUser(info.getUser());
        deviceInstruct.setSendTime(info.getSendTime());

        sender.getInstructs().put(info.getId(), deviceInstruct);
    }

    /**
     * 圆形区域同步
     */
    private static void circleAreaSync(DeviceInAreaInfo info) {
        if (info.getAction() == AreaActions.Remove.getIndex()) {
            // {"list":["1"]}
            Map<String, List<Long>> map = new HashMap<String, List<Long>>();
            List<Long> list = new ArrayList<Long>();
            list.add(info.getAreaId());
            map.put("list", list);
            DeviceInstructSender sender = get(info.getDeviceNumber());
            if (sender == null) {
                sender = new DeviceInstructSender();
                sender.setDeviceNumber(info.getDeviceNumber());
                sender.setSent(new Date());
                senders.put(info.getDeviceNumber(), sender);
            }

            DeviceInstruct deviceInstruct = new DeviceInstruct();
            deviceInstruct.setSerialNumber(info.getId());
            deviceInstruct.setCommand("8601");
            deviceInstruct.setDeviceNumber(info.getDeviceNumber());
            deviceInstruct.setName("删除圆形区域");
            deviceInstruct.setParams(JsonMapper.toJson(map));
            deviceInstruct.setUnid(info.getUnid());
            deviceInstruct.setUser(info.getUser());
            deviceInstruct.setSendTime(info.getSendTime());
            sender.getInstructs().put(info.getId(), deviceInstruct);
        }
        CircleArea circleArea = syncInstructService.getCircleArea(info.getAreaId());
        if (circleArea == null)
            return;
        if (!circleArea.isDeviceCatch())
            return;
        // {"action":"1","areas":[{"areaId":"1","flag":"1","lat":"23.234234","lng":"113.534546","radius":"500",
        // "startTime":"15-09-24 00:00:00","endTime":"15-09-25 00:00:00","maxSpeed":"80","overspeedSeconds":"10"}]
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("action", info.getAction());

        Map<String, Object> areas = new HashMap<String, Object>();
        areas.put("areaId", circleArea.getId());
        areas.put("flag", circleArea.getFlag());
        areas.put("lat", circleArea.getLat());
        areas.put("lng", circleArea.getLng());
        areas.put("radius", circleArea.getRadius());
        areas.put("startTime", DateFormats.toDateTimeString(circleArea.getStartTime()));
        areas.put("endTime", DateFormats.toDateTimeString(circleArea.getStartTime()));
        areas.put("maxSpeed", circleArea.getMaxSpeed());
        areas.put("overspeedSeconds", circleArea.getOverspeedSeconds());

        List<Object> list = new ArrayList<Object>();
        list.add(areas);
        map.put("areas", list);
        DeviceInstructSender sender = get(info.getDeviceNumber());
        if (sender == null) {
            sender = new DeviceInstructSender();
            sender.setDeviceNumber(info.getDeviceNumber());
            sender.setSent(new Date());
            senders.put(info.getDeviceNumber(), sender);
        }

        DeviceInstruct deviceInstruct = new DeviceInstruct();
        deviceInstruct.setSerialNumber(info.getId());
        deviceInstruct.setCommand("8600");
        deviceInstruct.setDeviceNumber(info.getDeviceNumber());
        deviceInstruct.setName("设置圆形区域");
        deviceInstruct.setParams(JsonMapper.toJson(map));
        deviceInstruct.setUnid(info.getUnid());
        deviceInstruct.setUser(info.getUser());
        deviceInstruct.setSendTime(info.getSendTime());

        sender.getInstructs().put(info.getId(), deviceInstruct);
    }

    public static void remove(String deviceNumber, String serialNumber) {
        DeviceInstructSender sender = get(deviceNumber);
        if (sender == null)
            return;
        if (sender.getInstructs().remove(serialNumber) != null) {
            syncInstructService.updateLog(serialNumber);
        }
    }
}
