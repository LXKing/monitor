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
import com.edata.monitor.dao.baseinfo.IAreaInDeviceDao;
import com.edata.monitor.dao.baseinfo.IRectangleAreaDao;
import com.edata.monitor.dao.baseinfo.RectangleAreaDto;
import com.edata.monitor.dao.baseinfo.RectangleAreaInfoDto;
import com.edata.monitor.domain.baseinfo.AreaInDeviceInfo;
import com.edata.monitor.domain.baseinfo.RectangleArea;
import com.edata.monitor.domain.baseinfo.RectangleAreaInfo;
import com.edata.monitor.domain.instruct.DeviceInAreaInfo;
import com.edata.monitor.util.kind.AreaActions;
import com.edata.monitor.util.kind.AreaKinds;

/**
 * 矩形区域服务类
 * 
 * @author yangzs
 *
 */
@Service
public class RectangleAreaService {
	@Autowired
	private IRectangleAreaDao rectangleAreaDao;
	@Autowired
	private IAreaInDeviceDao areaInDeviceDao;

	private final byte areaKind = (byte) AreaKinds.RectangleArea.getIndex();

	public Page<RectangleAreaInfo> query(String companyId, String filter, int pageIndex, int pageSize) {
		int total = rectangleAreaDao.queryPageCount(companyId, filter);
		Page<RectangleAreaInfo> query = new Page<RectangleAreaInfo>();
		query.total = total;
		if (total > 0) {
			List<RectangleAreaInfoDto> rows = rectangleAreaDao.queryPageDetail(companyId, filter, (pageIndex - 1) * pageSize, pageSize);
			for (RectangleAreaInfoDto dto : rows) {
				RectangleAreaInfo info = new RectangleAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setUllat(dto.ullat);
				info.setUllng(dto.ullng);
				info.setBrlat(dto.brlat);
				info.setBrlng(dto.brlng);
				info.setDeviceCatch(dto.deviceCatch);
				info.setRemark(dto.remark);

				query.rows.add(info);
			}
		}

		return query;
	}

	public Page<RectangleAreaInfo> search(String companyId, String filter, int pageIndex, int pageSize) {
		int total = rectangleAreaDao.searchPageCount(companyId, filter);
		Page<RectangleAreaInfo> page = new Page<RectangleAreaInfo>();
		page.total = total;
		if (total > 0) {
			List<RectangleAreaInfoDto> rows = rectangleAreaDao.searchPageDetail(companyId, filter, pageIndex, pageSize);

			for (RectangleAreaInfoDto dto : rows) {
				RectangleAreaInfo info = new RectangleAreaInfo();
				info.setId(dto.id);
				info.setName(dto.name);
				info.setUllat(dto.ullat);
				info.setUllng(dto.ullng);
				info.setBrlat(dto.brlat);
				info.setBrlng(dto.brlng);
				info.setDeviceCatch(dto.deviceCatch);
				info.setRemark(dto.remark);

				page.rows.add(info);
			}
		}
		return page;
	}

	@ServiceMethod(id = "baseinfo.rectangleArea.create", pid = "baseinfo.rectangleArea", name = "创建新的矩形区域")
	@Transactional
	public void create(RectangleArea rectangleArea) {
		RectangleAreaDto dto = new RectangleAreaDto();
		dto.id = rectangleArea.getId();
		dto.companyId = rectangleArea.getCompanyId();
		dto.name = rectangleArea.getName();
		dto.deviceCatch = rectangleArea.isDeviceCatch();
		dto.flag = rectangleArea.getFlag();
		dto.ullat = rectangleArea.getUllat();
		dto.ullng = rectangleArea.getUllng();
		dto.brlat = rectangleArea.getBrlat();
		dto.brlng = rectangleArea.getBrlng();
		dto.maxSpeed = rectangleArea.getMaxSpeed();
		dto.overspeedSeconds = rectangleArea.getOverspeedSeconds();
		dto.startTime = rectangleArea.getStartTime();
		dto.endTime = rectangleArea.getEndTime();
		dto.remark = rectangleArea.getRemark();

		rectangleAreaDao.create(dto);
	}

