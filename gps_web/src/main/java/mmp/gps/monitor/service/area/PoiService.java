package mmp.gps.monitor.service.area;

import mmp.gps.common.util.Errors;
import mmp.gps.common.util.Page;
import mmp.gps.common.enums.AreaKinds;
import mmp.gps.domain.poi.Poi;
import mmp.gps.domain.poi.PoiInfo;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.dao.IAreaInDeviceDao;
import mmp.gps.monitor.dao.IPoiDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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


    @RequiresPermissions("baseinfo.poi.create")
    @Log(name = "创建新的兴趣点")
    @Transactional
    public void create(Poi poi) {


        poiDao.create(poi);
    }

    @RequiresPermissions("baseinfo.poi.update")
    @Log(name = "更新兴趣点")
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

    @RequiresPermissions("baseinfo.poi.delete")
    @Log(name = "删除兴趣点")
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
