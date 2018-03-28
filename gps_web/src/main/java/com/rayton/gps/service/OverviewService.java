package com.rayton.gps.service;

import com.rayton.gps.cache.AssociationCache;
import com.rayton.gps.dao.baseinfo.company.CompanyInfo;
import com.rayton.gps.dao.baseinfo.vehicle.VehicleInfo;
import com.rayton.gps.dao.baseinfo.vehicle.VehicleInfoDto;
import com.rayton.gps.dao.cache.UserMonitorTarget;
import com.rayton.gps.dao.cache.association.MonitorTarget;
import com.rayton.gps.dao.locate.GroupVehicle;
import com.rayton.gps.dao.overview.*;
import com.rayton.gps.util.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class OverviewService {
    @Autowired
    private IOverviewDao overviewDao;
    @Autowired
    private LocateService locateService;

    public CompanyServiceOverview companyServiceOverview(String userId) throws Exception {
        CompanyServiceOverview overview = new CompanyServiceOverview();

        List<String> companyIds = getCompanyIds(userId);
        if (companyIds == null)
            return overview;
        overview.setTotal(companyIds.size());
        overview.setExpired(overviewDao.companyServiceOverview(companyIds));
        overview.setExpired30(overviewDao.companyServiceOverviewDays(KeyValue.of(30, companyIds)));
        overview.setExpired15(overviewDao.companyServiceOverviewDays(KeyValue.of(15, companyIds)));
        overview.setExpired7(overviewDao.companyServiceOverviewDays(KeyValue.of(7, companyIds)));

        return overview;
    }

    public VehicleServiceOverview vehicleServiceOverview(String userId) throws Exception {
        VehicleServiceOverview overview = new VehicleServiceOverview();
        List<String> deviceNumbers = getDeviceNumbers(userId);
        if (deviceNumbers == null)
            return overview;

        overview.setTotal(deviceNumbers.size());
        overview.setExpired(overviewDao.vehicleServiceOverview(deviceNumbers));
        overview.setExpired30(overviewDao.vehicleServiceOverviewDays(KeyValue.of(30, deviceNumbers)));
        overview.setExpired15(overviewDao.vehicleServiceOverviewDays(KeyValue.of(15, deviceNumbers)));
        overview.setExpired7(overviewDao.vehicleServiceOverviewDays(KeyValue.of(7, deviceNumbers)));

        return overview;
    }

    public VehicleMaintainOverview vehicleMaintainOverview(String userId) throws Exception {
        VehicleMaintainOverview overview = new VehicleMaintainOverview();
        List<String> vehicleIds = getVehicleIds(userId);
        if (vehicleIds == null)
            return overview;

        overview.setTotal(vehicleIds.size());
        overview.setExpired(overviewDao.vehicleMaintainOverview(vehicleIds));
        overview.setExpired30(overviewDao.vehicleMaintainOverviewDays(KeyValue.of(30, vehicleIds)));
        overview.setExpired15(overviewDao.vehicleMaintainOverviewDays(KeyValue.of(15, vehicleIds)));
        overview.setExpired7(overviewDao.vehicleMaintainOverviewDays(KeyValue.of(7, vehicleIds)));

        return overview;
    }

    public VehicleCheckOverview vehicleCheckOverview(String userId) throws Exception {
        VehicleCheckOverview overview = new VehicleCheckOverview();
        List<String> vehicleIds = getVehicleIds(userId);
        if (vehicleIds == null)
            return overview;

        overview.setTotal(vehicleIds.size());
        overview.setExpired(overviewDao.vehicleCheckOverview(vehicleIds));
        overview.setExpired30(overviewDao.vehicleCheckOverviewDays(KeyValue.of(30, vehicleIds)));
        overview.setExpired15(overviewDao.vehicleCheckOverviewDays(KeyValue.of(15, vehicleIds)));
        overview.setExpired7(overviewDao.vehicleCheckOverviewDays(KeyValue.of(7, vehicleIds)));

        return overview;
    }

    public VehicleStatusOverview vehicleStatusOverview(String userId) throws Exception {
        List<GroupVehicle> vehicles = locateService.loadGroupVehicles(userId, false);
        int online = 0, offline = 0, accon = 0, accoff = 0, alarm = 0;
        for (GroupVehicle vehicle : vehicles) {
            if (vehicle.getType() == 0) {
                if (vehicle.getO() == 1)
                    online++;
                else
                    offline++;

                if ((vehicle.getS() & 1) == 1)
                    accon++;
                else
                    accoff++;

                if (vehicle.getA() != 0)
                    alarm++;
            }
        }

        VehicleStatusOverview overview = new VehicleStatusOverview();
        overview.setAccoff(accoff);
        overview.setAccon(accon);
        overview.setOffline(offline);
        overview.setOnline(online);
        overview.setAlarm(alarm);

        return overview;
    }

    public List<CompanyInfo> companyServiceExpired(String userId, int days) throws Exception {
        List<CompanyInfo> result = new ArrayList<CompanyInfo>();

        List<String> companyIds = getCompanyIds(userId);
        if (companyIds == null)
            return result;

        List<CompanyInfo> list;
        if (days == 0)
            list = overviewDao.companyServiceExpired(companyIds);
        else
            list = overviewDao.companyServiceExpiredDays(KeyValue.of(days, companyIds));

        result.addAll(list);


        return result;
    }

    public List<VehicleInfo> vehicleServiceExpired(String userId, int days) throws Exception {
        List<VehicleInfo> result = new ArrayList<VehicleInfo>();
        List<String> vehicleIds = getVehicleIds(userId);
        if (vehicleIds == null)
            return result;

        List<VehicleInfoDto> list = null;
        if (days == 0)
            list = overviewDao.vehicleServiceExpired(vehicleIds);
        else
            list = overviewDao.vehicleServiceExpiredDays(KeyValue.of(days, vehicleIds));

        toVehicleInfo(result, list);
        return result;
    }

    public List<VehicleInfo> vehicleMaintainExpired(String userId, int days) throws Exception {
        List<VehicleInfo> result = new ArrayList<VehicleInfo>();
        List<String> vehicleIds = getVehicleIds(userId);
        if (vehicleIds == null)
            return result;

        List<VehicleInfoDto> list = null;
        if (days == 0)
            list = overviewDao.vehicleMaintainExpired(vehicleIds);
        else
            list = overviewDao.vehicleMaintainExpiredDays(KeyValue.of(days, vehicleIds));

        toVehicleInfo(result, list);
        return result;
    }

    public List<VehicleInfo> vehicleAnnualSurveyExpired(String userId, int days) throws Exception {
        List<VehicleInfo> result = new ArrayList<VehicleInfo>();
        List<String> vehicleIds = getVehicleIds(userId);
        if (vehicleIds == null)
            return result;

        List<VehicleInfoDto> list = null;
        if (days == 0)
            list = overviewDao.vehicleAnnualSurveyExpired(vehicleIds);
        else
            list = overviewDao.vehicleAnnualSurveyExpiredDays(KeyValue.of(days, vehicleIds));

        toVehicleInfo(result, list);
        return result;
    }

    private void toVehicleInfo(List<VehicleInfo> result, List<VehicleInfoDto> list) {
        for (VehicleInfoDto dto : list) {
            VehicleInfo info = new VehicleInfo();
            info.setId(dto.id);
            info.setDeviceNumber(dto.deviceNumber);
            info.setPhoneNumber(dto.phoneNumber);
            info.setPlateNumber(dto.plateNumber);
            info.setPlateColor(dto.plateColor);
            info.setInstallDate(dto.installDate);
            info.setAnnualSurveyDate(dto.annualSurveyDate);
            info.setServiceStartDate(dto.serviceStartDate);
            info.setServiceEndDate(dto.serviceEndDate);
            info.setNextMaintainDate(dto.nextMaintainDate);
            info.setMotorcade(dto.motorcade);
            info.setMarker(dto.marker);
            info.setRemark(dto.remark);
            result.add(info);
        }
    }

    private List<String> getCompanyIds(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        if (target == null)
            return null;

        List<MonitorTarget> motorcades = target.getMotorcades();
        if (motorcades == null || motorcades.size() <= 0)
            return null;
        List<String> companyIds = new ArrayList<String>();
        for (MonitorTarget motorcade : motorcades) {
            if (motorcade.getType() == 2)
                companyIds.add(motorcade.getId());
        }
        if (companyIds.size() <= 0)
            return null;
        return companyIds;
    }

    private List<String> getDeviceNumbers(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        if (target == null)
            return null;

        Map<String, MonitorTarget> map = target.getDevices();
        if (map == null || map.size() <= 0)
            return null;

        List<String> deviceNumbers = new ArrayList<String>(map.keySet());

        if (deviceNumbers.size() <= 0)
            return null;

        return deviceNumbers;
    }

    private List<String> getVehicleIds(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        if (target == null)
            return null;

        Map<String, MonitorTarget> map = target.getDevices();
        if (map == null || map.size() <= 0)
            return null;

        List<String> deviceNumbers = new ArrayList<String>(map.keySet());

        if (deviceNumbers.size() <= 0)
            return null;

        List<String> vehicleIds = new ArrayList<String>();
        for (Entry<String, MonitorTarget> entry : map.entrySet()) {
            MonitorTarget item = entry.getValue();
            if (item.getType() == 0)
                vehicleIds.add(item.getId());
        }
        if (vehicleIds.size() <= 0)
            return null;

        return vehicleIds;
    }
}
