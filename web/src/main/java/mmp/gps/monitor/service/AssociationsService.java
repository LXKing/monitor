package mmp.gps.monitor.service;

import mmp.gps.monitor.dao.IAssociationDao;
import mmp.gps.domain.monitor.MonitorTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociationsService {
    @Autowired
    private IAssociationDao associationDao;

    /**
     * 获取用户所有监控对象
     *
     * @throws Exception
     */
    public List<MonitorTarget> getMonitorTargets(String userId) throws Exception {
        // List<MonitorTarget> monitorTargets = new ArrayList<MonitorTarget>();
        List<MonitorTarget> mt = associationDao.loadUserMonitors(userId);

        // 获取监控对象以及其子公司监控对象
        List<MonitorTarget> monitorTargets = new ArrayList<MonitorTarget>();
        // monitorTargets.stream().filter(monitorTarget -> monitorTarget.getType() == 2).forEach(monitorTarget ->
        //         loadSubUserMonitors(monitorTargets, monitorTarget.getId()));


        for (MonitorTarget monitorTarget : mt) {
            monitorTargets.add(monitorTarget);
            if (monitorTarget.getType() == 2) { // 获取子企业的所有车队与子企业
                loadSubUserMonitors(monitorTargets, monitorTarget.getId());
            }
        }


        // for (MonitorTargetDto dto : roots) {
        //     MonitorTarget target = new MonitorTarget();
        //     target.setId(dto.id);
        //     target.setPid(dto.pid);
        //     target.setType(dto.type);
        //     target.setDeviceNumber(dto.deviceNumber);
        //     target.setName(dto.name);
        //     monitorTargets.add(target);
        //
        //     if (dto.type == 2) {// 获取子企业的所有车队与子企业
        //         loadSubUserMonitors(monitorTargets, dto.id);
        //     }
        // }

        // 获取车队所有车辆
        List<String> motorcades = new ArrayList<String>();
        for (MonitorTarget target : monitorTargets) {
            if (target.getType() == 1) {
                motorcades.add(target.getId());
            }
        }

        if (motorcades.size() > 0) {
            List<MonitorTarget> vehicles = associationDao.loadUserVehicles(motorcades);
            monitorTargets.addAll(vehicles);


        }

        return monitorTargets;
    }

    private void loadSubUserMonitors(List<MonitorTarget> monitorTargets, String companyId) {
        List<MonitorTarget> sub;
        try {
            sub = associationDao.loadSubUserMonitors(companyId);
            for (MonitorTarget monitorTarget : sub) {
                monitorTargets.add(monitorTarget);
                if (monitorTarget.getType() == 2) {// 获取子企业的所有车队与子企业
                    loadSubUserMonitors(monitorTargets, monitorTarget.getId());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // List<MonitorTargetDto> sub = associationDao.loadSubUserMonitors(companyId);
        // for (MonitorTargetDto dto : sub) {
        //     MonitorTarget target = new MonitorTarget();
        //     target.setId(dto.id);
        //     target.setPid(dto.pid);
        //     target.setType(dto.type);
        //     target.setDeviceNumber(dto.deviceNumber);
        //     target.setName(dto.name);
        //     monitorTargets.add(target);
        //
        //     if (dto.type == 2) {// 获取子企业的所有车队与子企业
        //         loadSubUserMonitors(monitorTargets, dto.id);
        //     }
        // }
    }
}
