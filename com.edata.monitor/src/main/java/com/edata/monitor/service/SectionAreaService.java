package com.edata.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.ISectionAreaDao;
import com.edata.monitor.dao.baseinfo.SectionAreaDto;
import com.edata.monitor.dao.baseinfo.SectionAreaInfoDto;
import com.edata.monitor.dao.baseinfo.SectionPointDto;
import com.edata.monitor.domain.baseinfo.SectionArea;
import com.edata.monitor.domain.baseinfo.SectionAreaInfo;
import com.edata.monitor.domain.baseinfo.SectionPoint;

/**
 * 路线区域服务类
 * 
 * @author yangzs
 *
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
			List<SectionAreaInfoDto> rows = sectionAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (SectionAreaInfoDto dto : rows) {
				SectionAreaInfo info = new SectionAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setRemark(dto.remark);

				dto.points = sectionAreaDao.fetchSectionPoint(dto.id);
				for (SectionPointDto rpd : dto.points) {
					SectionPoint rp = new SectionPoint();
					rp.setId(rpd.id);
					rp.setLat(rpd.lat);
					rp.setLng(rpd.lng);
					rp.setSectionId(rpd.sectionId);
					rp.setIndex(rpd.index);

					info.getPoints().add(rp);
				}

				query.rows.add(info);
			}
		}

		return query;
	}

	public Page<SectionAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
		int total = sectionAreaDao.searchPageCount(companyId, filter);
		Page<SectionAreaInfo> search = new Page<SectionAreaInfo>();
		search.total = total;

		if (total > 0) {
			List<SectionAreaInfoDto> rows = sectionAreaDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (SectionAreaInfoDto dto : rows) {
				SectionAreaInfo info = new SectionAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setRemark(dto.remark);

				dto.points = sectionAreaDao.fetchSectionPoint(dto.id);
				for (SectionPointDto rpd : dto.points) {
					SectionPoint rp = new SectionPoint();
					rp.setId(rpd.id);
					rp.setLat(rpd.lat);
					rp.setLng(rpd.lng);
					rp.setSectionId(rpd.sectionId);
					rp.setIndex(rpd.index);

					info.getPoints().add(rp);
				}

				search.rows.add(info);
			}
		}
		return search;
	}

	@ServiceMethod(id = "baseinfo.sectionArea.create", pid = "baseinfo.sectionArea", name = "创建新的路段")
	@Transactional
	public void create(SectionArea sectionArea) {
		SectionAreaDto dto = new SectionAreaDto();

		dto.id = sectionArea.getId();
		dto.companyId = sectionArea.getCompanyId();
		dto.name = sectionArea.getName();
		dto.width = sectionArea.getWidth();
		dto.flag = sectionArea.getFlag();
		dto.maxSeconds = sectionArea.getMaxSeconds();
		dto.minSeconds = sectionArea.getMinSeconds();
		dto.maxSpeed = sectionArea.getMaxSpeed();
		dto.overspeedSeconds = sectionArea.getOverspeedSeconds();
		dto.remark = sectionArea.getRemark();

		sectionAreaDao.create(dto);

		if (sectionArea.getPoints() != null) {
			for (SectionPoint item : sectionArea.getPoints()) {
				SectionPointDto p = new SectionPointDto();
				p.sectionId = dto.id;
				p.lat = item.getLat();
				p.lng = item.getLng();
				p.index = item.getIndex();

				dto.points.add(p);
			}
			sectionAreaDao.createSectionPoint(dto.points);
		}
	}

	public SectionArea fetch(long id) {
		SectionAreaDto dto = sectionAreaDao.fetch(id);
		SectionArea sectionArea = new SectionArea();
		sectionArea.setId(dto.id);
		sectionArea.setCompanyId(dto.companyId);
		sectionArea.setName(dto.name);
		sectionArea.setFlag(dto.flag);
		sectionArea.setWidth(dto.width);
		sectionArea.setMaxSeconds(dto.maxSeconds);
		sectionArea.setMinSeconds(dto.minSeconds);
		sectionArea.setMaxSpeed(dto.maxSpeed);
		sectionArea.setOverspeedSeconds(dto.overspeedSeconds);
		sectionArea.setRemark(dto.remark);
		sectionArea.setEditTime(dto.editTime);

		dto.points = sectionAreaDao.fetchSectionPoint(dto.id);
		if (dto.points != null) {
			for (SectionPointDto item : dto.points) {
				SectionPoint rp = new SectionPoint();
				rp.setId(item.id);
				rp.setSectionId(item.sectionId);
				rp.setLat(item.lat);
				rp.setLng(item.lng);
				rp.setIndex(item.index);

				sectionArea.getPoints().add(rp);
			}
		}

		return sectionArea;
	}

	@ServiceMethod(id = "baseinfo.sectionArea.update", pid = "baseinfo.sectionArea", name = "修改路段")
	@Transactional
	public void update(SectionArea sectionArea) {
		SectionAreaDto dto = new SectionAreaDto();

		dto.id = sectionArea.getId();
		dto.companyId = sectionArea.getCompanyId();
		dto.name = sectionArea.getName();
		dto.width = sectionArea.getWidth();
		dto.flag = sectionArea.getFlag();
		dto.maxSeconds = sectionArea.getMaxSeconds();
		dto.minSeconds = sectionArea.getMinSeconds();
		dto.maxSpeed = sectionArea.getMaxSpeed();
		dto.overspeedSeconds = sectionArea.getOverspeedSeconds();
		dto.remark = sectionArea.getRemark();
		dto.editTime = sectionArea.getEditTime();

		sectionAreaDao.deleteSectionPoint(dto.id);

		if (sectionArea.getPoints() != null) {
			for (SectionPoint item : sectionArea.getPoints()) {
				SectionPointDto p = new SectionPointDto();

				p.sectionId = dto.id;
				p.lat = item.getLat();
				p.lng = item.getLng();
				p.index = item.getIndex();

				dto.points.add(p);
			}
			sectionAreaDao.createSectionPoint(dto.points);
		}

		int rows = sectionAreaDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	@ServiceMethod(id = "baseinfo.sectionArea.delete", pid = "baseinfo.sectionArea", name = "删除路段")
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
