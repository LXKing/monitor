package mmp.gps.monitor.dao;

import mmp.gps.common.util.KeyValue;
import mmp.gps.domain.company.CompanyInfo;
import mmp.gps.domain.vehicle.VehicleInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IOverviewDao {
    int companyServiceOverview(List<String> companyIds);

    int companyServiceOverviewDays(KeyValue daysAndCompanyIds);

    int vehicleServiceOverview(List<String> deviceNumbers);

    int vehicleServiceOverviewDays(KeyValue daysAndDeviceNumbers);

    int vehicleCheckOverview(List<String> vehicleIds);

    int vehicleCheckOverviewDays(KeyValue daysAndVehicleIds);

    int vehicleMaintainOverview(List<String> vehicleIds);

    int vehicleMaintainOverviewDays(KeyValue daysAndVehicleIds);

    List<CompanyInfo> companyServiceExpired(List<String> companyIds);

    List<CompanyInfo> companyServiceExpiredDays(KeyValue daysAndCompanyIds);

    List<VehicleInfoDto> vehicleServiceExpired(List<String> vehicleIds);

    List<VehicleInfoDto> vehicleServiceExpiredDays(KeyValue daysAndVehicleIds);

    List<VehicleInfoDto> vehicleMaintainExpired(List<String> vehicleIds);

    List<VehicleInfoDto> vehicleMaintainExpiredDays(KeyValue daysAndVehicleIds);

    List<VehicleInfoDto> vehicleAnnualSurveyExpired(List<String> vehicleIds);

    List<VehicleInfoDto> vehicleAnnualSurveyExpiredDays(KeyValue daysAndVehicleIds);
}
