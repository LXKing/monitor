package mmp.gps.monitor.service.area;

import mmp.gps.common.util.Errors;
import mmp.gps.common.util.Page;
import mmp.gps.domain.area.SectionArea;
import mmp.gps.domain.area.SectionAreaInfo;
import mmp.gps.domain.area.SectionPoint;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.dao.ISectionAreaDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 路线区域服务类
 */
@Service
public class SectionAreaService {
    @Autowired
    private ISectionAreaDao sectionAreaDao;

    public Page<SectionAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
        int total = sectionAreaDao.queryPageCount(companyId, filter);
        Page<SectionAreaInfo> query = new Page<SectionAreaInfo>();
        query.total = total;

        if (total > 0) {
            List<SectionAreaInfo> rows = sectionAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            for (SectionAreaInfo dto : rows) {
                // SectionAreaInfo info = new SectionAreaInfo();
                // info.setId(dto.id);
                // info.setName(dto.name);
                // info.setRemark(dto.remark);

                dto.setPoints(sectionAreaDao.fetchSectionPoint(dto.getId()));
                // for (SectionPointDto rpd : dto.points) {
                //     SectionPoint rp = new SectionPoint();
                //     rp.setId(rpd.id);
                //     rp.setLat(rpd.lat);
                //     rp.setLng(rpd.lng);
                //     rp.setSectionId(rpd.sectionId);
                //     rp.setIndex(rpd.index);
                //
                //     info.getPoints().add(rp);
                // }

                query.rows.add(dto);
            }
        }

        return query;
    }

    public Page<SectionAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
        int total = sectionAreaDao.searchPageCount(companyId, filter);
        Page<SectionAreaInfo> search = new Page<SectionAreaInfo>();
        search.total = total;

        if (total > 0) {
            List<SectionAreaInfo> rows = sectionAreaDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
            for (SectionAreaInfo dto : rows) {
                // SectionAreaInfo info = new SectionAreaInfo();
                // info.setId(dto.id);
                // info.setName(dto.name);
                // info.setRemark(dto.remark);

                dto.setPoints(sectionAreaDao.fetchSectionPoint(dto.getId()));
                // for (SectionPointDto rpd : dto.points) {
                //     SectionPoint rp = new SectionPoint();
                //     rp.setId(rpd.id);
                //     rp.setLat(rpd.lat);
                //     rp.setLng(rpd.lng);
                //     rp.setSectionId(rpd.sectionId);
                //     rp.setIndex(rpd.index);
                //
                //     info.getPoints().add(rp);
                // }

                search.rows.add(dto);
            }
        }
        return search;
    }


    @RequiresPermissions("baseinfo.sectionArea.create")
    @Log(name = "创建新的路段")
    @Transactional
    public void create(SectionArea sectionArea) {
        // SectionAreaDto dto = new SectionAreaDto();

        // dto.id = sectionArea.getId();
        // dto.companyId = sectionArea.getCompanyId();
        // dto.name = sectionArea.getName();
        // dto.width = sectionArea.getWidth();
        // dto.flag = sectionArea.getFlag();
        // dto.maxSeconds = sectionArea.getMaxSeconds();
        // dto.minSeconds = sectionArea.getMinSeconds();
        // dto.maxSpeed = sectionArea.getMaxSpeed();
        // dto.overspeedSeconds = sectionArea.getOverspeedSeconds();
        // dto.remark = sectionArea.getRemark();

        sectionAreaDao.create(sectionArea);

        if (sectionArea.getPoints() != null) {


            for (SectionPoint item : sectionArea.getPoints()) {
                // SectionPoint p = new SectionPoint();
                item.setSectionId(sectionArea.getId());
                // p.setLat(); = item.getLat();
                // p.lng = item.getLng();
                // p.index = item.getIndex();

                // sectionArea.getPoints().add(item);
            }


            sectionAreaDao.createSectionPoint(sectionArea.getPoints());
        }
    }

    public SectionArea fetch(long id) {
        SectionArea dto = sectionAreaDao.fetch(id);
        // SectionArea sectionArea = new SectionArea();
        // sectionArea.setId(dto.id);
        // sectionArea.setCompanyId(dto.companyId);
        // sectionArea.setName(dto.name);
        // sectionArea.setFlag(dto.flag);
        // sectionArea.setWidth(dto.width);
        // sectionArea.setMaxSeconds(dto.maxSeconds);
        // sectionArea.setMinSeconds(dto.minSeconds);
        // sectionArea.setMaxSpeed(dto.maxSpeed);
        // sectionArea.setOverspeedSeconds(dto.overspeedSeconds);
        // sectionArea.setRemark(dto.remark);
        // sectionArea.setEditTime(dto.editTime);

        dto.setPoints(sectionAreaDao.fetchSectionPoint(dto.getId()));
        // if (dto.points != null) {
        //     for (SectionPointDto item : dto.points) {
        //         SectionPoint rp = new SectionPoint();
        //         rp.setId(item.id);
        //         rp.setSectionId(item.sectionId);
        //         rp.setLat(item.lat);
        //         rp.setLng(item.lng);
        //         rp.setIndex(item.index);
        //
        //         sectionArea.getPoints().add(rp);
        //     }
        // }

        return dto;
    }


    @RequiresPermissions("baseinfo.sectionArea.update")
    @Log(name = "修改路段")
    @Transactional
    public void update(SectionArea sectionArea) {
        // SectionAreaDto dto = new SectionAreaDto();
        //
        // dto.id = sectionArea.getId();
        // dto.companyId = sectionArea.getCompanyId();
        // dto.name = sectionArea.getName();
        // dto.width = sectionArea.getWidth();
        // dto.flag = sectionArea.getFlag();
        // dto.maxSeconds = sectionArea.getMaxSeconds();
        // dto.minSeconds = sectionArea.getMinSeconds();
        // dto.maxSpeed = sectionArea.getMaxSpeed();
        // dto.overspeedSeconds = sectionArea.getOverspeedSeconds();
        // dto.remark = sectionArea.getRemark();
        // dto.editTime = sectionArea.getEditTime();

        sectionAreaDao.deleteSectionPoint(sectionArea.getId());

        if (sectionArea.getPoints() != null) {
            for (SectionPoint item : sectionArea.getPoints()) {
                item.setSectionId(sectionArea.getId());
                // SectionPointDto p = new SectionPointDto();

                // p.sectionId = dto.id;
                // p.lat = item.getLat();
                // p.lng = item.getLng();
                // p.index = item.getIndex();

                // dto.points.add(p);
            }
            sectionAreaDao.createSectionPoint(sectionArea.getPoints());
        }

        int rows = sectionAreaDao.update(sectionArea);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }


    @RequiresPermissions("baseinfo.sectionArea.delete")
    @Log(name = "删除路段")
    @Transactional
    public void delete(long id) {
        sectionAreaDao.delete(id);
        sectionAreaDao.deleteSectionPoint(id);
    }

    public boolean exist(String name, String companyId, long id) {
        return sectionAreaDao.existOutId(name, companyId, id);
    }

    public boolean exist(String name, String companyId) {
        return sectionAreaDao.exist(name, companyId);
    }

}
