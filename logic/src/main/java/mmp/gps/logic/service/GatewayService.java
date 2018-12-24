package mmp.gps.logic.service;

import mmp.gps.domain.device.Device;
import mmp.gps.domain.gateway.*;
import mmp.gps.domain.statistics.DeviceOnlineOfflineReport;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.dao.IGatewayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GatewayService {

    @Autowired
    private IGatewayDao gatewayDao;


    public void save(BulkDataDto bulk) {
        if (bulk != null) {
            if (bulk.tracks != null && bulk.tracks.size() > 0) {
                this.saveTracks(bulk.tracks);
            }

            if (bulk.faults != null && bulk.faults.size() > 0) {
                this.saveFaults(bulk.faults);
            }

            if (bulk.flows != null && bulk.flows.size() > 0) {
                this.saveFlows(bulk.flows);
            }

            if (bulk.dataLogs != null && bulk.dataLogs.size() > 0) {
                this.saveDataLogs(bulk.dataLogs);
            }

            if (bulk.trips != null && bulk.trips.size() > 0) {
                this.saveTrips(bulk.trips);
            }

            if (bulk.instructResults != null && bulk.instructResults.size() > 0) {
                this.saveInstructResults(bulk.instructResults);
            }

        }
    }

    @Transactional
    private void saveTracks(List tracks) {
        this.gatewayDao.saveTracks(tracks);
    }

    @Transactional
    private void saveFaults(List faults) {
        this.gatewayDao.saveFaults(faults);
    }

    @Transactional
    private void saveFlows(List flows) {
        this.gatewayDao.saveFlows(flows);
    }

    @Transactional
    private void saveDataLogs(List dataLogs) {
        this.gatewayDao.saveDataLogs(dataLogs);
    }

    @Transactional
    private void saveTrips(List trips) {
        this.gatewayDao.saveTrips(trips);
    }

    @Transactional
    private void saveInstructResults(List instructResults) {
        this.gatewayDao.saveInstructResults(instructResults);
    }

    @Transactional
    public void save(InstructStatusRequest request) {
        InstructResultDto dto = new InstructResultDto();
        dto.id = request.getId();
        dto.result = request.getResult();
        this.gatewayDao.saveInstructStatus(dto);
    }

    @Transactional
    public void save(MultimediaSaveRequest request) {
        MultimediaDto dto = new MultimediaDto();
        dto.id = request.getId();
        dto.alarms = request.getAlarms();
        dto.altitude = request.getAltitude();
        dto.angle = request.getAngle();
        dto.channelId = request.getChannelId();
        dto.data = request.getData();
        dto.eventType = request.getEventType();
        dto.formatType = request.getFormatType();
        dto.lat = request.getLat();
        dto.lng = request.getLng();
        dto.mediaId = request.getMediaId();
        dto.mediaType = request.getMediaType();
        dto.number = request.getNumber();
        dto.speed = request.getSpeed();
        dto.status = request.getStatus();
        dto.time = request.getTime();
        this.gatewayDao.saveMultimedia(dto);
    }

    @Transactional
    public void save(DeviceOnlineOfflineReport report) {
        Device device = Devices.getCurrent().get(report.number);
        if (device != null && report.on) {
            device.setOnline();
        }

        DeviceOnlineOfflineReportDto dto = new DeviceOnlineOfflineReportDto();
        dto.number = report.number;
        dto.offTime = report.offTime;
        dto.on = report.on;
        dto.onTime = report.onTime;
        dto.time = report.time;
        this.gatewayDao.saveDeviceOnlineOfflineReport(dto);
    }
}
