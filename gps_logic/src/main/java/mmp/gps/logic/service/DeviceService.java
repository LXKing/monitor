package mmp.gps.logic.service;

import mmp.gps.domain.device.*;
import mmp.gps.domain.log.DataLog;
import mmp.gps.domain.log.DataLogDto;
import mmp.gps.logic.Identity;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.dao.IDeviceDao;
import mmp.gps.logic.push.Pushers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DeviceService {

    @Autowired
    private IDeviceDao deviceDao;


    @Transactional
    public Device create(String deviceNumber, int protocolKind) {
        this.deviceDao.create(deviceNumber, protocolKind);
        Device device = new Device();
        device.setNumber(deviceNumber);
        device.setProtocolKind(protocolKind);
        return device;
    }

    public List reload() {
        List list = this.deviceDao.reload();
        ArrayList devices = new ArrayList(list.size());
        Iterator var3 = list.iterator();

        while (var3.hasNext()) {
            DeviceDto dto = (DeviceDto) var3.next();
            Device device = new Device();
            device.setNumber(dto.number);
            device.setProtocolKind(dto.protocolKind);
            device.setDebugging(dto.debugging);
            device.setUpgrading(dto.upgrading);
            device.setMatching(dto.matching);
            device.setSleeping(dto.sleeping);
            device.setRepairing(dto.repairing);
            device.setPreVer(dto.preVer);
            device.setUpgradeStart(dto.upgradeStart);
            device.setCurVer(dto.curVer);
            device.setUpgradeEnd(dto.upgradeEnd);
            device.setMatchResult(dto.matchResult);
            device.setMatchTime(dto.matchTime);
            devices.add(device);
        }

        return devices;
    }

    @Transactional
    public void upgradeCompletedReport(Device device, String version) {
        device.setCurVer(version);
        device.setUpgrading(false);
        device.setUpgradeEnd(new Date());
        this.deviceDao.upgradeCompletedReport(device.getNumber(), version);
    }

    @Transactional
    public void setSleepStatus(Device device, boolean start) {
        device.setSleeping(start);
        this.deviceDao.setSleepStatus(device.getNumber(), start);
    }

    @Transactional
    public void vehicleSystemMatchReport(Device device, String result) {
        device.setMatching(false);
        device.setMatchResult(result);
        device.setMatchTime(new Date());
        this.deviceDao.vehicleSystemMatchReport(device.getNumber(), result);
    }

    public void setRepairMode(Device device, boolean start) {
        device.setRepairing(start);
        this.deviceDao.setRepairMode(device.getNumber(), start);
    }

    @Transactional
    public void setDebuggingMode(Device device, boolean enabled) {
        device.setDebugging(enabled);
        this.deviceDao.setDebuggingMode(device.getNumber(), enabled);
    }

    public DeviceCountOnlineResponse countOnline(List numbers, int timeOut) throws Exception {
        int total = numbers.size();
        int online = 0;
        Date now = new Date();
        Iterator response = numbers.iterator();

        while (response.hasNext()) {
            String number = (String) response.next();
            Device device = Devices.getCurrent().get(number);
            if (device != null && now.getTime() - device.getServerTime().getTime() < (long) (timeOut * 1000)) {
                ++online;
            }
        }

        DeviceCountOnlineResponse var9 = new DeviceCountOnlineResponse();
        var9.setTotal(total);
        var9.setOnline(online);
        var9.setOffline(total - online);
        return var9;
    }

    public DeviceCountOnlineResponse queryOnline(int timeOut) throws Exception {
        DeviceCountOnlineDto dto = Devices.getCurrent().countAllOnline(timeOut);
        DeviceCountOnlineResponse response = new DeviceCountOnlineResponse();
        response.setTotal(dto.total);
        response.setOnline(dto.online);
        response.setOffline(dto.offline);
        return response;
    }

    public DeviceStatusResponse status(String number) throws Exception {
        Device device = Devices.getCurrent().get(number);
        DeviceStatusResponse response = new DeviceStatusResponse();
        response.setCurVer(device.getCurVer());
        response.setDebugging(device.isDebugging());
        response.setMatching(device.isMatching());
        response.setMatchResult(device.getMatchResult());
        response.setMatchTime(device.getMatchTime());
        response.setNumber(number);
        response.setPreVer(device.getPreVer());
        response.setRepairing(device.isRepairing());
        response.setSleeping(device.isSleeping());
        response.setUpgradeEnd(device.getUpgradeEnd());
        response.setUpgradeStart(device.getUpgradeStart());
        response.setUpgrading(device.isUpgrading());
        return response;
    }

    public LoadDataLogResponse loadLogs(LoadDataLogRequest request) throws Exception {
        int total = this.deviceDao.loadLogPageCount(request.getNumber(), request.getStart(), request.getEnd());
        LoadDataLogResponse response = new LoadDataLogResponse();
        response.setTotal(total);
        if (total > 0) {
            List rows = this.deviceDao.loadLogPageDetail(request.getNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                DataLogDto dto = (DataLogDto) var5.next();
                DataLog log = new DataLog();
                log.setTime(dto.time);
                log.setRaw(dto.raw);
                response.getLogs().add(log);
            }
        }

        return response;
    }

    @Transactional
    public void bindUser(DeviceBindUserRequest request, Identity identity) throws Exception {
        if (request.isBind()) {
            this.deviceDao.bindUser(identity.getId(), request.getNumber());
            Pushers.getCurrent().bindDeviceInUser(identity.getId(), request.getNumber());
        } else {
            this.deviceDao.unbindUser(identity.getId(), request.getNumber());
            Pushers.getCurrent().unbindDeviceInUser(identity.getId(), request.getNumber());
        }

    }

    public Map getDeviceInUsers() throws Exception {
        HashMap map = new HashMap();
        List rows = this.deviceDao.getDeviceInUsers();

        DeviceInUserDto dto;
        Object userIds;
        for (Iterator var3 = rows.iterator(); var3.hasNext(); ((List) userIds).add(dto.userId)) {
            dto = (DeviceInUserDto) var3.next();
            userIds = (List) map.get(dto.number);
            if (userIds == null) {
                userIds = new ArrayList(3);
                map.put(dto.number, userIds);
            }
        }

        return map;
    }

    @Transactional
    public void regist(DeviceRegistrationDto dto) {
        this.deviceDao.regist(dto);
    }
}
