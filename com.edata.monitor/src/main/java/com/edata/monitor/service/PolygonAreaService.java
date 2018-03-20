package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.common.LatLng;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AreaCatcherCache;
import com.edata.monitor.cache.SynchronizerCache;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.AreaInDeviceInfoDto;
import com.edata.monitor.dao.baseinfo.IAreaInDeviceDao;
import com.edata.monitor.dao.baseinfo.IPolygonAreaDao;
import com.edata.monitor.dao.baseinfo.PolygonAreaDto;
import com.edata.monitor.dao.baseinfo.PolygonAreaInfoDto;
import com.edata.monitor.domain.baseinfo.AreaInDeviceInfo;
import com.edata.monitor.domain.baseinfo.PolygonArea;
import com.edata.monitor.domain.baseinfo.PolygonAreaInfo;
import com.edata.monitor.domain.instruct.DeviceInAreaInfo;
import com.edata.monitor.util.KeyValue;
import com.edata.monitor.util.kind.AreaActions;
import com.edata.monitor.util.kind.AreaKinds;

/**
 * 多边形区域服务类
 * 
 * @author yangzs
 *
 */
@Service
public class PolygonAreaService {
	@Autowired
	private IPolygonAreaDao polygonAreaDao;
	@Autowired
	private IAreaInDeviceDao areaInDeviceDao;

	private final byte areaKind = (byte) AreaKinds.PolygonArea.getIndex();

	public Page<PolygonAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		int total = polygonAreaDao.queryPageCount(companyId, filter);
		Page<PolygonAreaInfo> query = new Page<PolygonAreaInfo>();
		query.total = total;

		if (total > 0) {
			List<PolygonAreaInfoDto> rows = polygonAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (PolygonAreaInfoDto dto : rows) {
				PolygonAreaInfo info = new PolygonAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setDeviceCatch(dto.deviceCatch);
				info.setRemark(dto.remark);

				List<LatLng> points = polygonAreaDao.fetchPolygonPoint(dto.id);
				info.setPoints(points);

				query.rows.add(info);
			}
		}