	public RectangleArea fetch(long id) {
		RectangleAreaDto dto = rectangleAreaDao.fetch(id);
		RectangleArea rectangleArea = new RectangleArea();
		rectangleArea.setId(dto.id);
		rectangleArea.setCompanyId(dto.companyId);
		rectangleArea.setName(dto.name);
		rectangleArea.setDeviceCatch(dto.deviceCatch);
		rectangleArea.setFlag(dto.flag);
		rectangleArea.setUllat(dto.ullat);
		rectangleArea.setUllng(dto.ullng);
		rectangleArea.setBrlat(dto.brlat);
		rectangleArea.setBrlng(dto.brlng);
		rectangleArea.setMaxSpeed(dto.maxSpeed);
		rectangleArea.setOverspeedSeconds(dto.overspeedSeconds);
		rectangleArea.setStartTime(dto.startTime);
		rectangleArea.setEndTime(dto.endTime);
		rectangleArea.setRemark(dto.remark);
		rectangleArea.setEditTime(dto.editTime);

		return rectangleArea;
	}

	@ServiceMethod(id = "baseinfo.rectangleArea.update", pid = "baseinfo.rectangleArea", name = "修改矩形区域")
	@Transactional
	public void update(String unid, String user, RectangleArea rectangleArea) {
		RectangleAreaDto old = rectangleAreaDao.fetch(rectangleArea.getId());

		RectangleAreaDto dto = new RectangleAreaDto();
		dto.id = rectangleArea.getId();
		dto.companyId = rectangleArea.getCompanyId();
		dto.name = rectangleArea.getName();
		dto.deviceCatch = rectangleArea.isDeviceCatch();
		dto.flag = rectangleArea.getFlag();
		dto.ullat = rectangleArea.getUllat();
		dto.ullng = rectangleArea.getUllng();
		dto.brlat = rectangleArea.getBrlat();
		dto.brlng = rectangleArea.getBrlng();
		dto.maxSpeed = rectangleArea.getMaxSpeed();
		dto.overspeedSeconds = rectangleArea.getOverspeedSeconds();
		dto.startTime = rectangleArea.getStartTime();
		dto.endTime = rectangleArea.getEndTime();
		dto.remark = rectangleArea.getRemark();
		dto.editTime = rectangleArea.getEditTime();

		int rows = rectangleAreaDao.update(dto);
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

			if (old.deviceCatch && !rectangleArea.isDeviceCatch()) {// 由终端计算变成平台计算
				AreaCatcherCache.bind(number, dto.id, areaKind);

				info.setAction((byte) AreaActions.Remove.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else if (!old.deviceCatch && rectangleArea.isDeviceCatch()) {// 由平台计算变成终端计算
				AreaCatcherCache.remove(dto.id, areaKind);

				info.setAction((byte) AreaActions.Append.getIndex());
				areaInDeviceDao.addVehicle(number, dto.id, areaKind);
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
				SynchronizerCache.put(info);

				info.setAction((byte) AreaActions.Edit.getIndex());
				SynchronizerCache.put(info);
			} else {
				if (!rectangleArea.isDeviceCatch())
					AreaCatcherCache.refresh(dto.id, areaKind);

				info.setAction((byte) AreaActions.Edit.getIndex());
				areaInDeviceDao.log(info.getId(), number, dto.id, areaKind, (byte) AreaActions.Update.getIndex(), unid, user);
				SynchronizerCache.put(info);
			}
		}

	}

	@ServiceMethod(id = "baseinfo.rectangleArea.delete", pid = "baseinfo.rectangleArea", name = "删除矩形区域")
	@Transactional
	public void delete(String unid, String user, long id) {
		RectangleAreaDto rectangle = rectangleAreaDao.fetch(id);
		List<String> numbers = areaInDeviceDao.findDevice(id, areaKind);
		
		rectangleAreaDao.delete(id);
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

			if (rectangle != null) {
				areaInDeviceDao.removeVehicle(number, id, areaKind);
				if (rectangle.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, id, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
			}

			SynchronizerCache.put(info);
			AreaCatcherCache.unbind(number, id, areaKind);
		}
	}

	public boolean exist(String name, String companyId, long id) {
		return rectangleAreaDao.existOutId(name, companyId, id);
	}

	public boolean exist(String name, String companyId) {
		return rectangleAreaDao.exist(name, companyId);
	}

	public Page<AreaInDeviceInfo> assignedVehicles(long rectangleAreaId, int pageIndex, int pageSize) {
		int total = areaInDeviceDao.assignedPageVehiclesCount(rectangleAreaId, areaKind);
		Page<AreaInDeviceInfo> query = new Page<AreaInDeviceInfo>();
		query.total = total;
		if (total > 0) {
			List<AreaInDeviceInfoDto> rows = areaInDeviceDao.assignedPageVehiclesDetail(rectangleAreaId, areaKind, (pageIndex - 1) * pageSize,
					pageSize);

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

	@ServiceMethod(id = "baseinfo.rectangleArea.addVehicles", pid = "baseinfo.rectangleArea", name = "矩形区域绑定车辆")
	@Transactional
	public void addVehicles(String unid, String user, long rectangleAreaId, List<String> numbers) {
		RectangleAreaDto rectangle = rectangleAreaDao.fetch(rectangleAreaId);
		for (String number : numbers) {
			// 更新指令同步器
			DeviceInAreaInfo info = new DeviceInAreaInfo();
			info.setAction((byte) AreaActions.Append.getIndex());
			info.setAreaId(rectangleAreaId);
			info.setAreaType(areaKind);
			info.setDeviceNumber(number);
			info.setId(UUID.randomUUID().toString());
			info.setSendTime(new Date());
			info.setUnid(unid);
			info.setUser(user);

			if (rectangle != null) {
				areaInDeviceDao.addVehicle(number, rectangleAreaId, areaKind);
				if (rectangle.deviceCatch)
					areaInDeviceDao.log(info.getId(), number, rectangleAreaId, areaKind, (byte) AreaActions.Append.getIndex(), unid, user);
			}
			SynchronizerCache.put(info);
			AreaCatcherCache.bind(number, rectangleAreaId, areaKind);
		}
	}

	@ServiceMethod(id = "baseinfo.rectangleArea.removeVehicle", pid = "baseinfo.rectangleArea", name = "矩形区域解除车辆")
	@Transactional
	public void removeVehicle(String unid, String user, long rectangleAreaId, String number) {
		RectangleAreaDto rectangle = rectangleAreaDao.fetch(rectangleAreaId);
		// 更新指令同步器
		DeviceInAreaInfo info = new DeviceInAreaInfo();
		info.setAction((byte) AreaActions.Remove.getIndex());
		info.setAreaId(rectangleAreaId);
		info.setAreaType(areaKind);
		info.setDeviceNumber(number);
		info.setId(UUID.randomUUID().toString());
		info.setSendTime(new Date());
		info.setUnid(unid);

		if (rectangle != null) {
			areaInDeviceDao.removeVehicle(number, rectangleAreaId, areaKind);
			if (rectangle.deviceCatch)
				areaInDeviceDao.log(info.getId(), number, rectangleAreaId, areaKind, (byte) AreaActions.Remove.getIndex(), unid, user);
		}
		SynchronizerCache.put(info);
		AreaCatcherCache.unbind(number, rectangleAreaId, areaKind);
	}

	public RectangleAreaInfo fetchInfo(Long id) {
		RectangleAreaDto dto = rectangleAreaDao.fetch(id);
		RectangleAreaInfo info = new RectangleAreaInfo();
		info.setId(dto.id);
		info.setName(dto.name);
		info.setUllat(dto.ullat);
		info.setUllng(dto.ullng);
		info.setBrlat(dto.brlat);
		info.setBrlng(dto.brlng);
		info.setDeviceCatch(dto.deviceCatch);
		info.setRemark(dto.remark);

		return info;
	}

}
