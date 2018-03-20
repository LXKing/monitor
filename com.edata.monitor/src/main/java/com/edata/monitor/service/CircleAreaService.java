package com.edata.monitor.service;

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
import com.edata.monitor.dao.baseinfo.CircleAreaDto;
import com.edata.monitor.dao.baseinfo.CircleAreaInfoDto;
import com.edata.monitor.dao.baseinfo.IAreaInDeviceDao;
import com.edata.monitor.dao.baseinfo.ICircleAreaDao;
import com.edata.monitor.domain.baseinfo.AreaInDeviceInfo;
import com.edata.monitor.domain.baseinfo.CircleArea;
import com.edata.monitor.domain.baseinfo.CircleAreaInfo;
import com.edata.monitor.domain.instruct.DeviceInAreaInfo;
import com.edata.monitor.util.kind.AreaActions;
import com.edata.monitor.util.kind.AreaKinds;

@Service
public class CircleAreaService {
	@Autowired
	private ICircleAreaDao circleAreaDao;
	@Autowired
	private IAreaInDeviceDao areaInDeviceDao;

	private final byte areaKind = (byte) AreaKinds.CircleArea.getIndex();

	public Page<CircleAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		int total = circleAreaDao.queryPageCount(companyId, filter);
		Page<CircleAreaInfo> query = new Page<CircleAreaInfo>();
		query.total = total;

		if (total > 0) {
			List<CircleAreaInfoDto> rows = circleAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);

