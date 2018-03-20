package com.edata.monitor.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AssociationCache;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.alarm.AlarmDto;
import com.edata.monitor.dao.alarm.IAlarmDao;
import com.edata.monitor.dao.alarm.ProcessAllDto;
import com.edata.monitor.dao.alarm.ProcessOnceDto;
import com.edata.monitor.domain.alarm.Alarm;
import com.edata.monitor.domain.alarm.ProcessAlarmOnce;
import com.edata.monitor.domain.cache.UserMonitorTarget;

@Service
public class AlarmService {
	@Autowired
	private IAlarmDao alarmDao;

	public List<Alarm> untreated(String deviceNumber) {
		List<AlarmDto> query = alarmDao.untreatedByDeviceNumber(deviceNumber);
		List<Alarm> alarms = new ArrayList<Alarm>();
		for (AlarmDto dto : query) {
			Alarm alarm = new Alarm();
			alarm.setId(dto.id);
			alarm.setGt(dto.gt);
			alarm.setSt(dto.st);
			alarm.setVal(dto.val);
			alarm.setLng(dto.lng);
			alarm.setLat(dto.lat);
			alarm.setAlt(dto.alt);
			alarm.setSp(dto.sp);
			alarm.setD(dto.d);
			alarm.setA(dto.a);
			alarm.setS(dto.s);
			alarm.setM(dto.m);
			alarm.setOil(dto.oil);
			alarm.setVss(dto.vss);
			alarm.setOvt(dto.ovt);
			alarm.setOid(dto.oid);
			alarm.setIot(dto.iot);
			alarm.setIid(dto.iid);
			alarm.setIof(dto.iof);
			alarm.setRid(dto.rid);
			alarm.setRt(dto.rt);
			alarm.setRf(dto.rf);
			alarm.setAid(dto.aid);
			alarm.setExs(dto.exs);
			alarm.setIos(dto.ios);
			alarm.setAd0(dto.ad0);
			alarm.setAd0(dto.ad1);
			alarm.setNet(dto.net);
			alarm.setSat(dto.sat);
			alarm.setFrom(dto.from);
			alarm.setUserName(dto.userName);
			alarm.setUserTime(dto.userTime);
			alarm.setUserconfirm(dto.userconfirm);
			alarm.setUserMethod(dto.userMethod);
			alarm.setUserRemark(dto.userRemark);
			alarm.setEditTime(dto.editTime);

			alarms.add(alarm);
		}

		return alarms;
	}

	public Object processed(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) {
		int total = alarmDao.processPageCount(deviceNumber, start, end);
		Page<Alarm> query = new Page<Alarm>();
		query.total = total;

		if (total > 0) {
			List<AlarmDto> rows = alarmDao.processedPageDetail(deviceNumber, start, end, (pageIndex - 1) * pageSize, pageSize);
			for (AlarmDto dto : rows) {
				Alarm alarm = new Alarm();
				alarm.setId(dto.id);
				alarm.setGt(dto.gt);
				alarm.setSt(dto.st);
				alarm.setVal(dto.val);
				alarm.setLng(dto.lng);
				alarm.setLat(dto.lat);
				alarm.setAlt(dto.alt);
				alarm.setSp(dto.sp);
				alarm.setD(dto.d);
				alarm.setA(dto.a);
				alarm.setS(dto.s);
				alarm.setM(dto.m);
				alarm.setOil(dto.oil);
				alarm.setVss(dto.vss);
				alarm.setOvt(dto.ovt);
				alarm.setOid(dto.oid);
				alarm.setIot(dto.iot);
				alarm.setIid(dto.iid);
				alarm.setIof(dto.iof);
				alarm.setRid(dto.rid);
				alarm.setRt(dto.rt);
				alarm.setRf(dto.rf);
				alarm.setAid(dto.aid);
				alarm.setExs(dto.exs);
				alarm.setIos(dto.ios);
				alarm.setAd0(dto.ad0);
				alarm.setAd0(dto.ad1);
				alarm.setNet(dto.net);
				alarm.setSat(dto.sat);
				alarm.setFrom(dto.from);
				alarm.setUserName(dto.userName);
				alarm.setUserTime(dto.userTime);
				alarm.setUserconfirm(dto.userconfirm);
				alarm.setUserMethod(dto.userMethod);
				alarm.setUserRemark(dto.userRemark);
				alarm.setEditTime(dto.editTime);
				query.rows.add(alarm);
			}
		}

		return query;
	}

