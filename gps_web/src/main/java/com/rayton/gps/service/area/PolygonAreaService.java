package com.rayton.gps.service.area;

import com.rayton.gps.aop.Log;
import com.rayton.gps.cache.AreaCatcherCache;
import com.rayton.gps.cache.SynchronizerCache;
import com.rayton.gps.common.LatLng;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.areaInDevice.AreaInDeviceInfo;
import com.rayton.gps.dao.baseinfo.areaInDevice.IAreaInDeviceDao;
import com.rayton.gps.dao.baseinfo.polygonArea.IPolygonAreaDao;
import com.rayton.gps.dao.baseinfo.polygonArea.PolygonArea;
import com.rayton.gps.dao.baseinfo.polygonArea.PolygonAreaInfo;
import com.rayton.gps.dao.instruct.DeviceInAreaInfo;
import com.rayton.gps.util.Errors;
import com.rayton.gps.util.KeyValue;
import com.rayton.gps.util.enums.AreaActions;
import com.rayton.gps.util.enums.AreaKinds;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 多边形区域服务类
 *
 * @author yangzs
 */
@Service
public class PolygonAreaService {
    private final byte areaKind = (byte) AreaKinds.PolygonArea.getIndex();
    @Autowired
    private IPolygonAreaDao polygonAreaDao;
    @Autowired
    private IAreaInDeviceDao areaInDeviceDao;

    public Page<PolygonAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
        int total = polygonAreaDao.queryPageCount(companyId, filter);
        Page<PolygonAreaInfo> query = new Page<PolygonAreaInfo>();
        query.total = total;

        if (total > 0) {
            List<PolygonAreaInfo> rows = polygonAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            rows.forEach(polygonAreaInfo -> {
                List<LatLng> points = polygonAreaDao.fetchPolygonPoint(polygonAreaInfo.getId());
                polygonAreaInfo.setPoints(points);

                query.rows.add(polygonAreaInfo);

            });
            // for (PolygonAreaInfoDto dto : rows) {
            //     PolygonAreaInfo info = new PolygonAreaInfo();
            //     info.setId(dto.id);
            //     info.setName(dto.name);
            //     info.setDeviceCatch(dto.deviceCatch);
            //     info.setRemark(dto.remark);
            //
            //     List<LatLng> points = polygonAreaDao.fetchPolygonPoint(dto.id);
            //     info.setPoints(points);
            //
            //     query.rows.add(info);
            // }
        }

