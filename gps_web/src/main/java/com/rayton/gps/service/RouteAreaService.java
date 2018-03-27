package com.rayton.gps.service;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.cache.AreaCatcherCache;
import com.rayton.gps.cache.SynchronizerCache;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.areaInDevice.AreaInDeviceInfo;
import com.rayton.gps.dao.baseinfo.areaInDevice.IAreaInDeviceDao;
import com.rayton.gps.dao.baseinfo.routeArea.IRouteAreaDao;
import com.rayton.gps.dao.baseinfo.routeArea.RouteArea;
import com.rayton.gps.dao.baseinfo.routeArea.RouteAreaInfo;
import com.rayton.gps.dao.baseinfo.sectionArea.ISectionAreaDao;
import com.rayton.gps.dao.baseinfo.sectionArea.SectionAreaInfo;
import com.rayton.gps.dao.instruct.DeviceInAreaInfo;
import com.rayton.gps.util.KeyValue;
import com.rayton.gps.util.enums.AreaActions;
import com.rayton.gps.util.enums.AreaKinds;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 路线区域服务类
 *
 * @author yangzs
 */
@Service
public class RouteAreaService {
    private final byte areaKind = (byte) AreaKinds.RouteArea.getIndex();
    @Autowired
    private IRouteAreaDao routeAreaDao;
    @Autowired
    private ISectionAreaDao sectionAreaDao;
    @Autowired
    private IAreaInDeviceDao areaInDeviceDao;

    public Page<RouteAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
        int total = routeAreaDao.queryPageCount(companyId, filter);
        Page<RouteAreaInfo> query = new Page<RouteAreaInfo>();
        query.total = total;

        if (total > 0) {
            List<RouteAreaInfo> rows = routeAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize,
                    pageSize);

