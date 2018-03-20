package com.edata.monitor.dao.overview;

import java.util.List;

import com.edata.monitor.dao.baseinfo.CompanyInfoDto;
import com.edata.monitor.dao.baseinfo.VehicleInfoDto;
import com.edata.monitor.util.KeyValue;

public interface IOverviewDao {
	int companyServiceOverview(List<String> companyIds);

	int companyServiceOverviewDays(KeyValue daysAndCompanyIds);

	int vehicleServiceOverview(List<String> deviceNumbers);

	int vehicleServiceOverviewDays(KeyValue daysAndDeviceNumbers);

	int vehicleCheckOverview(List<String> vehicleIds);

	int vehicleCheckOverviewDays(KeyValue daysAndVehicleIds);

	int vehicleMaintainOverview(List<String> vehicleIds);

	int vehicleMaintainOverviewDays(KeyValue daysAndVehicleIds);

	List<CompanyInfoDto> companyServiceExpired(List<String> companyIds);

	List<CompanyInfoDto> companyServiceExpiredDays(KeyValue daysAndCompanyIds);

	List<VehicleInfoDto> vehicleServiceExpired(List<String> vehicleIds);

	List<VehicleInfoDto> vehicleServiceExpiredDays(KeyValue daysAndVehicleIds);

	List<VehicleInfoDto> vehicleMaintainExpired(List<String> vehicleIds);

	List<VehicleInfoDto> vehicleMaintainExpiredDays(KeyValue daysAndVehicleIds);

	List<VehicleInfoDto> vehicleAnnualSurveyExpired(List<String> vehicleIds);

	List<VehicleInfoDto> vehicleAnnualSurveyExpiredDays(KeyValue daysAndVehicleIds);
}
