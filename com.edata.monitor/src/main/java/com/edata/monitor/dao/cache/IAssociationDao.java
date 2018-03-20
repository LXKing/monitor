package com.edata.monitor.dao.cache;

import java.util.List;

public interface IAssociationDao {

	List<MonitorTargetDto> loadUserMonitors(String userId) throws Exception;

	List<MonitorTargetDto> loadSubUserMonitors(String companyId) throws Exception;

	List<MonitorTargetDto> loadUserVehicles(List<String> motorcades) throws Exception;

}
