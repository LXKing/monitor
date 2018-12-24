package mmp.gps.monitor.service;

import mmp.gps.common.util.Page;
import mmp.gps.domain.alarm.Alarm;
import mmp.gps.domain.alarm.ProcessAlarmOnce;
import mmp.gps.domain.alarm.ProcessAllDto;
import mmp.gps.domain.alarm.ProcessOnceDto;
import mmp.gps.domain.monitor.UserMonitorTarget;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.cache.AssociationCache;
import mmp.gps.monitor.dao.IAlarmDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class AlarmService {

    @Autowired
    private IAlarmDao alarmDao;

    public List<Alarm> untreated(String deviceNumber, Integer pageIndex, Integer pageSize, Date start, Date end) {
        Map map = new HashMap();
        map.put("deviceNumber", deviceNumber);
        map.put("pageIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        map.put("start", start);
        map.put("end", end);
        List<Alarm> alarms = alarmDao.untreatedByDeviceNumber(map);


        return alarms;
    }

    public Integer getDnUntreated(String deviceNumber, Date start, Date end) {
        Integer alarms = alarmDao.getDnUntreated(deviceNumber, start, end);


        return alarms;
    }


    public Object processed(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) {
        int total = alarmDao.processPageCount(deviceNumber, start, end);
        Page<Alarm> query = new Page<Alarm>();
        query.total = total;

        if (total > 0) {
            List<Alarm> rows = alarmDao.processedPageDetail(deviceNumber, start, end, (pageIndex - 1) * pageSize, pageSize);
            query.rows.addAll(rows);
            // rows.forEach(alarm -> query.rows.add(alarm));

        }

        return query;
    }

    public ProcessAlarmOnce getProcessAlarm(String alarmId) {
        Date date = alarmDao.getAlarmTimestamp(alarmId);
        ProcessAlarmOnce alarm = new ProcessAlarmOnce();
        alarm.setAlarmId(alarmId);
        alarm.setAlarmTimestamp(new Timestamp(date.getTime()));
        return alarm;
    }


    @RequiresPermissions("center.alarm.processOnce")
    @Log(name = "单次处理报警")
    @Transactional
    public void processOnce(String alarmId, Timestamp alarmTimestamp, String userMethod, String userRemark, String userId, String userName) {
        ProcessOnceDto dto = new ProcessOnceDto();
        dto.alarmId = alarmId;
        dto.alarmTimestamp = alarmTimestamp;
        dto.userMethod = userMethod;
        dto.userRemark = userRemark;
        dto.userId = userId;
        dto.userName = userName;

        alarmDao.processOnce(dto);
    }

    @RequiresPermissions("center.alarm.processAll")
    @Log(name = "处理全部报警")
    @Transactional
    public void processAll(String[] deviceNumbers, String userMethod, String userRemark, String userId, String userName) {
        for (String deviceNumber : deviceNumbers) {
            ProcessAllDto dto = new ProcessAllDto();

            dto.deviceNumber = deviceNumber;
            dto.userMethod = userMethod;
            dto.userRemark = userRemark;
            dto.userId = userId;
            dto.userName = userName;
            alarmDao.processAll(dto);
        }
    }

    public List<Alarm> unhandled(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        if (target == null)
            return null;

        List<String> deviceNumbers = new ArrayList<String>(target.getDevices().keySet());
        List<Alarm> alarms = null;
        if (deviceNumbers.size() != 0 && deviceNumbers != null) {
            alarms = alarmDao.untreatedByDeviceNumbers(deviceNumbers);
        }

        System.out.println(alarms);
        return alarms;

    }

    public Integer getCount(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        if (target == null)
            return null;

        List<String> deviceNumbers = new ArrayList<String>(target.getDevices().keySet());

        Integer alarms = null;
        if (deviceNumbers.size() != 0 && deviceNumbers != null) {


            alarms = alarmDao.getCount(deviceNumbers);
//            alarms = alarmDao.allByDeviceNumbers(deviceNumbers);
        }

        System.out.println(alarms);
        return alarms;

    }

    public List<Alarm> all(String userId, Integer pageIndex, Integer pageSize) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        if (target == null)
            return null;

        List<String> deviceNumbers = new ArrayList<String>(target.getDevices().keySet());
        List<Alarm> alarms = null;
        if (deviceNumbers.size() != 0 && deviceNumbers != null) {
            Map map = new HashMap();
            map.put("deviceNumbers", deviceNumbers);
            map.put("pageIndex", (pageIndex - 1) * pageSize);
            map.put("pageSize", pageSize);

            alarms = alarmDao.allByDeviceNumbers(map);
//            alarms = alarmDao.allByDeviceNumbers(deviceNumbers);
        }

        System.out.println(alarms);
        return alarms;

    }

}
