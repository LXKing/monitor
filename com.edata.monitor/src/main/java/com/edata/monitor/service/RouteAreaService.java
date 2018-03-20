package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AreaCatcherCache;
import com.edata.monitor.cache.SynchronizerCache;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.AreaInDeviceInfoDto;
import com.edata.monitor.dao.baseinfo.IAreaInDeviceDao;
import com.edata.monitor.dao.baseinfo.IRouteAreaDao;
import com.edata.monitor.dao.baseinfo.ISectionAreaDao;
import com.edata.monitor.dao.baseinfo.RouteAreaDto;
import com.edata.monitor.dao.baseinfo.RouteAreaInfoDto;
import com.edata.monitor.dao.baseinfo.SectionAreaInfoDto;
import com.edata.monitor.dao.baseinfo.SectionPointDto;
import com.edata.monitor.domain.baseinfo.AreaInDeviceInfo;
import com.edata.monitor.domain.baseinfo.RouteArea;
import com.edata.monitor.domain.baseinfo.RouteAreaInfo;
import com.edata.monitor.domain.baseinfo.SectionAreaInfo;
import com.edata.monitor.domain.baseinfo.SectionPoint;
import com.edata.monitor.domain.instruct.DeviceInAreaInfo;
import com.edata.monitor.util.KeyValue;
import com.edata.monitor.util.kind.AreaActions;
import com.edata.monitor.util.kind.AreaKinds;

/**
 * 路线区域服务类
 * 
 * @author yangzs
 *
 */
@Service
public class RouteAreaService {
	@Autowired
	private IRouteAreaDao routeAreaDao;
	@Autowired
	private ISectionAreaDao sectionAreaDao;
	@Autowired
	private IAreaInDeviceDao areaInDeviceDao;

	private final byte areaKind = (byte) AreaKinds.RouteArea.getIndex();

	public Page<RouteAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		int total = routeAreaDao.queryPageCount(companyId, filter);
		Page<RouteAreaInfo> query = new Page<RouteAreaInfo>();
		query.total = total;

		if (total > 0) {
			List<RouteAreaInfoDto> rows = routeAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (RouteAreaInfoDto dto : rows) {
				RouteAreaInfo info = new RouteAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setDeviceCatch(dto.deviceCatch);
				info.setRemark(dto.remark);

				dto.sections = routeAreaDao.assignedSections(dto.id);
				if (dto.sections != null) {
					for (SectionAreaInfoDto s : dto.sections) {
						SectionAreaInfo i = new SectionAreaInfo();
						i.setId(s.id);
						i.setName(s.name);
						i.setRemark(s.remark);

						s.points = sectionAreaDao.fetchSectionPoint(s.id);
						if (s.points != null) {
							for (SectionPointDto p : s.points) {
								SectionPoint sp = new SectionPoint();
								sp.setId(p.id);
								sp.setIndex(p.index);
								sp.setLat(p.lat);
								sp.setLng(p.lng);
								sp.setSectionId(p.sectionId);

								i.getPoints().add(sp);
							}
						}

						info.getSections().add(i);
					}
				}

				query.rows.add(info);
			}
		}

