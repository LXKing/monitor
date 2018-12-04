package mmp.gps.monitor.service;

import mmp.gps.common.util.Page;
import mmp.gps.common.enums.DrivingRecorderActions;
import mmp.gps.common.enums.MediaEventTypes;
import mmp.gps.common.enums.MediaFormatTypes;
import mmp.gps.common.enums.MediaTypes;
import mmp.gps.domain.devicedata.MultimediaRetrieval;
import mmp.gps.domain.devicedata.PhotoInfo;
import mmp.gps.domain.devicedata.PhotoInfoDto;
import mmp.gps.domain.drivingRecorder.DrivingRecorderInfo;
import mmp.gps.domain.drivingRecorder.DrivingRecorderInfoDto;
import mmp.gps.domain.locate.*;
import mmp.gps.domain.log.*;
import mmp.gps.monitor.dao.IDeviceDataDao;
import mmp.gps.monitor.godp.IGodpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceDataService {
    @Autowired
    private IDeviceDataDao deviceDataDao;
    @Autowired
    private IGodpDao godpDao;

    public Page<PhotoInfo> queryMultimedia(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<PhotoInfoDto> page = godpDao.queryMultimedia(deviceNumber, start, end, pageIndex, pageSize);
        Page<PhotoInfo> query = new Page<PhotoInfo>();
        query.total = page.total;
        for (PhotoInfoDto dto : page.rows) {
            PhotoInfo info = new PhotoInfo();
            info.setId(dto.id);
            info.setGt(dto.gt);
            info.setMediaId(dto.mediaId);
            info.setMediaType(MediaTypes.getName(dto.mediaType));
            info.setFormatType(MediaFormatTypes.getName(dto.formatType));
            info.setEventType(MediaEventTypes.getName(dto.eventType));
            info.setChannelId(dto.channelId);
            info.setLng(dto.lng);
            info.setLat(dto.lat);
            info.setSp(dto.sp);
            info.setD(dto.d);
            info.setA(dto.a);
            info.setS(dto.s);

            query.rows.add(info);
        }
        return query;
    }

    public byte[] readMultimedia(String id) throws Exception {
        return godpDao.readMultimedia(id);
    }

    public Page<MultimediaEventReport> queryMultimediaEventReport(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) {
        int total = deviceDataDao.queryMultimediaEventReportPageCount(deviceNumber, start, end);
        Page<MultimediaEventReport> query = new Page<MultimediaEventReport>();
        query.total = total;

        if (total > 0) {
            List<MultimediaEventReportDto> rows = deviceDataDao.queryMultimediaEventReportPageDetail(deviceNumber, start, end, (pageIndex - 1) * pageSize, pageSize);

            for (MultimediaEventReportDto dto : rows) {
                MultimediaEventReport report = new MultimediaEventReport();
                report.setId(dto.id);
                report.setNumber(dto.number);
                report.setTime(dto.time);
                report.setMediaId(dto.mediaId);
                report.setMediaType(MediaTypes.getName(dto.mediaType));
                report.setFormatType(MediaFormatTypes.getName(dto.formatType));
                report.setEventType(MediaEventTypes.getName(dto.eventType));
                report.setChannelId(dto.channelId);

                query.rows.add(report);
            }
        }
        return query;
    }

    public Page<MultimediaRetrieval> queryMultimediaDataRetrieval(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) {
        int total = deviceDataDao.queryMultimediaDataRetrievalPageCount(deviceNumber, start, end);
        Page<MultimediaRetrieval> query = new Page<MultimediaRetrieval>();
        query.total = total;

        if (total > 0) {
            List<MultimediaRetrievalDto> rows = deviceDataDao.queryMultimediaDataRetrievalPageDetail(deviceNumber, start, end, (pageIndex - 1) * pageSize, pageSize);

            for (MultimediaRetrievalDto dto : rows) {
                MultimediaRetrieval row = new MultimediaRetrieval();
                row.setNumber(dto.number);
                row.setMediaId(dto.mediaId);
                row.setMediaType(MediaTypes.getName(dto.mediaType));
                row.setChannelId(dto.channelId);
                row.setEventType(MediaEventTypes.getName(dto.eventType));
                row.setA(dto.alarms);
                row.setS(dto.status);
                row.setLat(dto.lat);
                row.setLng(dto.lng);
                row.setAlt(dto.altitude);
                row.setSp(dto.speed);
                row.setD(dto.angle);
                row.setGt(dto.gpstime);
                query.rows.add(row);
            }
        }
        return query;
    }

    public Object queryDeviceEventReport(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) {
        int total = deviceDataDao.queryDeviceEventReportPageCount(deviceNumber, start, end);
        Page<DeviceEventReport> query = new Page<DeviceEventReport>();
        query.total = total;

        if (total > 0) {
            List<DeviceEventReportDto> rows = deviceDataDao.queryDeviceEventReportPageDetail(deviceNumber, start, end, (pageIndex - 1) * pageSize, pageSize);

            for (DeviceEventReportDto dto : rows) {
                DeviceEventReport row = new DeviceEventReport();
                row.setContent(dto.content);
                row.setEventId(dto.eventId);
                row.setId(dto.id);
                row.setNumber(dto.number);
                row.setTime(dto.time);
                query.rows.add(row);
            }
        }
        return query;
    }

    public Object deviceUpgradeResultReport(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        int total = deviceDataDao.queryDeviceUpgradeResultReportPageCount(deviceNumber, start, end);
        Page<UpgradeResultReport> query = new Page<UpgradeResultReport>();
        query.total = total;

        if (total > 0) {
            List<UpgradeResultReportDto> rows = deviceDataDao.queryDeviceUpgradeResultReportPageDetail(deviceNumber, start, end, (pageIndex - 1) * pageSize, pageSize);

            for (UpgradeResultReportDto dto : rows) {
                UpgradeResultReport row = new UpgradeResultReport();
                row.setId(dto.id);
                row.setNumber(dto.number);
                row.setTime(dto.time);
                row.setType(dto.type);
                row.setResult(dto.result);
                query.rows.add(row);
            }
        }
        return query;
    }

    /**
     * 查询行驶记录仪信息
     */
    public Object querydrivingRecorder(String deviceNumber) throws Exception {
        DrivingRecorderInfoDto dto = godpDao.querydrivingRecorder(deviceNumber);
        DrivingRecorderInfo info = new DrivingRecorderInfo();
        info.setNumber(dto.number);
        info.setRevision(dto.revision);
        info.setLicense(dto.license);
        info.setInitialMileage(dto.initialMileage);
        info.setTotalMileage(dto.totalMileage);
        info.setPulseFactor(dto.pulseFactor);
        info.setVehicleIdCode(dto.vehicleIdCode);
        info.setPlateNumber(dto.plateNumber);
        info.setPlateType(dto.plateType);
        info.setCccCode(dto.cccCode);
        info.setModel(dto.model);
        info.setProductionDate(dto.productionDate);
        info.setSerialNumber(dto.serialNumber);
        info.setD0(dto.d0);
        info.setD1(dto.d1);
        info.setD2(dto.d2);
        info.setD3(dto.d3);
        info.setD4(dto.d4);
        info.setD5(dto.d5);
        info.setD6(dto.d6);
        info.setD7(dto.d7);
        return info;
    }

    /**
     * 查询行驶记录事故疑点数据
     */
    public Object queryAccidentDoubtLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<AccidentDoubtLogDto> page = godpDao.queryAccidentDoubtLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<AccidentDoubtLog> query = new Page<AccidentDoubtLog>();
        query.total = page.total;
        for (AccidentDoubtLogDto dto : page.rows) {
            AccidentDoubtLog row = new AccidentDoubtLog();
            row.setLicense(dto.license);
            row.setTime(dto.time);
            row.setLat(dto.lat);
            row.setLng(dto.lng);
            row.setAlt(dto.alt);
            List<Short> data = new ArrayList<Short>();
            for (byte b : dto.content) {
                data.add(new Short(b));
            }
            row.setContent(data);
            query.rows.add(row);
        }
        return query;
    }

    /**
     * 查询行驶记录供电日志
     */
    public Object queryPowerLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<PowerLogDto> page = godpDao.queryPowerLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<PowerLog> query = new Page<PowerLog>();
        query.total = page.total;
        for (PowerLogDto dto : page.rows) {
            PowerLog row = new PowerLog();
            row.setNumber(dto.number);
            row.setTime(dto.time);
            row.setEvent(dto.event == 1 ? "通电" : "断电");
            query.rows.add(row);
        }
        return query;
    }

    /**
     * 查询行驶记录超时驾驶日志
     */
    public Object queryTimeoutLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<TimeoutLogDto> page = godpDao.queryTimeoutLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<TimeoutLog> query = new Page<TimeoutLog>();
        query.total = page.total;
        for (TimeoutLogDto dto : page.rows) {
            TimeoutLog row = new TimeoutLog();
            row.setNumber(dto.number);
            row.setStartTime(dto.startTime);
            row.setEndTime(dto.endTime);
            row.setLicense(dto.license);
            row.setStartLng(dto.startLng);
            row.setStartLat(dto.startLat);
            row.setStartAlt(dto.startAlt);
            row.setEndLng(dto.endLng);
            row.setEndLat(dto.endLat);
            row.setEndAlt(dto.endAlt);

            query.rows.add(row);
        }
        return query;
    }

    public Object queryParameterLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<ParameterLogDto> page = godpDao.queryParameterLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<ParameterLog> query = new Page<ParameterLog>();
        query.total = page.total;
        for (ParameterLogDto dto : page.rows) {
            ParameterLog row = new ParameterLog();
            row.setNumber(dto.number);
            row.setTime(dto.time);
            row.setEvent(DrivingRecorderActions.getName(dto.event));

            query.rows.add(row);
        }
        return query;
    }

    /**
     * 查询行驶记录驾驶员登签日志
     */
    public Object queryLoginLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<LoginLogDto> page = godpDao.queryLoginLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<LoginLog> query = new Page<LoginLog>();
        query.total = page.total;

        for (LoginLogDto dto : page.rows) {
            LoginLog row = new LoginLog();
            row.setNumber(dto.number);
            row.setTime(dto.time);
            row.setLicense(dto.license);
            if (dto.license != null && !dto.license.trim().isEmpty())
                row.setDriver(deviceDataDao.getDriverName(dto.license.trim()));
            row.setEvent(dto.event == 1 ? "登录" : "退出");

            query.rows.add(row);
        }

        return query;
    }

    /**
     * 查询行驶记录速度状态日志
     */
    public Object querySpeedStatusLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<SpeedStatusLogDto> page = godpDao.querySpeedStatusLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<SpeedStatusLog> query = new Page<SpeedStatusLog>();
        query.total = page.total;
        for (SpeedStatusLogDto dto : page.rows) {
            SpeedStatusLog row = new SpeedStatusLog();
            row.setStartTime(dto.startTime);
            row.setEndTime(dto.endTime);
            row.setState(dto.state == 1 ? "正常" : "异常");
            List<Short> data = new ArrayList<Short>();
            for (byte b : dto.content) {
                data.add(new Short(b));
            }
            row.setContent(data);
            query.rows.add(row);
        }
        return query;
    }

    /**
     * 查询行驶记录位置日志
     */
    public Object queryLocateLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<LocateLogDto> page = godpDao.queryLocateLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<LocateLog> query = new Page<LocateLog>();
        query.total = page.total;
        for (LocateLogDto dto : page.rows) {
            LocateLog row = new LocateLog();
            row.setNumber(dto.number);
            row.setTime(dto.time);
            row.setLng(dto.lng);
            row.setLat(dto.lat);
            row.setAlt(dto.alt);
            row.setSpeed(dto.speed);

            query.rows.add(row);
        }
        return query;
    }

    /**
     * 查询行驶记录速度日志
     */
    public Object querySpeedLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<SpeedLogDto> page = godpDao.querySpeedLog(deviceNumber, start, end, pageIndex, pageSize);
        Page<SpeedLog> query = new Page<SpeedLog>();
        query.total = page.total;
        for (SpeedLogDto dto : page.rows) {
            SpeedLog row = new SpeedLog();
            row.setTime(dto.time);
            List<Short> data = new ArrayList<Short>();
            for (byte b : dto.content) {
                data.add(new Short(b));
            }
            row.setContent(data);

            query.rows.add(row);
        }
        return query;
    }
}