        return query;
    }

    public Page<PolygonAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
        int total = polygonAreaDao.searchPageCount(companyId, filter);
        Page<PolygonAreaInfo> search = new Page<PolygonAreaInfo>();
        search.total = total;

        if (total > 0) {
            List<PolygonAreaInfo> rows = polygonAreaDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);

            rows.forEach(polygonAreaInfo -> {
                List<LatLng> points = polygonAreaDao.fetchPolygonPoint(polygonAreaInfo.getId());
                polygonAreaInfo.setPoints(points);

                search.rows.add(polygonAreaInfo);

            });
            // for (PolygonAreaInfoDto dto : rows) {
            //     PolygonAreaInfo info = new PolygonAreaInfo();
            //     info.setId(dto.id);
            //     info.setName(dto.name);
            //     info.setDeviceCatch(dto.deviceCatch);
            //     info.setRemark(dto.remark);
            //
            //     List<LatLng> points = polygonAreaDao.fetchPolygonPoint(dto.id);
            //     info.setPoints(points);
            //
            //     search.rows.add(info);
            // }
        }
        return search;
    }


    @RequiresPermissions("baseinfo.polygonArea.create")
    @Log(name = "创建新的多边形区域")
    @Transactional
    public void create(PolygonArea polygonArea) {
        // PolygonAreaDto dto = new PolygonAreaDto();
        // dto.id = polygonArea.getId();
        // dto.companyId = polygonArea.getCompanyId();
        // dto.name = polygonArea.getName();
        // dto.deviceCatch = polygonArea.isDeviceCatch();
        // dto.flag = polygonArea.getFlag();
        // dto.maxSpeed = polygonArea.getMaxSpeed();
        // dto.overspeedSeconds = polygonArea.getOverspeedSeconds();
        // dto.startTime = polygonArea.getStartTime();
        // dto.endTime = polygonArea.getEndTime();
        // dto.remark = polygonArea.getRemark();
        // dto.points = polygonArea.getPoints();

        polygonAreaDao.create(polygonArea);

        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (LatLng p : polygonArea.getPoints()) {
            KeyValue row = new KeyValue();
            row.setKey(polygonArea.getId());
            row.setValue(p);

            rows.add(row);
        }
        polygonAreaDao.createPolygonPoint(rows);
    }

    public PolygonArea fetch(long id) {
        PolygonArea polygonArea = polygonAreaDao.fetch(id);
        // PolygonArea polygonArea = new PolygonArea();
        // polygonArea.setId(dto.id);
        // polygonArea.setCompanyId(dto.companyId);
        // polygonArea.setName(dto.name);
        // polygonArea.setDeviceCatch(dto.deviceCatch);
        // polygonArea.setFlag(dto.flag);
        // polygonArea.setMaxSpeed(dto.maxSpeed);
        // polygonArea.setOverspeedSeconds(dto.overspeedSeconds);
        // polygonArea.setStartTime(dto.startTime);
        // polygonArea.setEndTime(dto.endTime);
        // polygonArea.setRemark(dto.remark);
        // polygonArea.setEditTime(dto.editTime);

        List<LatLng> points = polygonAreaDao.fetchPolygonPoint(id);
        polygonArea.setPoints(points);

        return polygonArea;
    }

    @RequiresPermissions("baseinfo.polygonArea.update")
    @Log(name = "修改多边形区域")
    @Transactional
    public void update(String unid, String user, PolygonArea polygonArea) {
        PolygonArea old = polygonAreaDao.fetch(polygonArea.getId());

        // PolygonAreaDto dto = new PolygonAreaDto();
        // dto.id = polygonArea.getId();
        // dto.companyId = polygonArea.getCompanyId();
        // dto.name = polygonArea.getName();
        // dto.deviceCatch = polygonArea.isDeviceCatch();
        // dto.flag = polygonArea.getFlag();
        // dto.maxSpeed = polygonArea.getMaxSpeed();
        // dto.overspeedSeconds = polygonArea.getOverspeedSeconds();
        // dto.startTime = polygonArea.getStartTime();
        // dto.endTime = polygonArea.getEndTime();
        // dto.remark = polygonArea.getRemark();
        // dto.editTime = polygonArea.getEditTime();
        //
        // dto.points = polygonArea.getPoints();

        int count = polygonAreaDao.update(polygonArea);


        if (count != 1)
            throw new RuntimeException(Errors.anotherEdited);

        polygonAreaDao.deletePolygonPoint(polygonArea.getId());

        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (LatLng p : polygonArea.getPoints()) {
            KeyValue row = new KeyValue();
            row.setKey(polygonArea.getId());
            row.setValue(p);

            rows.add(row);
        }
        polygonAreaDao.createPolygonPoint(rows);

        List<String> numbers = areaInDeviceDao.findDevice(polygonArea.getId(), areaKind);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();

            info.setAreaId(old.getId());
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (old.isDeviceCatch() && !polygonArea.isDeviceCatch()) {// 由终端计算变成平台计算
                AreaCatcherCache.bind(number, old.getId(), areaKind);

                info.setAction((byte) AreaActions.Remove.getIndex());
                areaInDeviceDao.log(info.getId(), number, old.getId(), areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else if (!old.isDeviceCatch() && polygonArea.isDeviceCatch()) {// 由平台计算变成终端计算
                AreaCatcherCache.remove(old.getId(), areaKind);

                info.setAction((byte) AreaActions.Append.getIndex());
                areaInDeviceDao.addVehicle(number, old.getId(), areaKind);
                areaInDeviceDao.log(info.getId(), number, old.getId(), areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else {
                if (!polygonArea.isDeviceCatch())
                    AreaCatcherCache.refresh(old.getId(), areaKind);

                info.setAction((byte) AreaActions.Edit.getIndex());
                areaInDeviceDao.log(info.getId(), number, old.getId(), areaKind, (byte) AreaActions.Update.getIndex(), unid, user);
                SynchronizerCache.put(info);
            }
        }

    }

    @RequiresPermissions("baseinfo.polygonArea.delete")
    @Log(name = "删除多边形区域")
    @Transactional
    public void delete(String unid, String user, long id) {
        PolygonArea polygon = polygonAreaDao.fetch(id);
        List<String> numbers = areaInDeviceDao.findDevice(id, areaKind);
        polygonAreaDao.delete(id);
        polygonAreaDao.deletePolygonPoint(id);
        areaInDeviceDao.deleteAreaInDevice(id, areaKind);
        areaInDeviceDao.deleteAreaInMaplayer(id, areaKind);

        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();
            info.setAction((byte) AreaActions.Remove.getIndex());
            info.setAreaId(id);
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (polygon != null) {
                areaInDeviceDao.removeVehicle(number, id, areaKind);
                if (polygon.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
            }

            SynchronizerCache.put(info);
            AreaCatcherCache.unbind(number, id, areaKind);
        }
    }

    public boolean exist(String name, String companyId, long id) {
        return polygonAreaDao.existOutId(name, companyId, id);
    }

    public boolean exist(String name, String companyId) {
        return polygonAreaDao.exist(name, companyId);
    }

    /**
     * 获取已绑定的车辆
     */
    public Page<AreaInDeviceInfo> assignedVehicles(long polygonAreaId, int pageIndex, int pageSize) {
        int total = areaInDeviceDao.assignedPageVehiclesCount(polygonAreaId, areaKind);
        Page<AreaInDeviceInfo> query = new Page<AreaInDeviceInfo>();
        query.total = total;

        if (total > 0) {
            List<AreaInDeviceInfo> rows = areaInDeviceDao.assignedPageVehiclesDetail(polygonAreaId, areaKind, (pageIndex - 1) * pageSize, pageSize);
            query.rows.addAll(rows);
        }
        return query;
    }

    /**
     * 绑定车辆
     */
    @RequiresPermissions("baseinfo.polygonArea.addVehicles")
    @Log(name = "多边形区域绑定车辆")
    @Transactional
    public void addVehicles(String unid, String user, long polygonAreaId, List<String> numbers) {
        PolygonArea polygon = polygonAreaDao.fetch(polygonAreaId);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();
            info.setAction((byte) AreaActions.Append.getIndex());
            info.setAreaId(polygonAreaId);
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (polygon != null) {
                areaInDeviceDao.addVehicle(number, polygonAreaId, areaKind);
                if (polygon.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, polygonAreaId, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
            }
            SynchronizerCache.put(info);
            AreaCatcherCache.bind(number, polygonAreaId, areaKind);
        }
    }

    /**
     * 解除车辆
     */
    @RequiresPermissions("baseinfo.polygonArea.removeVehicle")
    @Log(name = "多边形区域解除车辆")
    @Transactional
    public void removeVehicle(String unid, String user, long polygonAreaId, String number) {
        PolygonArea polygon = polygonAreaDao.fetch(polygonAreaId);
        // 更新指令同步器
        DeviceInAreaInfo info = new DeviceInAreaInfo();
        info.setAction((byte) AreaActions.Remove.getIndex());
        info.setAreaId(polygonAreaId);
        info.setAreaType(areaKind);
        info.setDeviceNumber(number);
        info.setId(UUID.randomUUID().toString());
        info.setSendTime(new Date());
        info.setUnid(unid);
        info.setUser(user);

        if (polygon != null) {
            areaInDeviceDao.removeVehicle(number, polygonAreaId, areaKind);
            if (polygon.isDeviceCatch())
                areaInDeviceDao.log(info.getId(), number, polygonAreaId, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
        }
        SynchronizerCache.put(info);
        AreaCatcherCache.unbind(number, polygonAreaId, areaKind);
    }

    public PolygonAreaInfo fetchInfo(Long id) {
        PolygonArea dto = polygonAreaDao.fetch(id);

        // dto.setPoints(polygonAreaDao.fetchPolygonPoint(id));

        PolygonAreaInfo info = new PolygonAreaInfo();

        BeanUtils.copyProperties(dto, info);

        info.setPoints(polygonAreaDao.fetchPolygonPoint(id));

        //
        // info.setId(dto.id);
        // info.setName(dto.name);
        // info.setDeviceCatch(dto.deviceCatch);
        // info.setRemark(dto.remark);
        // info.setPoints(dto.points);

        return info;
    }
}
