package com.rayton.gps.service.area;

import com.rayton.gps.aop.Log;
import com.rayton.gps.cache.AreaCatcherCache;
import com.rayton.gps.cache.SynchronizerCache;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.areaInDevice.AreaInDeviceInfo;
import com.rayton.gps.dao.baseinfo.areaInDevice.IAreaInDeviceDao;
import com.rayton.gps.dao.baseinfo.rectangleArea.IRectangleAreaDao;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleArea;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleAreaInfo;
import com.rayton.gps.dao.instruct.DeviceInAreaInfo;
import com.rayton.gps.util.Errors;
import com.rayton.gps.util.enums.AreaActions;
import com.rayton.gps.util.enums.AreaKinds;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 矩形区域服务类
 *
 * @author yangzs
 */
@Service
public class RectangleAreaService {
    private final byte areaKind = (byte) AreaKinds.RectangleArea.getIndex();
    @Autowired
    private IRectangleAreaDao rectangleAreaDao;
    @Autowired
    private IAreaInDeviceDao areaInDeviceDao;

    public Page<RectangleAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
        int total = rectangleAreaDao.queryPageCount(companyId, filter);
        Page<RectangleAreaInfo> query = new Page<RectangleAreaInfo>();
        query.total = total;
        if (total > 0) {
            List<RectangleAreaInfo> rows = rectangleAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) *
                    pageSize, pageSize);
            query.rows.addAll(rows);

            // for (RectangleAreaInfoDto dto : rows) {
            //     RectangleAreaInfo info = new RectangleAreaInfo();
            //     info.setId(dto.id);
            //     info.setName(dto.name);
            //     info.setUllat(dto.ullat);
            //     info.setUllng(dto.ullng);
            //     info.setBrlat(dto.brlat);
            //     info.setBrlng(dto.brlng);
            //     info.setDeviceCatch(dto.deviceCatch);
            //     info.setRemark(dto.remark);
            //
            //     query.rows.add(info);
            // }
        }

        return query;
    }

    public Page<RectangleAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
        int total = rectangleAreaDao.searchPageCount(companyId, filter);
        Page<RectangleAreaInfo> page = new Page<RectangleAreaInfo>();
        page.total = total;
        if (total > 0) {
            List<RectangleAreaInfo> rows = rectangleAreaDao.searchPageDetail(companyId, filter, pageIndex, pageSize);
            page.rows.addAll(rows);
            // for (RectangleAreaInfoDto dto : rows) {
            //     RectangleAreaInfo info = new RectangleAreaInfo();
            //     info.setId(dto.id);
            //     info.setName(dto.name);
            //     info.setUllat(dto.ullat);
            //     info.setUllng(dto.ullng);
            //     info.setBrlat(dto.brlat);
            //     info.setBrlng(dto.brlng);
            //     info.setDeviceCatch(dto.deviceCatch);
            //     info.setRemark(dto.remark);
            //
            //     page.rows.add(info);
            // }
        }
        return page;
    }


    @RequiresPermissions("baseinfo.rectangleArea.create")
    @Log(id = "baseinfo.rectangleArea.create", pid = "baseinfo.rectangleArea", name = "创建新的矩形区域")
    @Transactional
    public void create(RectangleArea rectangleArea) {
        // RectangleAreaDto dto = new RectangleAreaDto();
        // dto.id = rectangleArea.getId();
        // dto.companyId = rectangleArea.getCompanyId();
        // dto.name = rectangleArea.getName();
        // dto.deviceCatch = rectangleArea.isDeviceCatch();
        // dto.flag = rectangleArea.getFlag();
        // dto.ullat = rectangleArea.getUllat();
        // dto.ullng = rectangleArea.getUllng();
        // dto.brlat = rectangleArea.getBrlat();
        // dto.brlng = rectangleArea.getBrlng();
        // dto.maxSpeed = rectangleArea.getMaxSpeed();
        // dto.overspeedSeconds = rectangleArea.getOverspeedSeconds();
        // dto.startTime = rectangleArea.getStartTime();
        // dto.endTime = rectangleArea.getEndTime();
        // dto.remark = rectangleArea.getRemark();

        rectangleAreaDao.create(rectangleArea);
    }

    public RectangleArea fetch(long id) {
        RectangleArea rectangleArea = rectangleAreaDao.fetch(id);
        // RectangleArea rectangleArea = new RectangleArea();
        // rectangleArea.setId(dto.id);
        // rectangleArea.setCompanyId(dto.companyId);
        // rectangleArea.setName(dto.name);
        // rectangleArea.setDeviceCatch(dto.deviceCatch);
        // rectangleArea.setFlag(dto.flag);
        // rectangleArea.setUllat(dto.ullat);
        // rectangleArea.setUllng(dto.ullng);
        // rectangleArea.setBrlat(dto.brlat);
        // rectangleArea.setBrlng(dto.brlng);
        // rectangleArea.setMaxSpeed(dto.maxSpeed);
        // rectangleArea.setOverspeedSeconds(dto.overspeedSeconds);
        // rectangleArea.setStartTime(dto.startTime);
        // rectangleArea.setEndTime(dto.endTime);
        // rectangleArea.setRemark(dto.remark);
        // rectangleArea.setEditTime(dto.editTime);

        return rectangleArea;
    }


    @RequiresPermissions("baseinfo.rectangleArea.update")
    @Log(id = "baseinfo.rectangleArea.update", pid = "baseinfo.rectangleArea", name = "修改矩形区域")
    @Transactional
    public void update(String unid, String user, RectangleArea rectangleArea) {
        RectangleArea old = rectangleAreaDao.fetch(rectangleArea.getId());

        // RectangleAreaDto dto = new RectangleAreaDto();
        // dto.id = rectangleArea.getId();
        // dto.companyId = rectangleArea.getCompanyId();
        // dto.name = rectangleArea.getName();
        // dto.deviceCatch = rectangleArea.isDeviceCatch();
        // dto.flag = rectangleArea.getFlag();
        // dto.ullat = rectangleArea.getUllat();
        // dto.ullng = rectangleArea.getUllng();
        // dto.brlat = rectangleArea.getBrlat();
        // dto.brlng = rectangleArea.getBrlng();
        // dto.maxSpeed = rectangleArea.getMaxSpeed();
        // dto.overspeedSeconds = rectangleArea.getOverspeedSeconds();
        // dto.startTime = rectangleArea.getStartTime();
        // dto.endTime = rectangleArea.getEndTime();
        // dto.remark = rectangleArea.getRemark();
        // dto.editTime = rectangleArea.getEditTime();

        int rows = rectangleAreaDao.update(rectangleArea);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);

        List<String> numbers = areaInDeviceDao.findDevice(rectangleArea.getId(), areaKind);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();

            info.setAreaId(rectangleArea.getId());
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (old.isDeviceCatch() && !rectangleArea.isDeviceCatch()) {// 由终端计算变成平台计算
                AreaCatcherCache.bind(number, rectangleArea.getId(), areaKind);

                info.setAction((byte) AreaActions.Remove.getIndex());
                areaInDeviceDao.log(info.getId(), number, rectangleArea.getId(), areaKind, (byte) AreaActions.Remove
                        .getIndex(), unid, user);
                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else if (!old.isDeviceCatch() && rectangleArea.isDeviceCatch()) {// 由平台计算变成终端计算
                AreaCatcherCache.remove(rectangleArea.getId(), areaKind);

                info.setAction((byte) AreaActions.Append.getIndex());
                areaInDeviceDao.addVehicle(number, rectangleArea.getId(), areaKind);
                areaInDeviceDao.log(info.getId(), number, rectangleArea.getId(), areaKind, (byte) AreaActions.Append
                        .getIndex(), unid, user);
                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else {
                if (!rectangleArea.isDeviceCatch())
                    AreaCatcherCache.refresh(rectangleArea.getId(), areaKind);

                info.setAction((byte) AreaActions.Edit.getIndex());
                areaInDeviceDao.log(info.getId(), number, rectangleArea.getId(), areaKind, (byte) AreaActions.Update
                        .getIndex(), unid, user);
                SynchronizerCache.put(info);
            }
        }

    }


    @RequiresPermissions("baseinfo.rectangleArea.delete")
    @Log(id = "baseinfo.rectangleArea.delete", pid = "baseinfo.rectangleArea", name = "删除矩形区域")
    @Transactional
    public void delete(String unid, String user, long id) {
        RectangleArea rectangle = rectangleAreaDao.fetch(id);
        List<String> numbers = areaInDeviceDao.findDevice(id, areaKind);

        rectangleAreaDao.delete(id);
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

            if (rectangle != null) {
                areaInDeviceDao.removeVehicle(number, id, areaKind);
                if (rectangle.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(),
                            unid, user);
            }

            SynchronizerCache.put(info);
            AreaCatcherCache.unbind(number, id, areaKind);
        }
    }

    public boolean exist(String name, String companyId, long id) {
        return rectangleAreaDao.existOutId(name, companyId, id);
    }

    public boolean exist(String name, String companyId) {
        return rectangleAreaDao.exist(name, companyId);
    }

    public Page<AreaInDeviceInfo> assignedVehicles(long rectangleAreaId, int pageIndex, int pageSize) {
        int total = areaInDeviceDao.assignedPageVehiclesCount(rectangleAreaId, areaKind);
        Page<AreaInDeviceInfo> query = new Page<AreaInDeviceInfo>();
        query.total = total;
        if (total > 0) {
            List<AreaInDeviceInfo> rows = areaInDeviceDao.assignedPageVehiclesDetail(rectangleAreaId, areaKind,
                    (pageIndex - 1) * pageSize, pageSize);

            query.rows.addAll(rows);
        }
        return query;
    }


    @RequiresPermissions("baseinfo.rectangleArea.addVehicles")
    @Log(id = "baseinfo.rectangleArea.addVehicles", pid = "baseinfo.rectangleArea", name = "矩形区域绑定车辆")
    @Transactional
    public void addVehicles(String unid, String user, long rectangleAreaId, List<String> numbers) {
        RectangleArea rectangle = rectangleAreaDao.fetch(rectangleAreaId);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();
            info.setAction((byte) AreaActions.Append.getIndex());
            info.setAreaId(rectangleAreaId);
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (rectangle != null) {
                areaInDeviceDao.addVehicle(number, rectangleAreaId, areaKind);
                if (rectangle.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, rectangleAreaId, areaKind, (byte) AreaActions.Append
                            .getIndex(), unid, user);
            }
            SynchronizerCache.put(info);
            AreaCatcherCache.bind(number, rectangleAreaId, areaKind);
        }
    }


    @RequiresPermissions("baseinfo.rectangleArea.removeVehicle")
    @Log(id = "baseinfo.rectangleArea.removeVehicle", pid = "baseinfo.rectangleArea", name = "矩形区域解除车辆")
    @Transactional
    public void removeVehicle(String unid, String user, long rectangleAreaId, String number) {
        RectangleArea rectangle = rectangleAreaDao.fetch(rectangleAreaId);
        // 更新指令同步器
        DeviceInAreaInfo info = new DeviceInAreaInfo();
        info.setAction((byte) AreaActions.Remove.getIndex());
        info.setAreaId(rectangleAreaId);
        info.setAreaType(areaKind);
        info.setDeviceNumber(number);
        info.setId(UUID.randomUUID().toString());
        info.setSendTime(new Date());
        info.setUnid(unid);

        if (rectangle != null) {
            areaInDeviceDao.removeVehicle(number, rectangleAreaId, areaKind);
            if (rectangle.isDeviceCatch())
                areaInDeviceDao.log(info.getId(), number, rectangleAreaId, areaKind, (byte) AreaActions.Remove
                        .getIndex(), unid, user);
        }
        SynchronizerCache.put(info);
        AreaCatcherCache.unbind(number, rectangleAreaId, areaKind);
    }

    public RectangleAreaInfo fetchInfo(Long id) {
        RectangleArea dto = rectangleAreaDao.fetch(id);
        RectangleAreaInfo info = new RectangleAreaInfo();
        BeanUtils.copyProperties(dto, info);
        // info.setId(dto.id);
        // info.setName(dto.name);
        // info.setUllat(dto.ullat);
        // info.setUllng(dto.ullng);
        // info.setBrlat(dto.brlat);
        // info.setBrlng(dto.brlng);
        // info.setDeviceCatch(dto.deviceCatch);
        // info.setRemark(dto.remark);

        return info;
    }

}
