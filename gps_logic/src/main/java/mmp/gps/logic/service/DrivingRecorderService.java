package mmp.gps.logic.service;


import mmp.gps.domain.drivingRecorder.*;
import mmp.gps.domain.log.LocateLog;
import mmp.gps.logic.dao.IDrivingRecorderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DrivingRecorderService {

    @Autowired
    private IDrivingRecorderDao drivingRecorderDao;


    @Transactional
    public void updateVersion(String number, String version) {
        if (!this.drivingRecorderDao.exit(number)) {
            this.drivingRecorderDao.create(number);
        }

        this.drivingRecorderDao.updateVersion(number, version);
    }

    @Transactional
    public void updateLicense(String number, String license) {
        if (!this.drivingRecorderDao.exit(number)) {
            this.drivingRecorderDao.create(number);
        }

        this.drivingRecorderDao.updateLicense(number, license);
    }

    @Transactional
    public void updateMeleage(String number, double init, double total) {
        if (!this.drivingRecorderDao.exit(number)) {
            this.drivingRecorderDao.create(number);
        }

        this.drivingRecorderDao.updateMeleage(number, init, total);
    }

    @Transactional
    public void updatePulse(String number, int pulse) {
        if (!this.drivingRecorderDao.exit(number)) {
            this.drivingRecorderDao.create(number);
        }

        this.drivingRecorderDao.updatePulse(number, pulse);
    }

    @Transactional
    public void updateVehicleInfo(String number, String vehicleIdcode, String plateNumber, String category) {
        if (!this.drivingRecorderDao.exit(number)) {
            this.drivingRecorderDao.create(number);
        }

        this.drivingRecorderDao.updateVehicleInfo(number, vehicleIdcode, plateNumber, category);
    }

    @Transactional
    public void updateStatusSetting(String number, String name0, String name1, String name2, String name3, String name4, String name5, String name6, String name7) {
        if (!this.drivingRecorderDao.exit(number)) {
            this.drivingRecorderDao.create(number);
        }

        this.drivingRecorderDao.updateStatusSetting(number, name0, name1, name2, name3, name4, name5, name6, name7);
    }

    @Transactional
    public void updateID(String number, String ccc, String model, String time, long sn) {
        if (!this.drivingRecorderDao.exit(number)) {
            this.drivingRecorderDao.create(number);
        }

        this.drivingRecorderDao.updateID(number, ccc, model, time, sn);
    }

    @Transactional
    public void saveAccidentDoubt(List accidentDoubts) {
        ArrayList list = new ArrayList();
        Iterator var3 = accidentDoubts.iterator();

        while (var3.hasNext()) {
            AccidentDoubt log = (AccidentDoubt) var3.next();
            AccidentDoubtDto dto = new AccidentDoubtDto();
            dto.number = log.getNumber();
            dto.time = log.getTime();
            dto.license = log.getLicense();
            dto.lng = log.getLng();
            dto.lat = log.getLat();
            dto.alt = log.getAlt();
            dto.content = log.getContent();
            list.add(dto);
        }

        this.drivingRecorderDao.saveAccidentDoubt(list);
    }

    @Transactional
    public void saveTimeoutDriving(List timeouts) {
        ArrayList list = new ArrayList();
        Iterator var3 = timeouts.iterator();

        while (var3.hasNext()) {
            TimeoutDriving log = (TimeoutDriving) var3.next();
            TimeoutDrivingDto dto = new TimeoutDrivingDto();
            dto.number = log.getNumber();
            dto.startTime = log.getStartTime();
            dto.endTime = log.getEndTime();
            dto.license = log.getLicense();
            dto.startLat = log.getStartLat();
            dto.startLng = log.getStartLng();
            dto.startAlt = log.getStartAlt();
            dto.endLat = log.getEndLat();
            dto.endLng = log.getEndLng();
            dto.endAlt = log.getEndAlt();
            list.add(dto);
        }

        this.drivingRecorderDao.saveTimeoutDriving(list);
    }

    @Transactional
    public void saveSpeedLog(List logs) {
        ArrayList list = new ArrayList();
        Iterator var3 = logs.iterator();

        while (var3.hasNext()) {
            SpeedLog log = (SpeedLog) var3.next();
            SpeedLogDto dto = new SpeedLogDto();
            dto.number = log.getNumber();
            dto.time = log.getTime();
            dto.content = log.getContent();
            list.add(dto);
        }

        this.drivingRecorderDao.saveSpeedLog(list);
    }

    @Transactional
    public void saveLocateLog(List logs) {
        ArrayList list = new ArrayList();
        Iterator var3 = logs.iterator();

        while (var3.hasNext()) {
            LocateLog log = (LocateLog) var3.next();
            LocateLogDto dto = new LocateLogDto();
            dto.number = log.getNumber();
            dto.time = log.getTime();
            dto.lat = log.getLat();
            dto.lng = log.getLng();
            dto.alt = log.getAlt();
            list.add(dto);
        }

        this.drivingRecorderDao.saveLocateLog(list);
    }

    @Transactional
    public void saveLoginLogoutLog(List logs) {
        ArrayList list = new ArrayList();
        Iterator var3 = logs.iterator();

        while (var3.hasNext()) {
            LoginLogoutLog log = (LoginLogoutLog) var3.next();
            LoginLogoutLogDto dto = new LoginLogoutLogDto();
            dto.number = log.getNumber();
            dto.license = log.getLicense();
            dto.time = log.getTime();
            dto.event = log.getEvent();
            list.add(dto);
        }

        this.drivingRecorderDao.saveLoginLogoutLog(list);
    }

    @Transactional
    public void savePowerSupplyLog(List logs) {
        ArrayList list = new ArrayList();
        Iterator var3 = logs.iterator();

        while (var3.hasNext()) {
            PowerSupplyLog log = (PowerSupplyLog) var3.next();
            PowerSupplyLogDto dto = new PowerSupplyLogDto();
            dto.number = log.getNumber();
            dto.time = log.getTime();
            dto.event = log.getEvent();
            list.add(dto);
        }

        this.drivingRecorderDao.savePowerSupplyLog(list);
    }

    @Transactional
    public void saveParameterChangeLog(List logs) {
        ArrayList list = new ArrayList();
        Iterator var3 = logs.iterator();

        while (var3.hasNext()) {
            ParameterChangeLog log = (ParameterChangeLog) var3.next();
            ParameterChangeLogDto dto = new ParameterChangeLogDto();
            dto.number = log.getNumber();
            dto.time = log.getTime();
            dto.event = log.getEvent();
            list.add(dto);
        }

        this.drivingRecorderDao.saveParameterChangeLog(list);
    }

    @Transactional
    public void saveSpeedStatusLog(List logs) {
        ArrayList list = new ArrayList();
        Iterator var3 = logs.iterator();

        while (var3.hasNext()) {
            SpeedStatusLog log = (SpeedStatusLog) var3.next();
            SpeedStatusLogDto dto = new SpeedStatusLogDto();
            dto.number = log.getNumber();
            dto.start = log.getStart();
            dto.end = log.getEnd();
            dto.status = log.getStatus();
            dto.content = log.getContent();
            list.add(dto);
        }

        this.drivingRecorderDao.saveSpeedStatusLog(list);
    }

    public QueryDrivingRecorderInfoResponse fetch(String number) throws Exception {
        QueryDrivingRecorderInfoResponse response = new QueryDrivingRecorderInfoResponse();
        DrivingRecorderInfoDto dto = this.drivingRecorderDao.fetch(number);
        response.setNumber(dto.number);
        response.setRevision(dto.revision);
        response.setLicense(dto.license);
        response.setInitialMileage(dto.initialMileage);
        response.setTotalMileage(dto.totalMileage);
        response.setPulseFactor(dto.pulseFactor);
        response.setVehicleIdCode(dto.vehicleIdCode);
        response.setPlateNumber(dto.plateNumber);
        response.setPlateType(dto.plateType);
        response.setCccCode(dto.cccCode);
        response.setModel(dto.model);
        response.setProductionDate(dto.productionDate);
        response.setSerialNumber(dto.serialNumber);
        response.setD0(dto.d0);
        response.setD1(dto.d1);
        response.setD2(dto.d2);
        response.setD3(dto.d3);
        response.setD4(dto.d4);
        response.setD5(dto.d5);
        response.setD6(dto.d6);
        response.setD7(dto.d7);
        return response;
    }

    public QueryAccidentDoubtLogResponse queryAccidentDoubtLog(QueryAccidentDoubtLogRequest request) throws Exception {
        QueryAccidentDoubtLogResponse response = new QueryAccidentDoubtLogResponse();
        int total = this.drivingRecorderDao.queryAccidentDoubtLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.queryAccidentDoubtLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                AccidentDoubtDto dto = (AccidentDoubtDto) var5.next();
                AccidentDoubt info = new AccidentDoubt();
                info.setNumber(dto.number);
                info.setTime(dto.time);
                info.setLat(dto.lat);
                info.setLng(dto.lng);
                info.setAlt(dto.alt);
                info.setContent(dto.content);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public QueryPowerLogResponse queryPowerLog(QueryPowerLogRequest request) throws Exception {
        QueryPowerLogResponse response = new QueryPowerLogResponse();
        int total = this.drivingRecorderDao.queryPowerLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.queryPowerLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                PowerSupplyLogDto dto = (PowerSupplyLogDto) var5.next();
                PowerSupplyLog info = new PowerSupplyLog();
                info.setNumber(dto.number);
                info.setTime(dto.time);
                info.setEvent(dto.event);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public QueryTimeoutLogResponse queryTimeoutLog(QueryTimeoutLogRequest request) throws Exception {
        QueryTimeoutLogResponse response = new QueryTimeoutLogResponse();
        int total = this.drivingRecorderDao.queryTimeoutLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.queryTimeoutLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                TimeoutDrivingDto dto = (TimeoutDrivingDto) var5.next();
                TimeoutDriving info = new TimeoutDriving();
                info.setNumber(dto.number);
                info.setStartTime(dto.startTime);
                info.setEndTime(dto.endTime);
                info.setLicense(dto.license);
                info.setStartLng(dto.startLng);
                info.setStartLat(dto.startLat);
                info.setStartAlt(dto.startAlt);
                info.setEndLng(dto.endLng);
                info.setEndLat(dto.endLat);
                info.setEndAlt(dto.endAlt);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public QueryParameterLogResponse queryParameterLog(QueryParameterLogRequest request) throws Exception {
        QueryParameterLogResponse response = new QueryParameterLogResponse();
        int total = this.drivingRecorderDao.queryParameterLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.queryParameterLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                ParameterChangeLogDto dto = (ParameterChangeLogDto) var5.next();
                ParameterChangeLog info = new ParameterChangeLog();
                info.setNumber(dto.number);
                info.setTime(dto.time);
                info.setEvent(dto.event);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public QueryLoginLogResponse queryLoginLog(QueryLoginLogRequest request) throws Exception {
        QueryLoginLogResponse response = new QueryLoginLogResponse();
        int total = this.drivingRecorderDao.queryLoginLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.queryLoginLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                LoginLogoutLogDto dto = (LoginLogoutLogDto) var5.next();
                LoginLogoutLog info = new LoginLogoutLog();
                info.setNumber(dto.number);
                info.setTime(dto.time);
                info.setEvent(dto.event);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public QuerySpeedStatusLogResponse querySpeedStatusLog(QuerySpeedStatusLogRequest request) throws Exception {
        QuerySpeedStatusLogResponse response = new QuerySpeedStatusLogResponse();
        int total = this.drivingRecorderDao.querySpeedStatusLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.querySpeedStatusLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                SpeedStatusLogDto dto = (SpeedStatusLogDto) var5.next();
                SpeedStatusLog info = new SpeedStatusLog();
                info.setNumber(dto.number);
                info.setStart(dto.start);
                info.setEnd(dto.end);
                info.setStatus(dto.status);
                info.setContent(dto.content);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public QueryLocateLogResponse queryLocateLog(QueryLocateLogRequest request) throws Exception {
        QueryLocateLogResponse response = new QueryLocateLogResponse();
        int total = this.drivingRecorderDao.queryLocateLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.queryLocateLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                LocateLogDto dto = (LocateLogDto) var5.next();
                LocateLog info = new LocateLog();
                info.setNumber(dto.number);
                info.setTime(dto.time);
                info.setLng(dto.lng);
                info.setLat(dto.lat);
                info.setAlt(dto.alt);
                info.setSpeed(dto.speed);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public QuerySpeedLogResponse querySpeedLog(QuerySpeedLogRequest request) throws Exception {
        QuerySpeedLogResponse response = new QuerySpeedLogResponse();
        int total = this.drivingRecorderDao.querySpeedLogPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.drivingRecorderDao.querySpeedLogPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                SpeedLogDto dto = (SpeedLogDto) var5.next();
                SpeedLog info = new SpeedLog();
                info.setNumber(dto.number);
                info.setTime(dto.time);
                info.setContent(dto.content);
                response.getRows().add(info);
            }
        }

        return response;
    }
}
