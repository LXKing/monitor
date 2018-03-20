package com.edata.monitor.dao.alarm;

import java.util.Date;
import java.util.List;

public interface IAlarmDao {

	List<AlarmDto> untreatedByDeviceNumber(String deviceNumber);

	List<AlarmDto> untreatedByDeviceNumbers(List<String> deviceNumbers);

	int processPageCount(String deviceNumber, Date start, Date end);

	List<AlarmDto> processedPageDetail(String deviceNumber, Date start, Date end, int pageIndex, int pageSize);

	Date getAlarmTimestamp(String alarmId);

	void processOnce(ProcessOnceDto dto);

	void processAll(ProcessAllDto dto);

}
