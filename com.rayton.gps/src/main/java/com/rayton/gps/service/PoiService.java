package com.rayton.gps.service;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.areaInDevice.IAreaInDeviceDao;
import com.rayton.gps.dao.baseinfo.poi.IPoiDao;
import com.rayton.gps.dao.baseinfo.poi.Poi;
import com.rayton.gps.dao.baseinfo.poi.PoiInfo;
import com.rayton.gps.util.enums.AreaKinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PoiService {
    private final byte areaKind = (byte) AreaKinds.Poi.getIndex();
    @Autowired
    private IPoiDao poiDao;
    @Autowired
    private IAreaInDeviceDao areaInDeviceDao;

    public Page<PoiInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
        int total = poiDao.queryPageCount(companyId, filter);
        Page<PoiInfo> query = new Page<PoiInfo>();
        query.total = total;

        if (total > 0) {
            List<PoiInfo> rows = poiDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            query.rows.addAll(rows);


        }

        return query;
    }

    public Page<PoiInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
        int total = poiDao.searchPageCount(companyId, filter);
        Page<PoiInfo> page = new Page<PoiInfo>();
        page.total = total;

        if (total > 0) {
            List<PoiInfo> rows = poiDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            page.rows.addAll(rows);
        }
        return page;
    }

    public PoiInfo fetchInfo(long id) {
        PoiInfo info = poiDao.fetchInfo(id);


        return info;
    }

    @ServiceMethod(id = "baseinfo.poi.create", pid = "baseinfo.poi", name = "创建新的兴趣点")
    @Transactional
    public void create(Poi poi) {


        poiDao.create(poi);
    }

    @ServiceMethod(id = "baseinfo.poi.update", pid = "baseinfo.poi", name = "更新兴趣点")
    @Transactional
    public void update(Poi poi) {


        int rows = poiDao.update(poi);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    public Poi fetch(long id) {
        Poi poi = poiDao.fetch(id);


        return poi;
    }

    @ServiceMethod(id = "baseinfo.poi.delete", pid = "baseinfo.poi", name = "删除兴趣点")
    @Transactional
    public void delete(long id) {
        poiDao.delete(id);
        areaInDeviceDao.deleteAreaInDevice(id, areaKind);
        areaInDeviceDao.deleteAreaInMaplayer(id, areaKind);
    }

    public boolean exist(String name, String companyId, long id) {
        return poiDao.existOutId(name, companyId, id);
    }

    public boolean exist(String name, String companyId) {
        return poiDao.exist(name, companyId);
    }
}