            for (RouteAreaInfo info : rows) {
                // RouteAreaInfo info = new RouteAreaInfo();
                // info.setId(dto.id);
                // info.setName(dto.name);
                // info.setDeviceCatch(dto.deviceCatch);
                // info.setRemark(dto.remark);

                info.setSections(routeAreaDao.assignedSections(info.getId()));
                if (info.getSections() != null) {
                    for (SectionAreaInfo s : info.getSections()) {
                        // SectionAreaInfo i = new SectionAreaInfo();
                        // i.setId(s.id);
                        // i.setName(s.name);
                        // i.setRemark(s.remark);

                        s.setPoints(sectionAreaDao.fetchSectionPoint(s.getId()));
                        // if (s.getPoints() != null) {
                        //     for (SectionPoint p : s.getPoints()) {
                        //         // SectionPoint sp = new SectionPoint();
                        //         // sp.setId(p.id);
                        //         // sp.setIndex(p.index);
                        //         // sp.setLat(p.lat);
                        //         // sp.setLng(p.lng);
                        //         // sp.setSectionId(p.sectionId);
                        //
                        //         s.getPoints().add(p);
                        //     }
                        // }

                        // info.getSections().add(s);
                    }
                }


            }
            query.rows.addAll(rows);
        }

        return query;
    }

    public Page<RouteAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
        int total = routeAreaDao.searchPageCount(companyId, filter);
        Page<RouteAreaInfo> search = new Page<RouteAreaInfo>();
        search.total = total;

        if (total > 0) {
            List<RouteAreaInfo> rows = routeAreaDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize,
                    pageSize);
            for (RouteAreaInfo dto : rows) {
                // RouteAreaInfo info = new RouteAreaInfo();
                // info.setId(dto.id);
                // info.setName(dto.name);
                // info.setDeviceCatch(dto.deviceCatch);
                // info.setRemark(dto.remark);

                dto.setSections(routeAreaDao.assignedSections(dto.getId()));
                if (dto.getSections() != null) {
                    for (SectionAreaInfo s : dto.getSections()) {
                        // SectionAreaInfo i = new SectionAreaInfo();
                        // i.setId(s.id);
                        // i.setName(s.name);
                        // i.setRemark(s.remark);

                        s.setPoints(sectionAreaDao.fetchSectionPoint(s.getId()));
                        // if (s.points != null) {
                        //     for (SectionPointDto p : s.points) {
                        //         SectionPoint sp = new SectionPoint();
                        //         sp.setId(p.id);
                        //         sp.setIndex(p.index);
                        //         sp.setLat(p.lat);
                        //         sp.setLng(p.lng);
                        //         sp.setSectionId(p.sectionId);
                        //
                        //         i.getPoints().add(sp);
                        //     }
                        // }

                        // dto.getSections().add(s);
                    }
                }


            }
            search.rows.addAll(rows);
        }
        return search;
    }


    @RequiresPermissions("baseinfo.routeArea.create")
    @ServiceMethod(id = "baseinfo.routeArea.create", pid = "baseinfo.routeArea", name = "创建新的路线")
    @Transactional
    public void create(RouteArea routeArea) {
        // RouteAreaDto dto = new RouteAreaDto();
        // dto.id = routeArea.getId();
        // dto.companyId = routeArea.getCompanyId();
        // dto.name = routeArea.getName();
        // dto.deviceCatch = routeArea.isDeviceCatch();
        // dto.flag = routeArea.getFlag();
        // dto.startTime = routeArea.getStartTime();
        // dto.endTime = routeArea.getEndTime();
        // dto.remark = routeArea.getRemark();

        routeAreaDao.create(routeArea);
    }

    public RouteArea fetch(long id) {
        RouteArea routeArea = routeAreaDao.fetch(id);
        // RouteArea routeArea = new RouteArea();
        // routeArea.setId(dto.id);
        // routeArea.setCompanyId(dto.companyId);
        // routeArea.setName(dto.name);
        // routeArea.setDeviceCatch(dto.deviceCatch);
        // routeArea.setFlag(dto.flag);
        // routeArea.setStartTime(dto.startTime);
        // routeArea.setEndTime(dto.endTime);
        // routeArea.setRemark(dto.remark);
        // routeArea.setEditTime(dto.editTime);

        return routeArea;
    }


    @RequiresPermissions("baseinfo.routeArea.update")
    @ServiceMethod(id = "baseinfo.routeArea.update", pid = "baseinfo.routeArea", name = "修改路线")
    @Transactional
    public void update(String unid, String user, RouteArea routeArea) {
        RouteArea old = routeAreaDao.fetch(routeArea.getId());

        // RouteAreaDto dto = new RouteAreaDto();
        // dto.id = routeArea.getId();
        // dto.companyId = routeArea.getCompanyId();
        // dto.name = routeArea.getName();
        // dto.deviceCatch = routeArea.isDeviceCatch();
        // dto.flag = routeArea.getFlag();
        // dto.startTime = routeArea.getStartTime();
        // dto.endTime = routeArea.getEndTime();
        // dto.remark = routeArea.getRemark();
        // dto.editTime = routeArea.getEditTime();

        int rows = routeAreaDao.update(routeArea);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);

        List<String> numbers = areaInDeviceDao.findDevice(routeArea.getId(), areaKind);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();

            info.setAreaId(routeArea.getId());
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (old.isDeviceCatch() && !routeArea.isDeviceCatch()) {// 由终端计算变成平台计算
                AreaCatcherCache.bind(number, routeArea.getId(), areaKind);

                info.setAction((byte) AreaActions.Remove.getIndex());
                areaInDeviceDao.log(info.getId(), number, routeArea.getId(), areaKind, (byte) AreaActions.Remove
                        .getIndex(), unid, user);
                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else if (!old.isDeviceCatch() && routeArea.isDeviceCatch()) {// 由平台计算变成终端计算
                AreaCatcherCache.remove(routeArea.getId(), areaKind);

                info.setAction((byte) AreaActions.Append.getIndex());
                areaInDeviceDao.addVehicle(number, routeArea.getId(), areaKind);
                areaInDeviceDao.log(info.getId(), number, routeArea.getId(), areaKind, (byte) AreaActions.Append
                        .getIndex(), unid, user);
                SynchronizerCache.put(info);

                info.setAction((byte) AreaActions.Edit.getIndex());
                SynchronizerCache.put(info);
            } else {
                if (!routeArea.isDeviceCatch())
                    AreaCatcherCache.refresh(routeArea.getId(), areaKind);

                info.setAction((byte) AreaActions.Edit.getIndex());
                areaInDeviceDao.log(info.getId(), number, routeArea.getId(), areaKind, (byte) AreaActions.Update
                        .getIndex(), unid, user);
                SynchronizerCache.put(info);
            }
        }

    }


    @RequiresPermissions("baseinfo.routeArea.delete")
    @ServiceMethod(id = "baseinfo.routeArea.delete", pid = "baseinfo.routeArea", name = "删除路线")
    @Transactional
    public void delete(String unid, String user, long id) {
        RouteArea route = routeAreaDao.fetch(id);
        List<String> numbers = areaInDeviceDao.findDevice(id, areaKind);

        routeAreaDao.delete(id);
        routeAreaDao.deleteRouteSection(id);
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

            if (route != null) {
                areaInDeviceDao.removeVehicle(number, id, areaKind);
                if (route.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(),
                            unid, user);
            }

            SynchronizerCache.put(info);
            AreaCatcherCache.unbind(number, id, areaKind);
        }
    }

    public boolean exist(String name, String companyId, long id) {
        return routeAreaDao.existOutId(name, companyId, id);
    }

    public boolean exist(String name, String companyId) {
        return routeAreaDao.exist(name, companyId);
    }

    public List<SectionAreaInfo> assignedSections(String companyId, long routeId) {
        List<SectionAreaInfo> sections = routeAreaDao.assignedSections(routeId);
        // List<SectionAreaInfo> sections = new ArrayList<SectionAreaInfo>();
        for (SectionAreaInfo dto : sections) {
            // SectionAreaInfo info = new SectionAreaInfo();
            // info.setId(dto.id);
            // info.setName(dto.name);
            // info.setRemark(dto.remark);

            dto.setPoints(sectionAreaDao.fetchSectionPoint(dto.getId()));
            // if (dto.points != null) {
            //     for (SectionPointDto rpd : dto.points) {
            //         SectionPoint rp = new SectionPoint();
            //         rp.setId(rpd.id);
            //         rp.setLat(rpd.lat);
            //         rp.setLng(rpd.lng);
            //         rp.setSectionId(rpd.sectionId);
            //         rp.setIndex(rpd.index);
            //
            //         info.getPoints().add(rp);
            //     }
            // }

            // sections.add(dto);
        }

        return sections;
    }


    @RequiresPermissions("baseinfo.routeArea.addSections")
    @ServiceMethod(id = "baseinfo.routeArea.addSections", pid = "baseinfo.routeArea", name = "路线绑定路段")
    @Transactional
    public void addSections(long routeId, List<Long> list) {
        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (Long id : list) {
            KeyValue row = new KeyValue();
            row.setKey(routeId);
            row.setValue(id);

            rows.add(row);
        }
        routeAreaDao.addSections(rows);
    }


    @RequiresPermissions("baseinfo.routeArea.removeSection")
    @ServiceMethod(id = "baseinfo.routeArea.removeSection", pid = "baseinfo.routeArea", name = "路线解除路段")
    @Transactional
    public void removeSection(long routeId, long sectionId) {
        routeAreaDao.removeSection(routeId, sectionId);
    }

    public Page<AreaInDeviceInfo> assignedVehicles(long routeAreaId, int pageIndex, int pageSize) {
        int total = areaInDeviceDao.assignedPageVehiclesCount(routeAreaId, areaKind);
        Page<AreaInDeviceInfo> query = new Page<AreaInDeviceInfo>();
        query.total = total;

        if (total > 0) {
            List<AreaInDeviceInfo> rows = areaInDeviceDao.assignedPageVehiclesDetail(routeAreaId, areaKind,
                    (pageIndex - 1) * pageSize, pageSize);
            query.rows.addAll(rows);
        }
        return query;
    }


    @RequiresPermissions("baseinfo.routeArea.addVehicles")
    @ServiceMethod(id = "baseinfo.routeArea.addVehicles", pid = "baseinfo.routeArea", name = "路线绑定车辆")
    @Transactional
    public void addVehicles(String unid, String user, long routeAreaId, List<String> numbers) {
        RouteArea route = routeAreaDao.fetch(routeAreaId);
        for (String number : numbers) {
            // 更新指令同步器
            DeviceInAreaInfo info = new DeviceInAreaInfo();
            info.setAction((byte) AreaActions.Append.getIndex());
            info.setAreaId(routeAreaId);
            info.setAreaType(areaKind);
            info.setDeviceNumber(number);
            info.setId(UUID.randomUUID().toString());
            info.setSendTime(new Date());
            info.setUnid(unid);
            info.setUser(user);

            if (route != null) {
                areaInDeviceDao.addVehicle(number, routeAreaId, areaKind);
                if (route.isDeviceCatch())
                    areaInDeviceDao.log(info.getId(), number, routeAreaId, areaKind, (byte) AreaActions.Append
                            .getIndex(), unid, user);
            }
            SynchronizerCache.put(info);
            AreaCatcherCache.bind(number, routeAreaId, areaKind);
        }
    }

    @RequiresPermissions("baseinfo.routeArea.removeVehicle")
    @ServiceMethod(id = "baseinfo.routeArea.removeVehicle", pid = "baseinfo.routeArea", name = "路线解除车辆")
    @Transactional
    public void removeVehicle(String unid, String user, long routeAreaId, String number) {
        RouteArea route = routeAreaDao.fetch(routeAreaId);
        // 更新指令同步器
        DeviceInAreaInfo info = new DeviceInAreaInfo();
        info.setAction((byte) AreaActions.Remove.getIndex());
        info.setAreaId(routeAreaId);
        info.setAreaType(areaKind);
        info.setDeviceNumber(number);
        info.setId(UUID.randomUUID().toString());
        info.setSendTime(new Date());
        info.setUnid(unid);
        info.setUser(user);

        if (route != null) {
            areaInDeviceDao.removeVehicle(number, routeAreaId, areaKind);
            if (route.isDeviceCatch())
                areaInDeviceDao.log(info.getId(), number, routeAreaId, areaKind, (byte) AreaActions.Remove.getIndex()
                        , unid, user);
        }
        SynchronizerCache.put(info);
        AreaCatcherCache.unbind(number, routeAreaId, areaKind);
    }

    public RouteAreaInfo fetchInfo(Long id) {
        RouteAreaInfo dto = routeAreaDao.fetchInfo(id);
        // RouteAreaInfo info = new RouteAreaInfo();
        // info.setId(dto.id);
        // info.setName(dto.name);
        // info.setDeviceCatch(dto.deviceCatch);
        // info.setRemark(dto.remark);

        dto.setSections(routeAreaDao.assignedSections(id));
        if (dto.getSections() != null) {
            for (SectionAreaInfo s : dto.getSections()) {
                // SectionAreaInfo i = new SectionAreaInfo();
                // i.setId(s.id);
                // i.setName(s.name);
                // i.setRemark(s.remark);

                s.setPoints(sectionAreaDao.fetchSectionPoint(s.getId()));
                // if (s.getPoints() != null) {
                //     for (SectionPoint p : s.getPoints()) {
                //         SectionPoint sp = new SectionPoint();
                //         sp.setId(p.id);
                //         sp.setIndex(p.index);
                //         sp.setLat(p.lat);
                //         sp.setLng(p.lng);
                //         sp.setSectionId(p.sectionId);
                //
                //         i.getPoints().add(sp);
                //     }
                // }

                // info.getSections().add(s);
            }
        }

        return dto;
    }

}