		return query;
	}

	public Page<PolygonAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
		int total = polygonAreaDao.searchPageCount(companyId, filter);
		Page<PolygonAreaInfo> search = new Page<PolygonAreaInfo>();
		search.total = total;

		if (total > 0) {
			List<PolygonAreaInfoDto> rows = polygonAreaDao.searchPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (PolygonAreaInfoDto dto : rows) {
				PolygonAreaInfo info = new PolygonAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setDeviceCatch(dto.deviceCatch);
				info.setRemark(dto.remark);

				List<LatLng> points = polygonAreaDao.fetchPolygonPoint(dto.id);
				info.setPoints(points);

				search.rows.add(info);
			}
		}
		return search;
	}

	@ServiceMethod(id = "baseinfo.polygonArea.create", pid = "baseinfo.polygonArea", name = "创建新的多边形区域")
	@Transactional
	public void create(PolygonArea polygonArea) {
		PolygonAreaDto dto = new PolygonAreaDto();
		dto.id = polygonArea.getId();
		dto.companyId = polygonArea.getCompanyId();
		dto.name = polygonArea.getName();
		dto.deviceCatch = polygonArea.isDeviceCatch();
		dto.flag = polygonArea.getFlag();
		dto.maxSpeed = polygonArea.getMaxSpeed();
		dto.overspeedSeconds = polygonArea.getOverspeedSeconds();
		dto.startTime = polygonArea.getStartTime();
		dto.endTime = polygonArea.getEndTime();
		dto.remark = polygonArea.getRemark();
		dto.points = polygonArea.getPoints();

		polygonAreaDao.create(dto);

		List<KeyValue> rows = new ArrayList<KeyValue>();
		for (LatLng p : dto.points) {
			KeyValue row = new KeyValue();
			row.setKey(dto.id);
			row.setValue(p);

			rows.add(row);
		}
		polygonAreaDao.createPolygonPoint(rows);
	}

	public PolygonArea fetch(long id) {
		PolygonAreaDto dto = polygonAreaDao.fetch(id);
		PolygonArea polygonArea = new PolygonArea();
		polygonArea.setId(dto.id);
		polygonArea.setCompanyId(dto.companyId);
		polygonArea.setName(dto.name);
		polygonArea.setDeviceCatch(dto.deviceCatch);
		polygonArea.setFlag(dto.flag);
		polygonArea.setMaxSpeed(dto.maxSpeed);
		polygonArea.setOverspeedSeconds(dto.overspeedSeconds);
		polygonArea.setStartTime(dto.startTime);
		polygonArea.setEndTime(dto.endTime);
		polygonArea.setRemark(dto.remark);
		polygonArea.setEditTime(dto.editTime);

		List<LatLng> points = polygonAreaDao.fetchPolygonPoint(id);
		polygonArea.setPoints(points);

		return polygonArea;
	}

	@ServiceMethod(id = "baseinfo.polygonArea.update", pid = "baseinfo.polygonArea", name = "修改多边形区域")
	@Transactional
	public void update(String unid, String user, PolygonArea polygonArea) {
		PolygonAreaDto old = polygonAreaDao.fetch(polygonArea.getId());

		PolygonAreaDto dto = new PolygonAreaDto();
		dto.id = polygonArea.getId();
		dto.companyId = polygonArea.getCompanyId();
		dto.name = polygonArea.getName();
		dto.deviceCatch = polygonArea.isDeviceCatch();
		dto.flag = polygonArea.getFlag();
		dto.maxSpeed = polygonArea.getMaxSpeed();
		dto.overspeedSeconds = polygonArea.getOverspeedSeconds();
		dto.startTime = polygonArea.getStartTime();
		dto.endTime = polygonArea.getEndTime();
		dto.remark = polygonArea.getRemark();
		dto.editTime = polygonArea.getEditTime();

		dto.points = polygonArea.getPoints();

		int count = polygonAreaDao.update(dto);
		if (count != 1)
			throw new RuntimeException(Errors.anotherEdited);

		polygonAreaDao.deletePolygonPoint(dto.id);

		List<KeyValue> rows = new ArrayList<KeyValue>();
		for (LatLng p : dto.points) {
			KeyValue row = new KeyValue();
			row.setKey(dto.id);
			row.setValue(p);

			rows.add(row);
		}
		polygonAreaDao.createPolygonPoint(rows);

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

			if (old.deviceCatch && !polygonArea.isDeviceCatch()) {// 由终端计算变成平台计算
				AreaCatcherCache.bind(number, dto.id, areaKind);

				info.setAction((byte) AreaActions.Remove.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else if (!old.deviceCatch && polygonArea.isDeviceCatch()) {// 由平台计算变成终端计算
				AreaCatcherCache.remove(dto.id, areaKind);

				info.setAction((byte) AreaActions.Append.getIndex());
				areaInDeviceDao.addVehicle(number, dto.id, areaKind);
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else {
				if (!polygonArea.isDeviceCatch())
					AreaCatcherCache.refresh(dto.id, areaKind);

				info.setAction((byte) AreaActions.Edit.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Update.getIndex(), unid, user);
				SynchronizerCache.put(info);
			}
		}

	}

	@ServiceMethod(id = "baseinfo.polygonArea.delete", pid = "baseinfo.polygonArea", name = "删除多边形区域")
	@Transactional
	public void delete(String unid, String user, long id) {
		PolygonAreaDto polygon = polygonAreaDao.fetch(id);
		List<String> numbers = areaInDeviceDao.findDevice(id, areaKind);
		polygonAreaDao.delete(id);
		polygonAreaDao.deletePolygonPoint(id);
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

			if (polygon != null) {
				areaInDeviceDao.removeVehicle(number, id, areaKind);
				if (polygon.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
			}

			SynchronizerCache.put(info);
			AreaCatcherCache.unbind(number, id, areaKind);
		}
	}

	public boolean exist(String name, String companyId, long id) {
		return polygonAreaDao.existOutId(name, companyId, id);
	}

	public boolean exist(String name, String companyId) {
		return polygonAreaDao.exist(name, companyId);
	}

	/**
	 * 获取已绑定的车辆
	 */
	public Page<AreaInDeviceInfo> assignedVehicles(long polygonAreaId, int pageIndex, int pageSize) {
		int total = areaInDeviceDao.assignedPageVehiclesCount(polygonAreaId, areaKind);
		Page<AreaInDeviceInfo> query = new Page<AreaInDeviceInfo>();
		query.total = total;

		if (total > 0) {
			List<AreaInDeviceInfoDto> rows = areaInDeviceDao
					.assignedPageVehiclesDetail(polygonAreaId, areaKind, (pageIndex - 1) * pageSize, pageSize);
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
	@ServiceMethod(id = "baseinfo.polygonArea.addVehicles", pid = "baseinfo.polygonArea", name = "多边形区域绑定车辆")
	@Transactional
	public void addVehicles(String unid, String user, long polygonAreaId, List<String> numbers) {
		PolygonAreaDto polygon = polygonAreaDao.fetch(polygonAreaId);
		for (String number : numbers) {
			// 更新指令同步器
			DeviceInAreaInfo info = new DeviceInAreaInfo();
			info.setAction((byte) AreaActions.Append.getIndex());
			info.setAreaId(polygonAreaId);
			info.setAreaType(areaKind);
			info.setDeviceNumber(number);
			info.setId(UUID.randomUUID().toString());
			info.setSendTime(new Date());
			info.setUnid(unid);
			info.setUser(user);

			if (polygon != null) {
				areaInDeviceDao.addVehicle(number, polygonAreaId, areaKind);
				if (polygon.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, polygonAreaId, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
			}
			SynchronizerCache.put(info);
			AreaCatcherCache.bind(number, polygonAreaId, areaKind);
		}
	}

	/**
	 * 解除车辆
	 */
	@ServiceMethod(id = "baseinfo.polygonArea.removeVehicle", pid = "baseinfo.polygonArea", name = "多边形区域解除车辆")
	@Transactional
	public void removeVehicle(String unid, String user, long polygonAreaId, String number) {
		PolygonAreaDto polygon = polygonAreaDao.fetch(polygonAreaId);
		// 更新指令同步器
		DeviceInAreaInfo info = new DeviceInAreaInfo();
		info.setAction((byte) AreaActions.Remove.getIndex());
		info.setAreaId(polygonAreaId);
		info.setAreaType(areaKind);
		info.setDeviceNumber(number);
		info.setId(UUID.randomUUID().toString());
		info.setSendTime(new Date());
		info.setUnid(unid);
		info.setUser(user);

		if (polygon != null) {
			areaInDeviceDao.removeVehicle(number, polygonAreaId, areaKind);
			if (polygon.deviceCatch)
				areaInDeviceDao.log(info.getId(), number, polygonAreaId, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
		}
		SynchronizerCache.put(info);
		AreaCatcherCache.unbind(number, polygonAreaId, areaKind);
	}

	public PolygonAreaInfo fetchInfo(Long id) {
		PolygonAreaDto dto = polygonAreaDao.fetch(id);
		dto.points = polygonAreaDao.fetchPolygonPoint(id);

		PolygonAreaInfo info = new PolygonAreaInfo();
		info.setId(dto.id);
		info.setName(dto.name);
		info.setDeviceCatch(dto.deviceCatch);
		info.setRemark(dto.remark);
		info.setPoints(dto.points);

		return info;
	}
}