			for (CircleAreaInfoDto dto : rows) {
				CircleAreaInfo info = new CircleAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setLat(dto.lat);
				info.setLng(dto.lng);
				info.setRadius(dto.radius);
				info.setRemark(dto.remark);

				query.rows.add(info);
			}
		}

		return query;
	}

	public Page<CircleAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
		int total = circleAreaDao.searchPageCount(companyId, filter);
		Page<CircleAreaInfo> page = new Page<CircleAreaInfo>();
		page.total = total;

		if (total > 0) {
			List<CircleAreaInfoDto> rows = circleAreaDao.searchPageDetail(companyId, filter, pageIndex, pageSize);

			for (CircleAreaInfoDto dto : rows) {
				CircleAreaInfo info = new CircleAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setLat(dto.lat);
				info.setLng(dto.lng);
				info.setRadius(dto.radius);
				info.setRemark(dto.remark);
				page.rows.add(info);
			}
		}
		return page;
	}

	@ServiceMethod(id = "baseinfo.circleArea.create", pid = "baseinfo.circleArea", name = "创建圆形区域")
	@Transactional
	public void create(CircleArea circleArea) {
		CircleAreaDto dto = new CircleAreaDto();
		dto.id = circleArea.getId();
		dto.companyId = circleArea.getCompanyId();
		dto.name = circleArea.getName();
		dto.deviceCatch = circleArea.isDeviceCatch();
		dto.flag = circleArea.getFlag();
		dto.lat = circleArea.getLat();
		dto.lng = circleArea.getLng();
		dto.radius = circleArea.getRadius();
		dto.maxSpeed = circleArea.getMaxSpeed();
		dto.overspeedSeconds = circleArea.getOverspeedSeconds();
		dto.startTime = circleArea.getStartTime();
		dto.endTime = circleArea.getEndTime();
		dto.remark = circleArea.getRemark();

		circleAreaDao.create(dto);
	}

	@ServiceMethod(id = "baseinfo.circleArea.update", pid = "baseinfo.circleArea", name = "修改圆形区域")
	@Transactional
	public void update(String unid, String user, CircleArea circleArea) {
		CircleAreaDto old = circleAreaDao.fetch(circleArea.getId());

		CircleAreaDto dto = new CircleAreaDto();
		dto.id = circleArea.getId();
		dto.companyId = circleArea.getCompanyId();
		dto.name = circleArea.getName();
		dto.deviceCatch = circleArea.isDeviceCatch();
		dto.flag = circleArea.getFlag();
		dto.lat = circleArea.getLat();
		dto.lng = circleArea.getLng();
		dto.radius = circleArea.getRadius();
		dto.maxSpeed = circleArea.getMaxSpeed();
		dto.overspeedSeconds = circleArea.getOverspeedSeconds();
		dto.startTime = circleArea.getStartTime();
		dto.endTime = circleArea.getEndTime();
		dto.remark = circleArea.getRemark();
		dto.editTime = circleArea.getEditTime();

		int rows = circleAreaDao.update(dto);
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

			if (old.deviceCatch && !circleArea.isDeviceCatch()) {// 由终端计算变成平台计算
				AreaCatcherCache.bind(number, dto.id, areaKind);

				info.setAction((byte) AreaActions.Remove.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);

				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else if (!old.deviceCatch && circleArea.isDeviceCatch()) {// 由平台计算变成终端计算
				AreaCatcherCache.remove(dto.id, areaKind);

				info.setAction((byte) AreaActions.Append.getIndex());
				areaInDeviceDao.addVehicle(number, dto.id, areaKind);
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);

				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else {
				if (!circleArea.isDeviceCatch())
					AreaCatcherCache.refresh(dto.id, areaKind);

				info.setAction((byte) AreaActions.Edit.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Update.getIndex(), unid, user);
				SynchronizerCache.put(info);
			}
		}
	}

	public CircleArea fetch(long id) {
		CircleAreaDto dto = circleAreaDao.fetch(id);

		CircleArea circleArea = new CircleArea();
		circleArea.setId(dto.id);
		circleArea.setCompanyId(dto.companyId);
		circleArea.setName(dto.name);
		circleArea.setDeviceCatch(dto.deviceCatch);
		circleArea.setFlag(dto.flag);
		circleArea.setLat(dto.lat);
		circleArea.setLng(dto.lng);
		circleArea.setRadius(dto.radius);
		circleArea.setMaxSpeed(dto.maxSpeed);
		circleArea.setOverspeedSeconds(dto.overspeedSeconds);
		circleArea.setStartTime(dto.startTime);
		circleArea.setEndTime(dto.endTime);
		circleArea.setRemark(dto.remark);
		circleArea.setEditTime(dto.editTime);

		return circleArea;
	}

	@ServiceMethod(id = "baseinfo.circleArea.delete", pid = "baseinfo.circleArea", name = "删除圆形区域")
	@Transactional
	public void delete(String unid, String user, long id) {
		CircleAreaDto circle = circleAreaDao.fetch(id);
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
				if (circle.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
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
			List<AreaInDeviceInfoDto> rows = areaInDeviceDao.assignedPageVehiclesDetail(circleAreaId, areaKind, (pageIndex - 1) * pageSize, pageSize);

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

	/**
	 * 绑定车辆
	 */
	@ServiceMethod(id = "baseinfo.circleArea.addVehicles", pid = "baseinfo.circleArea", name = "圆形区域绑定车辆")
	@Transactional
	public void addVehicles(String unid, String user, long circleAreaId, List<String> numbers) {
		CircleAreaDto circle = circleAreaDao.fetch(circleAreaId);
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
				if (circle.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, circleAreaId, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
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
		CircleAreaDto circle = circleAreaDao.fetch(circleAreaId);
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
			if (circle.deviceCatch)
				areaInDeviceDao.log(info.getId(), number, circleAreaId, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
		}
		SynchronizerCache.put(info);
		AreaCatcherCache.unbind(number, circleAreaId, areaKind);
	}

	public CircleAreaInfo fetchInfo(Long id) {
		CircleAreaDto dto = circleAreaDao.fetch(id);
		CircleAreaInfo info = new CircleAreaInfo();
		info.setId(dto.id);
		info.setName(dto.name);
		info.setLat(dto.lat);
		info.setLng(dto.lng);
		info.setRadius(dto.radius);
		info.setRemark(dto.remark);

		return info;
	}
}
