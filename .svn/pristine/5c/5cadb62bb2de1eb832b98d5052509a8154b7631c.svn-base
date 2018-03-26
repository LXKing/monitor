package com.rayton.gps.service;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.cache.AreaCatcherCache;
import com.rayton.gps.cache.SynchronizerCache;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.areaInDevice.AreaInDeviceInfo;
import com.rayton.gps.dao.baseinfo.areaInDevice.IAreaInDeviceDao;
import com.rayton.gps.dao.baseinfo.circleArea.CircleArea;
import com.rayton.gps.dao.baseinfo.circleArea.CircleAreaInfo;
import com.rayton.gps.dao.baseinfo.circleArea.ICircleAreaDao;
import com.rayton.gps.dao.instruct.DeviceInAreaInfo;
import com.rayton.gps.util.enums.AreaActions;
import com.rayton.gps.util.enums.AreaKinds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CircleAreaService {
    private final byte areaKind = (byte) AreaKinds.CircleArea.getIndex();
    @Autowired
    private ICircleAreaDao circleAreaDao;
    @Autowired
    private IAreaInDeviceDao areaInDeviceDao;

    public Page<CircleAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
        int total = circleAreaDao.queryPageCount(companyId, filter);
        Page<CircleAreaInfo> query = new Page<CircleAreaInfo>();
        query.total = total;

        if (total > 0) {
            List<CircleAreaInfo> rows = circleAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize,
                    pageSize);
            query.rows.addAll(rows);


        }

        return query;
    }

    public Page<CircleAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
        int total = circleAreaDao.searchPageCount(companyId, filter);
        Page<CircleAreaInfo> page = new Page<CircleAreaInfo>();
        page.total = total;

        if (total > 0) {
            List<CircleAreaInfo> rows = circleAreaDao.searchPageDetail(companyId, filter, pageIndex, pageSize);
            page.rows.addAll(rows);


        }
        return page;
    }

    @ServiceMethod(id = "baseinfo.circleArea.create", pid = "baseinfo.circleArea", name = "创建圆形区域")
    @Transactional
    public void create(CircleArea circleArea) {


        circleAreaDao.create(circleArea);
    }

    @ServiceMethod(id = "baseinfo.circleArea.update", pid = "baseinfo.circleArea", name = "修改圆形区域")
    @Transactional
    public void update(String unid, String user, CircleArea circleArea) {
        CircleArea old = circleAreaDao.fetch(circleArea.getId());

        CircleArea dto = new CircleArea();
        BeanUtils.copyProperties(circleArea, dto);


        int rows = circleAreaDao.update(dto);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);

        List<String> numbers = areaInDeviceDao.findDevice(dto.getId(), areaKind);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();

            info.setAreaId(dto.getId());
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (old.isDeviceCatch() && !circleArea.isDeviceCatch()) {// 由终端计算变成平台计算
                AreaCatcherCache.bind(number, dto.getId(), areaKind);

                info.setAction((byte) AreaActions.Remove.getIndex());
                areaInDeviceDao.log(info.getId(), number, dto.getId(), areaKind, (byte) AreaActions.Remove.getIndex()
                        , unid, user);

                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else if (!old.isDeviceCatch() && circleArea.isDeviceCatch()) {// 由平台计算变成终端计算
                AreaCatcherCache.remove(dto.getId(), areaKind);

                info.setAction((byte) AreaActions.Append.getIndex());
                areaInDeviceDao.addVehicle(number, dto.getId(), areaKind);
                areaInDeviceDao.log(info.getId(), number, dto.getId(), areaKind, (byte) AreaActions.Append.getIndex()
                        , unid, user);

                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else {
                if (!circleArea.isDeviceCatch())
                    AreaCatcherCache.refresh(dto.getId(), areaKind);

                info.setAction((byte) AreaActions.Edit.getIndex());
                areaInDeviceDao.log(info.getId(), number, dto.getId(), areaKind, (byte) AreaActions.Update.getIndex()
                        , unid, user);
                SynchronizerCache.put(info);
            }
        }
    }

    public CircleArea fetch(long id) {
        CircleArea circleArea = circleAreaDao.fetch(id);


        return circleArea;
    }

    @ServiceMethod(id = "baseinfo.circleArea.delete", pid = "baseinfo.circleArea", name = "删除圆形区域")
    @Transactional
    public void delete(String unid, String user, long id) {
        CircleArea circle = circleAreaDao.fetch(id);
        List<String> numbers = areaInDeviceDao.findDevice(id, areaKind);

        circleAreaDao.delete(id);
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

            if (circle != null) {
                areaInDeviceDao.removeVehicle(number, id, areaKind);
                if (circle.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(),
                            unid, user);
            }

            SynchronizerCache.put(info);
            AreaCatcherCache.unbind(number, id, areaKind);
        }
    }

    public boolean exist(String name, String companyId, long id) {
        return circleAreaDao.existOutId(name, companyId, id);
    }

    public boolean exist(String name, String companyId) {
        return circleAreaDao.exist(name, companyId);
    }

    /**
     * 获取已绑定的车辆
     */
    public Page<AreaInDeviceInfo> assignedVehicles(long circleAreaId, int pageIndex, int pageSize) {
        int total = areaInDeviceDao.assignedPageVehiclesCount(circleAreaId, areaKind);
        Page<AreaInDeviceInfo> query = new Page<AreaInDeviceInfo>();
        query.total = total;

        if (total > 0) {
            List<AreaInDeviceInfo> rows = areaInDeviceDao.assignedPageVehiclesDetail(circleAreaId, areaKind,
                    (pageIndex - 1) * pageSize, pageSize);

            query.rows.addAll(rows);

        }
        return query;
    }

    /**
     * 绑定车辆
     */
    @ServiceMethod(id = "baseinfo.circleArea.addVehicles", pid = "baseinfo.circleArea", name = "圆形区域绑定车辆")
    @Transactional
    public void addVehicles(String unid, String user, long circleAreaId, List<String> numbers) {
        CircleArea circle = circleAreaDao.fetch(circleAreaId);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();
            info.setAction((byte) AreaActions.Append.getIndex());
            info.setAreaId(circleAreaId);
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (circle != null) {
                areaInDeviceDao.addVehicle(number, circleAreaId, areaKind);
                if (circle.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, circleAreaId, areaKind, (byte) AreaActions.Append
                            .getIndex(), unid, user);
            }
            SynchronizerCache.put(info);
            AreaCatcherCache.bind(number, circleAreaId, areaKind);
        }
    }

    /**
     * 解除车辆
     */
    @ServiceMethod(id = "baseinfo.circleArea.removeVehicle", pid = "baseinfo.circleArea", name = "圆形区域解除车辆")
    @Transactional
    public void removeVehicle(String unid, String user, long circleAreaId, String number) {
        CircleArea circle = circleAreaDao.fetch(circleAreaId);
        // 更新指令同步器
        DeviceInAreaInfo info = new DeviceInAreaInfo();
        info.setAction((byte) AreaActions.Remove.getIndex());
        info.setAreaId(circleAreaId);
        info.setAreaType(areaKind);
        info.setDeviceNumber(number);
        info.setId(UUID.randomUUID().toString());
        info.setSendTime(new Date());
        info.setUnid(unid);
        info.setUser(user);

        if (circle != null) {
            areaInDeviceDao.removeVehicle(number, circleAreaId, areaKind);
            if (circle.isDeviceCatch())
                areaInDeviceDao.log(info.getId(), number, circleAreaId, areaKind, (byte) AreaActions.Remove.getIndex
                        (), unid, user);
        }
        SynchronizerCache.put(info);
        AreaCatcherCache.unbind(number, circleAreaId, areaKind);
    }

    public CircleAreaInfo fetchInfo(Long id) {
        CircleArea dto = circleAreaDao.fetch(id);
        CircleAreaInfo info = new CircleAreaInfo();
        BeanUtils.copyProperties(dto, info);
        // info.setId(dto.id);
        // info.setName(dto.name);
        // info.setLat(dto.lat);
        // info.setLng(dto.lng);
        // info.setRadius(dto.radius);
        // info.setRemark(dto.remark);

        return info;
    }
}
