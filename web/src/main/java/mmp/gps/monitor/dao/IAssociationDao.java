package mmp.gps.monitor.dao;

import mmp.gps.domain.monitor.MonitorTarget;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IAssociationDao {

    List<MonitorTarget> loadUserMonitors(String userId) throws Exception;

    List<MonitorTarget> loadSubUserMonitors(String companyId) throws Exception;

    List<MonitorTarget> loadUserVehicles(List<String> motorcades) throws Exception;


    List<MonitorTarget> getUserDevice(String userId) throws Exception;

    int deleteUserDevice(String userId, String autoId) throws Exception;

    int addUserDevice(String userId, String autoId) throws Exception;

}
