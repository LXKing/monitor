package mmp.gps.monitor.dao;

import mmp.gps.domain.alarm.Alarm;
import mmp.gps.domain.alarm.ProcessAllDto;
import mmp.gps.domain.alarm.ProcessOnceDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface IAlarmDao {



    List<Alarm> untreatedByDeviceNumber(Map map);

    List<Alarm> untreatedByDeviceNumbers(List<String> deviceNumbers);

    //    List<Alarm> allByDeviceNumbers( @Param("deviceNumbers") List<String> deviceNumbers, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);
    List<Alarm> allByDeviceNumbers(Map map);

    int processPageCount(String deviceNumber, Date start, Date end);

    List<Alarm> processedPageDetail(String deviceNumber, Date start, Date end, int pageIndex, int pageSize);

    Date getAlarmTimestamp(String alarmId);

    void processOnce(ProcessOnceDto dto);

    int getCount(List<String> deviceNumbers);

    int getDnUntreated(String deviceNumbers, Date start, Date end);

    void processAll(ProcessAllDto dto);

}