		return query;
	}

	public Page<RouteAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
		int total = routeAreaDao.searchPageCount(companyId, filter);
		Page<RouteAreaInfo> search = new Page<RouteAreaInfo>();
		search.total = total;

		if (total > 0) {
			List<RouteAreaInfoDto> rows = routeAreaDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (RouteAreaInfoDto dto : rows) {
				RouteAreaInfo info = new RouteAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setDeviceCatch(dto.deviceCatch);
				info.setRemark(dto.remark);

				dto.sections = routeAreaDao.assignedSections(dto.id);
				if (dto.sections != null) {
					for (SectionAreaInfoDto s : dto.sections) {
						SectionAreaInfo i = new SectionAreaInfo();
						i.setId(s.id);
						i.setName(s.name);
						i.setRemark(s.remark);

						s.points = sectionAreaDao.fetchSectionPoint(s.id);
						if (s.points != null) {
							for (SectionPointDto p : s.points) {
								SectionPoint sp = new SectionPoint();
								sp.setId(p.id);
								sp.setIndex(p.index);
								sp.setLat(p.lat);
								sp.setLng(p.lng);
								sp.setSectionId(p.sectionId);

								i.getPoints().add(sp);
							}
						}

						info.getSections().add(i);
					}
				}

				search.rows.add(info);
			}
		}
		return search;
	}

	@ServiceMethod(id = "baseinfo.routeArea.create", pid = "baseinfo.routeArea", name = "创建新的路线")
	@Transactional
	public void create(RouteArea routeArea) {
		RouteAreaDto dto = new RouteAreaDto();
		dto.id = routeArea.getId();
		dto.companyId = routeArea.getCompanyId();
		dto.name = routeArea.getName();
		dto.deviceCatch = routeArea.isDeviceCatch();
		dto.flag = routeArea.getFlag();
		dto.startTime = routeArea.getStartTime();
		dto.endTime = routeArea.getEndTime();
		dto.remark = routeArea.getRemark();

		routeAreaDao.create(dto);
	}

	public RouteArea fetch(long id) {
		RouteAreaDto dto = routeAreaDao.fetch(id);
		RouteArea routeArea = new RouteArea();
		routeArea.setId(dto.id);
		routeArea.setCompanyId(dto.companyId);
		routeArea.setName(dto.name);
		routeArea.setDeviceCatch(dto.deviceCatch);
		routeArea.setFlag(dto.flag);
		routeArea.setStartTime(dto.startTime);
		routeArea.setEndTime(dto.endTime);
		routeArea.setRemark(dto.remark);
		routeArea.setEditTime(dto.editTime);

		return routeArea;
	}

	@ServiceMethod(id = "baseinfo.routeArea.update", pid = "baseinfo.routeArea", name = "修改路线")
	@Transactional
	public void update(String unid, String user, RouteArea routeArea) {
		RouteAreaDto old = routeAreaDao.fetch(routeArea.getId());

		RouteAreaDto dto = new RouteAreaDto();
		dto.id = routeArea.getId();
		dto.companyId = routeArea.getCompanyId();
		dto.name = routeArea.getName();
		dto.deviceCatch = routeArea.isDeviceCatch();
		dto.flag = routeArea.getFlag();
		dto.startTime = routeArea.getStartTime();
		dto.endTime = routeArea.getEndTime();
		dto.remark = routeArea.getRemark();
		dto.editTime = routeArea.getEditTime();

		int rows = routeAreaDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);

		List<String> numbers = areaInDeviceDao.findDevice(dto.id, areaKind);
		for (String number : numbers) {
			// 更新指令同步器
			DeviceInAreaInfo info = new DeviceInAreaInfo();

			info.setAreaId(dto.id);
			info.setAreaType(areaKind);
			info.setDeviceNumber(number);
			info.setId(UUID.randomUUID().toString());
			info.setSendTime(new Date());
			info.setUnid(unid);
			info.setUser(user);

			if (old.deviceCatch && !routeArea.isDeviceCatch()) {// 由终端计算变成平台计算
				AreaCatcherCache.bind(number, dto.id, areaKind);

				info.setAction((byte) AreaActions.Remove.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else if (!old.deviceCatch && routeArea.isDeviceCatch()) {// 由平台计算变成终端计算
				AreaCatcherCache.remove(dto.id, areaKind);

				info.setAction((byte) AreaActions.Append.getIndex());
				areaInDeviceDao.addVehicle(number, dto.id, areaKind);
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else {
				if (!routeArea.isDeviceCatch())
					AreaCatcherCache.refresh(dto.id, areaKind);

				info.setAction((byte) AreaActions.Edit.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Update.getIndex(), unid, user);
				SynchronizerCache.put(info);
			}
		}

	}

	@ServiceMethod(id = "baseinfo.routeArea.delete", pid = "baseinfo.routeArea", name = "删除路线")
	@Transactional
	public void delete(String unid, String user, long id) {
		RouteAreaDto route = routeAreaDao.fetch(id);
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
				if (route.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
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
		List<SectionAreaInfoDto> page = routeAreaDao.assignedSections(routeId);
		List<SectionAreaInfo> sections = new ArrayList<SectionAreaInfo>();
		for (SectionAreaInfoDto dto : page) {
			SectionAreaInfo info = new SectionAreaInfo();
			info.setId(dto.id);
			info.setName(dto.name);
			info.setRemark(dto.remark);

			dto.points = sectionAreaDao.fetchSectionPoint(dto.id);
			if (dto.points != null) {
				for (SectionPointDto rpd : dto.points) {
					SectionPoint rp = new SectionPoint();
					rp.setId(rpd.id);
					rp.setLat(rpd.lat);
					rp.setLng(rpd.lng);
					rp.setSectionId(rpd.sectionId);
					rp.setIndex(rpd.index);

					info.getPoints().add(rp);
				}
			}

			sections.add(info);
		}

		return sections;
	}

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
			List<AreaInDeviceInfoDto> rows = areaInDeviceDao.assignedPageVehiclesDetail(routeAreaId, areaKind, (pageIndex - 1) * pageSize, pageSize);
			for (AreaInDeviceInfoDto dto : rows) {
				AreaInDeviceInfo info = new AreaInDeviceInfo();
				info.setAreaId(dto.areaId);
				info.setAreaType(dto.areaType);
				info.setDeviceNumber(dto.deviceNumber);
				info.setPlateNumber(dto.plateNumber);
				info.setTime(dto.time);

				query.rows.add(info);
			}
		}
		return query;
	}

	@ServiceMethod(id = "baseinfo.routeArea.addVehicles", pid = "baseinfo.routeArea", name = "路线绑定车辆")
	@Transactional
	public void addVehicles(String unid, String user, long routeAreaId, List<String> numbers) {
		RouteAreaDto route = routeAreaDao.fetch(routeAreaId);
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
				if (route.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, routeAreaId, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
			}
			SynchronizerCache.put(info);
			AreaCatcherCache.bind(number, routeAreaId, areaKind);
		}
	}

	@ServiceMethod(id = "baseinfo.routeArea.removeVehicle", pid = "baseinfo.routeArea", name = "路线解除车辆")
	@Transactional
	public void removeVehicle(String unid, String user, long routeAreaId, String number) {
		RouteAreaDto route = routeAreaDao.fetch(routeAreaId);
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
			if (route.deviceCatch)
				areaInDeviceDao.log(info.getId(), number, routeAreaId, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
		}
		SynchronizerCache.put(info);
		AreaCatcherCache.unbind(number, routeAreaId, areaKind);
	}

	public RouteAreaInfo fetchInfo(Long id) {
		RouteAreaInfoDto dto = routeAreaDao.fetchInfo(id);
		RouteAreaInfo info = new RouteAreaInfo();
		info.setId(dto.id);
		info.setName(dto.name);
		info.setDeviceCatch(dto.deviceCatch);
		info.setRemark(dto.remark);

		dto.sections = routeAreaDao.assignedSections(id);
		if (dto.sections != null) {
			for (SectionAreaInfoDto s : dto.sections) {
				SectionAreaInfo i = new SectionAreaInfo();
				i.setId(s.id);
				i.setName(s.name);
				i.setRemark(s.remark);

				s.points = sectionAreaDao.fetchSectionPoint(s.id);
				if (s.points != null) {
					for (SectionPointDto p : s.points) {
						SectionPoint sp = new SectionPoint();
						sp.setId(p.id);
						sp.setIndex(p.index);
						sp.setLat(p.lat);
						sp.setLng(p.lng);
						sp.setSectionId(p.sectionId);

						i.getPoints().add(sp);
					}
				}

				info.getSections().add(i);
			}
		}

		return info;
	}

}
