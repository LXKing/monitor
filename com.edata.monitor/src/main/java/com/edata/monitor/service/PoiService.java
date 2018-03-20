package com.edata.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.IAreaInDeviceDao;
import com.edata.monitor.dao.baseinfo.IPoiDao;
import com.edata.monitor.dao.baseinfo.PoiDto;
import com.edata.monitor.dao.baseinfo.PoiInfoDto;
import com.edata.monitor.domain.baseinfo.Poi;
import com.edata.monitor.domain.baseinfo.PoiInfo;
import com.edata.monitor.util.kind.AreaKinds;

@Service
public class PoiService {
	@Autowired
	private IPoiDao poiDao;
	@Autowired
	private IAreaInDeviceDao areaInDeviceDao;

	private final byte areaKind = (byte) AreaKinds.Poi.getIndex();

	public Page<PoiInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		int total = poiDao.queryPageCount(companyId, filter);
		Page<PoiInfo> query = new Page<PoiInfo>();
		query.total = total;

		if (total > 0) {
			List<PoiInfoDto> rows = poiDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (PoiInfoDto dto : rows) {
				PoiInfo info = new PoiInfo();
				info.setId(dto.id);
				info.setType(dto.type);
				info.setName(dto.name);
				info.setLat(dto.lat);
				info.setLng(dto.lng);
				info.setRemark(dto.remark);

				query.rows.add(info);
			}
		}

		return query;
	}

	public Page<PoiInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
		int total = poiDao.searchPageCount(companyId, filter);
		Page<PoiInfo> page = new Page<PoiInfo>();
		page.total = total;

		if (total > 0) {
			List<PoiInfoDto> rows = poiDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (PoiInfoDto dto : rows) {
				PoiInfo info = new PoiInfo();
				info.setId(dto.id);
				info.setType(dto.type);
				info.setName(dto.name);
				info.setLat(dto.lat);
				info.setLng(dto.lng);
				info.setType(dto.type);
				info.setRemark(dto.remark);
				page.rows.add(info);
			}
		}
		return page;
	}

	public PoiInfo fetchInfo(long id) {
		PoiInfoDto dto = poiDao.fetchInfo(id);
		PoiInfo info = new PoiInfo();
		info.setId(dto.id);
		info.setType(dto.type);
		info.setName(dto.name);
		info.setLat(dto.lat);
		info.setLng(dto.lng);
		info.setType(dto.type);
		info.setRemark(dto.remark);
		return info;
	}

	@ServiceMethod(id = "baseinfo.poi.create", pid = "baseinfo.poi", name = "创建新的兴趣点")
	@Transactional
	public void create(Poi poi) {
		PoiDto dto = new PoiDto();
		dto.id = poi.getId();
		dto.companyId = poi.getCompanyId();
		dto.type = poi.getType();
		dto.name = poi.getName();
		dto.lat = poi.getLat();
		dto.lng = poi.getLng();
		dto.remark = poi.getRemark();

		poiDao.create(dto);
	}

	@ServiceMethod(id = "baseinfo.poi.update", pid = "baseinfo.poi", name = "更新兴趣点")
	@Transactional
	public void update(Poi poi) {
		PoiDto dto = new PoiDto();
		dto.id = poi.getId();
		dto.companyId = poi.getCompanyId();
		dto.type = poi.getType();
		dto.name = poi.getName();
		dto.lat = poi.getLat();
		dto.lng = poi.getLng();
		dto.remark = poi.getRemark();
		dto.editTime = poi.getEditTime();

		int rows = poiDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	public Poi fetch(long id) {
		PoiDto dto = poiDao.fetch(id);

		Poi poi = new Poi();
		poi.setId(dto.id);
		poi.setCompanyId(dto.companyId);
		poi.setType(dto.type);
		poi.setName(dto.name);
		poi.setLat(dto.lat);
		poi.setLng(dto.lng);
		poi.setRemark(dto.remark);
		poi.setEditTime(dto.editTime);

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
