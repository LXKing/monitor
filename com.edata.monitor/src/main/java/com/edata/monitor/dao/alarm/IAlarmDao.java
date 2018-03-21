package com.edata.monitor.dao.alarm;

import java.util.Date;
import java.util.List;

public interface IAlarmDao {


    List<Alarm> untreatedByDeviceNumber(String deviceNumber);

    List<Alarm> untreatedByDeviceNumbers(List<String> deviceNumbers);

    int processPageCount(String deviceNumber, Date start, Date end);

    List<Alarm> processedPageDetail(String deviceNumber, Date start, Date end, int pageIndex, int pageSize);

    Date getAlarmTimestamp(String alarmId);

    void processOnce(ProcessOnceDto dto);

    void processAll(ProcessAllDto dto);

}