	public ProcessAlarmOnce getProcessAlarm(String alarmId) {
		Date date = alarmDao.getAlarmTimestamp(alarmId);
		ProcessAlarmOnce alarm = new ProcessAlarmOnce();
		alarm.setAlarmId(alarmId);
		alarm.setAlarmTimestamp(new Timestamp(date.getTime()));
		return alarm;
	}

	@ServiceMethod(id = "center.alarm.processOnce", pid = "center.alarm", name = "单次处理报警")
	@Transactional
	public void processOnce(String alarmId, Timestamp alarmTimestamp, String userMethod, String userRemark, String userId, String userName) {
		ProcessOnceDto dto = new ProcessOnceDto();
		dto.alarmId = alarmId;
		dto.alarmTimestamp = alarmTimestamp;
		dto.userMethod = userMethod;
		dto.userRemark = userRemark;
		dto.userId = userId;
		dto.userName = userName;

		alarmDao.processOnce(dto);
	}

	@ServiceMethod(id = "center.alarm.processAll", pid = "center.alarm", name = "处理全部报警")
	@Transactional
	public void processAll(String[] deviceNumbers, String userMethod, String userRemark, String userId, String userName) {
		for (String deviceNumber : deviceNumbers) {
			ProcessAllDto dto = new ProcessAllDto();
			dto.deviceNumber = deviceNumber;
			dto.userMethod = userMethod;
			dto.userRemark = userRemark;
			dto.userId = userId;
			dto.userName = userName;
			alarmDao.processAll(dto);
		}
	}

	public List<Alarm> unhandled(String userId) {
		UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
		if (target == null)
			return null;
		List<Alarm> alarms = new ArrayList<Alarm>();

		List<AlarmDto> dtos = alarmDao.untreatedByDeviceNumbers(new ArrayList<String>(target.getDevices().keySet()));

		for (AlarmDto dto : dtos) {
			Alarm alarm = new Alarm();
			alarm.setId(dto.id);
			alarm.setNa(dto.na);
			alarm.setDn(dto.dn);
			alarm.setGt(dto.gt);
			alarm.setSt(dto.st);
			alarm.setVal(dto.val);
			alarm.setLng(dto.lng);
			alarm.setLat(dto.lat);
			alarm.setAlt(dto.alt);
			alarm.setSp(dto.sp);
			alarm.setD(dto.d);
			alarm.setA(dto.a);
			alarm.setS(dto.s);
			alarm.setM(dto.m);
			alarm.setOil(dto.oil);
			alarm.setVss(dto.vss);
			alarm.setOvt(dto.ovt);
			alarm.setOid(dto.oid);
			alarm.setIot(dto.iot);
			alarm.setIid(dto.iid);
			alarm.setIof(dto.iof);
			alarm.setRid(dto.rid);
			alarm.setRt(dto.rt);
			alarm.setRf(dto.rf);
			alarm.setAid(dto.aid);
			alarm.setExs(dto.exs);
			alarm.setIos(dto.ios);
			alarm.setAd0(dto.ad0);
			alarm.setAd0(dto.ad1);
			alarm.setNet(dto.net);
			alarm.setSat(dto.sat);
			alarm.setFrom(dto.from);
			alarm.setUserName(dto.userName);
			alarm.setUserTime(dto.userTime);
			alarm.setUserconfirm(dto.userconfirm);
			alarm.setUserMethod(dto.userMethod);
			alarm.setUserRemark(dto.userRemark);
			alarm.setEditTime(dto.editTime);

			alarms.add(alarm);
		}
		return alarms;

	}
}
